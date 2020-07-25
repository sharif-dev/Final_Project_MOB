package com.example.project;

import android.media.Image;
import android.provider.MediaStore;

public class post_data {

    private String name;
    private String username;
    private String post_text;
    private Image profile_pic;
    private Image post_Image;
    //private MediaStore.Video.Media

    post_data(String name,String username,String post_text,Image profile_pic,Image post_Image){
        setName(name);
        setPost_Image(post_Image);
        setPost_text(post_text);
        setProfile_pic(profile_pic);
        setUsername(username);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPost_Image(Image post_Image) {
        this.post_Image = post_Image;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    public void setProfile_pic(Image profile_pic) {
        this.profile_pic = profile_pic;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}