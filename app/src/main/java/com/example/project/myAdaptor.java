package com.example.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.FileUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project.post_data;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class myAdaptor extends RecyclerView.Adapter<myAdaptor.ViewHolder> {
    private ArrayList<post_data> post_data;
    private int index = 0;
    TextView like_num_tweet;

    public myAdaptor(ArrayList<post_data> post_data) {
        this.post_data = post_data;
    }



    public myAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);
    }


   /* public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }*/


    public void onBindViewHolder( ViewHolder holder, int position) {

        holder.post_text.setText(this.post_data.get(position).post_text);
        holder.name.setText(this.post_data.get(position).name);
        holder.user_id.setText(this.post_data.get(position).username);
        holder.id_post = post_data.get(position).id;
        holder.no_of_like = post_data.get(position).like;
        holder.like_num.setText(String.valueOf(holder.no_of_like));


        //holder.post_image.setImageURI(this.post_data.get(position).imageUri);
//        holder.post_image.setImageResource(this.post_data.get(position).imageUri);
/*
        if(!(this.post_data.get(position).videoUri ==null)){
           // holder.video.setVideoURI(this.post_data.get(position).videoUri);
             holder.video.setV(this.post_data.get(position).videoUri);


        }*/

        if(!(this.post_data.get(position).imageUri == null)) {
            //holder.post_image.setImageURI(this.post_data.get(position).imageUri);
//            ParseFile image = (ParseFile) userData.getParseFile("user_image");
            holder.loadImages( post_data.get(position).imageUri, holder.post_image);


//            holder.post_image.setImageResource(this.post_data.get(position).imageUri);
//            holder.post_image.setVisibility(View.VISIBLE);

        }
        if(!(this.post_data.get(position).profileUri ==null)) {
            //holder.profile_pic.setImageURI(this.post_data.get(position).profileUri);
            holder.profile_pic.setImageResource(this.post_data.get(position).profileUri);

        }

        index++;

    }

    public int getItemCount() {
        return post_data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        String id_post;
        Integer no_of_like;
        TextView user_id;
        TextView post_text;
        ImageView like;
        TextView like_num;
        ImageView dislike;
        //TextView dislike_num;
        ImageView profile_pic;
        ImageView post_image;
        VideoView video;
        Boolean is_liked ;
        Boolean is_disliked;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_post);
            user_id = itemView.findViewById(R.id.username_post);
            post_text=itemView.findViewById(R.id.text_post);
            like=itemView.findViewById(R.id.like_post);
            like_num=itemView.findViewById(R.id.like_text_post);
            dislike=itemView.findViewById(R.id.dislike_post);
            like.setTag(R.drawable.up);
            dislike.setTag(R.drawable.down);
            profile_pic=itemView.findViewById(R.id.profile_image);
            post_image=itemView.findViewById(R.id.image_item_post);
            video=itemView.findViewById(R.id.video_item_post);

            like.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (String.valueOf(dislike.getTag()).equals(String.valueOf(R.drawable.down))) {
                        if (String.valueOf(like.getTag()).equals(String.valueOf(R.drawable.up))) {
                            like.setImageResource(R.drawable.fill_up);
                            like.setTag(R.drawable.fill_up);
                            like_num.setText(String.valueOf(Integer.parseInt(like_num.getText().toString()) + 1));
                            is_liked = true;
                            is_disliked = false;
                            ParseQuery<ParseObject> query = ParseQuery.getQuery("tweets");
                            query.getInBackground(id_post, new GetCallback<ParseObject>() {
                                public void done(ParseObject tweet, ParseException e) {
                                    if (e == null) {
                                        tweet.increment("Like");
                                        tweet.saveInBackground();
                                        ParseUser user = ParseUser.getCurrentUser();
                                        ParseRelation<ParseObject> relation = user.getRelation("Likes");
                                        relation.add(tweet);
                                        user.saveInBackground();
                                    }
                                }
                            });

                        } else {
                            like.setImageResource(R.drawable.up);
                            like.setTag(R.drawable.up);
                            like_num.setText(String.valueOf(Integer.parseInt(like_num.getText().toString()) - 1));
                            is_liked = false;
                            is_disliked = true;
                            ParseQuery<ParseObject> query = ParseQuery.getQuery("tweets");
                            query.getInBackground(id_post, new GetCallback<ParseObject>() {
                                public void done(ParseObject tweet, ParseException e) {
                                    if (e == null) {
                                        tweet.increment("Like" , -1);
                                        tweet.saveInBackground();
                                        ParseUser user = ParseUser.getCurrentUser();
                                        ParseRelation<ParseObject> relation = user.getRelation("Dislikes");
                                        relation.remove(tweet);
                                        user.saveInBackground();
                                    }
                                }
                            });

                        }
                    }
                }
            });

            dislike.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    if(String.valueOf(like.getTag()).equals(String.valueOf(R.drawable.up))){
                        if(String.valueOf(dislike.getTag()).equals(String.valueOf(R.drawable.down))){
                            dislike.setImageResource(R.drawable.fill_down);
                            dislike.setTag(R.drawable.fill_down);
                            like_num.setText(String.valueOf(Integer.parseInt(like_num.getText().toString())-1));
                            is_liked = false;
                            is_disliked = true;
                            ParseQuery<ParseObject> query = ParseQuery.getQuery("tweets");
                            query.getInBackground(id_post, new GetCallback<ParseObject>() {
                                public void done(ParseObject tweet, ParseException e) {
                                    if (e == null) {
                                        tweet.increment("Like" , -1);
                                        tweet.saveInBackground();
                                        ParseUser user = ParseUser.getCurrentUser();
                                        ParseRelation<ParseObject> relation = user.getRelation("Dislikes");
                                        relation.remove(tweet);
                                        user.saveInBackground();
                                    }
                                }
                            });

                        }else {
                            dislike.setImageResource(R.drawable.down);
                            dislike.setTag(R.drawable.down);
                            like_num.setText(String.valueOf(Integer.parseInt(like_num.getText().toString())+1));
                            is_liked = true;
                            is_disliked = false;
                            ParseQuery<ParseObject> query = ParseQuery.getQuery("tweets");
                            query.getInBackground(id_post, new GetCallback<ParseObject>() {
                                public void done(ParseObject tweet, ParseException e) {
                                    if (e == null) {
                                        tweet.increment("Like" );
                                        tweet.saveInBackground();
                                        ParseUser user = ParseUser.getCurrentUser();
                                        ParseRelation<ParseObject> relation = user.getRelation("Likes");
                                        relation.add(tweet);
                                        user.saveInBackground();
                                    }
                                }
                            });
                        }
                    }

                }
            });
            //profile_pic=itemView.findViewById(R.id.profile_image_post);
            //post_image = itemView.findViewById(R.id.image_post);
        }
        private void loadImages(ParseFile thumbnail, final ImageView img) {

            if (thumbnail != null) {
                thumbnail.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] data, ParseException e) {
                        if (e == null) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                            img.setImageBitmap(bmp);
                            img.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        }
    }
}
