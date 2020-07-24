package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdaptor {
    private ArrayList<com.example.project.post_data> post_data;
    private int index = 0;

    myAdaptor(ArrayList<com.example.project.post_data> post_data) {
        this.post_data = post_data;
    }



    public myAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);
    }


    public void onBindViewHolder( ViewHolder holder, int position) {

        

        index++;

    }

    public int getItemCount() {
        return post_data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView user_id;
        TextView post_text;
        ImageView profile_pic;
        ImageView post_image;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_post);
            //user_id = itemView.findViewById(R.id.use);
            post_text=itemView.findViewById(R.id.text_post);
            profile_pic=itemView.findViewById(R.id.profile_image_post);
            post_image = itemView.findViewById(R.id.image_post);
        }
    }
}
