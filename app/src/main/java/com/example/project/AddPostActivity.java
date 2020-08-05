package com.example.project;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.project.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class AddPostActivity extends AppCompatActivity {
    FloatingActionButton post,add_image,add_video;
   // FloatingActionButton attach;
    ImageView profile;
    ImageView image_post;
    VideoView videoView;
    EditText text;
    Uri selectedMediaUri;
    Boolean selected_media=false;
    byte[] upload_image;
    byte[] upload_movie;
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



        ParseUser user = ParseUser.getCurrentUser();
        profile=findViewById(R.id.profile_image_post);
        loadImages_profile( user.getParseFile("Photo"), profile);


//        post.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            public void onClick(View v)
//            {
//                Intent myIntent = new Intent(AddPostActivity.this, MainActivity.class);
//                ParseObject tweet = new ParseObject("tweets");
//                tweet.put("Text", text.getText().toString());
//                tweet.put("Like", 0);
//                tweet.put("User_name", Objects.requireNonNull(ParseUser.getCurrentUser().getString("name")));
//                tweet.put("User_username", Objects.requireNonNull(ParseUser.getCurrentUser().getUsername()));
//                if (upload_image != null) {
//                    ParseFile file = new ParseFile("photo.png", upload_image);
//                    try {
//                        file.save();
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    tweet.put("Photo", file);
//                }
//                tweet.saveInBackground();
//                startActivity(myIntent);
//            }
//        });

        post.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View v)
            {
                Intent myIntent = new Intent(AddPostActivity.this, MainActivity.class);
                ParseObject tweet = new ParseObject("tweets");
                tweet.put("Text", text.getText().toString());
                tweet.put("Like", 0);
                tweet.put("User_name", Objects.requireNonNull(ParseUser.getCurrentUser().getString("name")));
                tweet.put("User_username", Objects.requireNonNull(ParseUser.getCurrentUser().getUsername()));
                if (upload_image != null) {
                    ParseFile file = new ParseFile("photo.png", upload_image);
                    try {
                        file.save();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    tweet.put("Photo", file);
                }
                if (upload_movie != null) {
                    ParseFile file = new ParseFile("movie.mp4", upload_movie);
                    try {
                        file.save();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    tweet.put("Movie", file);
                }
                tweet.saveInBackground();
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


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            selectedMediaUri = data.getData();
            assert selectedMediaUri != null;
            if (selectedMediaUri.toString().contains("image")) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(selectedMediaUri,
                        filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                Bitmap bitmap = null;

                try (ParcelFileDescriptor pfd = this.getContentResolver().openFileDescriptor(selectedMediaUri, "r")) {
                    if (pfd != null) {
                        bitmap = BitmapFactory.decodeFileDescriptor(pfd.getFileDescriptor());
                    }
                } catch (IOException ex) {}
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                assert bitmap != null;
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                upload_image= stream.toByteArray();
                image_post.setImageBitmap(bitmap);
                selected_media = true;
                image_post.setVisibility(View.VISIBLE);
            } else if (selectedMediaUri.toString().contains("video")) {
                videoView.setVideoURI(selectedMediaUri);
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }

                String[] projection = { MediaStore.Video.Media.DATA };
                Cursor cursor = getContentResolver().query(selectedMediaUri, projection, null, null, null);
                String picturePath = "";
                if (cursor != null) {
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                    System.out.println("column_index >>> " + column_index);
                    cursor.moveToFirst();
                    picturePath = cursor.getString(column_index);
                    cursor.close();
                    File inputFile = new File(picturePath);
                    try {
                        inputFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(inputFile);
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(inputFile);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buf = new byte[(int) inputFile.length()];
                    try {
                        assert fis != null;
                        for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                            bos.write(buf, 0, readNum);
                            upload_movie = bos.toByteArray();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                selected_media = true;
                videoView.setVisibility(View.VISIBLE);
                videoView.setZOrderOnTop(true);//this line solve the problem
                videoView.start();
            }
        }


    }


    private void loadImages_profile(ParseFile thumbnail, final ImageView img) {

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
