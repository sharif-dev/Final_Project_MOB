package com.example.project;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        TextView dislike_num;
        ImageView profile_pic;
        ImageView post_image;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_post);
            user_id = itemView.findViewById(R.id.username_post);
            post_text=itemView.findViewById(R.id.text_post);
            like=itemView.findViewById(R.id.like_post);
            like_num=itemView.findViewById(R.id.like_text_post);

            dislike=itemView.findViewById(R.id.dislike_post);
            //dislike_num=itemView.findViewById(R.id.dislike_text_post);

            like.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    like_num.setText(String.valueOf(Integer.parseInt(like_num.getText().toString())+1));
                }
            });

            /*dislike.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    dislike_num.setText(String.valueOf(Integer.parseInt(dislike_num.getText().toString())-1));
                }
            });*/
            //profile_pic=itemView.findViewById(R.id.profile_image_post);
            //post_image = itemView.findViewById(R.id.image_post);
        }
    }
}
