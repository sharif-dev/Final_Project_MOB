package com.example.project;

import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

public class post_data {

    String name;
    String id;
    Integer like;
    String username;
    String post_text;

   // Uri imageUri,profileUri,videoUri;
    Integer imageUri,profileUri,videoUri;

    public post_data(String id , String name, String username, Integer like ,String post_text,Integer imageUri,Integer profileUri,Integer videoUri ){
        setName(name);
        setid(id);
        setLike(like);
        Log.i("const ", name);
        setImageUri(imageUri);
        setVideoUri(videoUri);
        setProfileUri(profileUri);
        //setPost_Image(post_Image);
        setPost_text(post_text);
        //setProfile_pic(profile_pic);
        setUsername(username);

    }

    private void setLike(Integer like) {
        this.like =like;
    }

    private void setid(String id) {this.id =id;}

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUri(Integer imageUri) {
        this.imageUri = imageUri;
    }

    public void setProfileUri(Integer profileUri) {
        this.profileUri = profileUri;
    }

    public void setVideoUri(Integer videoUri) {
        this.videoUri = videoUri;
    }

    /*
    public void setPost_Image(Image post_Image) {
        this.post_Image = post_Image;
    }*/

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

   /* public void setProfile_pic(Image profile_pic) {
        this.profile_pic = profile_pic;
    }
*/
    public void setUsername(String username) {
        this.username = username;
    }


}
