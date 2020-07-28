package com.example.project;

import android.content.Intent;
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
import java.util.ArrayList;

public class myAdaptor extends RecyclerView.Adapter<myAdaptor.ViewHolder> {
    private ArrayList<post_data> post_data;
    private int index = 0;

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
        //holder.post_image.setImageURI(this.post_data.get(position).imageUri);
//        holder.post_image.setImageResource(this.post_data.get(position).imageUri);
/*
        if(!(this.post_data.get(position).videoUri ==null)){
           // holder.video.setVideoURI(this.post_data.get(position).videoUri);
             holder.video.setV(this.post_data.get(position).videoUri);


        }*/

        if(!(this.post_data.get(position).imageUri ==null)) {
            //holder.post_image.setImageURI(this.post_data.get(position).imageUri);
            holder.post_image.setImageResource(this.post_data.get(position).imageUri);
            holder.post_image.setVisibility(View.VISIBLE);

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
        TextView user_id;
        TextView post_text;
        ImageView like;
        TextView like_num;
        ImageView dislike;
        //TextView dislike_num;
        ImageView profile_pic;
        ImageView post_image;
        VideoView video;

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
            //dislike_num=itemView.findViewById(R.id.dislike_text_post);

            like.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    if(String.valueOf(dislike.getTag()).equals(String.valueOf(R.drawable.down))){
                        if(String.valueOf(like.getTag()).equals(String.valueOf(R.drawable.up))){
                            like.setImageResource(R.drawable.fill_up);
                            like.setTag(R.drawable.fill_up);
                            like_num.setText(String.valueOf(Integer.parseInt(like_num.getText().toString())+1));
                        }else {
                            like.setImageResource(R.drawable.up);
                            like.setTag(R.drawable.up);
                            like_num.setText(String.valueOf(Integer.parseInt(like_num.getText().toString())-1));
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
                        }else {
                            dislike.setImageResource(R.drawable.down);
                            dislike.setTag(R.drawable.down);
                            like_num.setText(String.valueOf(Integer.parseInt(like_num.getText().toString())+1));
                        }
                    }

                }
            });
            //profile_pic=itemView.findViewById(R.id.profile_image_post);
            //post_image = itemView.findViewById(R.id.image_post);
        }
    }
}
