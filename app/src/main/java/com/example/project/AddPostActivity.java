package com.example.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddPostActivity extends AppCompatActivity {
    FloatingActionButton post,add_image,add_video;
   // FloatingActionButton attach;
    ImageView profile;
    ImageView image_post;
    VideoView videoView;
    EditText text;
    Uri selectedMediaUri;
    Boolean selected_media=false;

    private static final int PICK_IMAGE = 100;
    private static final int PICK_Video = 200;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("oncreat","2");

        setContentView(R.layout.activity_add_post);

        post = findViewById(R.id.postButton);
        add_image = findViewById(R.id.add_image);
        add_video =findViewById(R.id.add_video);
        profile = findViewById(R.id.profile_image_post);
        text = findViewById(R.id.text_post);
        image_post=findViewById(R.id.imagetopost);
        videoView=findViewById(R.id.videoView);

        image_post.setVisibility(View.GONE);
        videoView.setVisibility(View.GONE);


        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(getApplicationContext(),"you clicked back button", Toast.LENGTH_SHORT).show();

            }
        };
        callback.setEnabled(true);


        post.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(AddPostActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        add_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if(!selected_media){
                    openGallery_Image();
                }else {
                    Toast.makeText(getApplicationContext(),"you can add one media", Toast.LENGTH_SHORT).show();
                }

            }
        });
        add_video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                if(!selected_media){
                    openGallery_Video();
                }else {
                    Toast.makeText(getApplicationContext(),"you can add one media", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void openGallery_Image() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }
    private void openGallery_Video() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_Video);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
             selectedMediaUri= data.getData();
            assert selectedMediaUri != null;
            if (selectedMediaUri.toString().contains("image")) {
                image_post.setImageURI(selectedMediaUri);
                selected_media=true;
                image_post.setVisibility(View.VISIBLE);
            } else if (selectedMediaUri.toString().contains("video")) {
                videoView.setVideoURI(selectedMediaUri);
                selected_media=true;
                videoView.setVisibility(View.VISIBLE);
                videoView.setZOrderOnTop(true);//this line solve the problem
                videoView.start();
            }
        }
    }

}
