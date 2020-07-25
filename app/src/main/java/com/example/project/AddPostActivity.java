package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddPostActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("oncreat","2");

        setContentView(R.layout.activity_add_post);

        FloatingActionButton post=findViewById(R.id.postButton);
        Button attach=findViewById(R.id.attach);
        ImageView profile=findViewById(R.id.profile_image_post);
        EditText text=findViewById(R.id.text_post);

        post.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(AddPostActivity.this, MainActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                Log.i("intent created","1");
                startActivity(myIntent);
            }
        });

    }
}
