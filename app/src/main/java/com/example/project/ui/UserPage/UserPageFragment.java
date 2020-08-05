package com.example.project.ui.UserPage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.MainActivity;
import com.example.project.R;
import com.example.project.SignInActivity;
import com.example.project.post_data;
import com.google.android.material.tabs.TabLayout;
import com.example.project.myAdaptor;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.parse.Parse.getApplicationContext;


public class UserPageFragment extends Fragment {

    //UserPageViewModel userPageViewModel;

    RecyclerView recyclerView;
    ArrayList<post_data> post_likedArrayList;
    ArrayList<post_data> post_dislikedArrayList;
    Uri imageUri;
    private static final int PICK_IMAGE = 100;
    ImageView profile_pic;
    myAdaptor myAdaptor1;
    myAdaptor myAdaptor2;
    byte[] upload_image;
    Boolean selected_media=false;
    TextView name;
    TextView username;
//    ImageView image_post;
//    ParseFile thumbnail;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent myIntent = new Intent(getContext(), SignInActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(myIntent);
        this.getActivity().finish();
        return super.onOptionsItemSelected(item);
    }



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_user_page, container, false);
        final TabLayout tabLayout= root.findViewById(R.id.tabLayout);

        name = (TextView) root.findViewById(R.id.name_profile);
        username = (TextView) root.findViewById(R.id.username_profille);





        // you liked:
        post_likedArrayList=new ArrayList<>();
//        post_likedArrayList.add(new post_data("maryam","marvt","i'm tired",R.drawable.photo_post,null,null,null,null,null,null));

        post_dislikedArrayList=new ArrayList<>();
//        post_dislikedArrayList.add(new post_data("zahra","zari","I'm selfish, impatient and a little insecure. I make mistakes, I am out of control and at times hard to handle. But if you can't handle me at my worst, then you sure as hell don't deserve me at my best",null,null,null,null,null,null,null));


//        recyclerView = root.findViewById(R.id.profile_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdaptor1 = new myAdaptor(post_dislikedArrayList);
        myAdaptor2 = new myAdaptor(post_likedArrayList);
        recyclerView.setAdapter(myAdaptor1);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {
                Log.i("tab2",tab.getText().toString());
                if (tab.getText().toString().equals("your posts")){
                    recyclerView.setAdapter(myAdaptor1);

                }else {
                    recyclerView.setAdapter(myAdaptor2);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i("tab",tab.getText().toString());
                if (tab.getText().toString().equals("your posts")){
                    recyclerView.setAdapter(myAdaptor1);

                }else {
                    recyclerView.setAdapter(myAdaptor2);

                }
            }
        });

        ParseUser user = ParseUser.getCurrentUser();
        profile_pic=root.findViewById(R.id.profile_image);
        loadImages_profile( user.getParseFile("Photo"), profile_pic);
        profile_pic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                openGallery();
            }
        });
        return root;

    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("RestrictedApi")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            Log.i("image selected", "set");
            profile_pic.setImageURI(imageUri);
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor =getApplicationContext().getContentResolver().query(imageUri,
                    filePathColumn, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();



            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap bitmap = null;

            try (ParcelFileDescriptor pfd = getApplicationContext().getContentResolver().openFileDescriptor(imageUri, "r")) {
                if (pfd != null) {
                    bitmap = BitmapFactory.decodeFileDescriptor(pfd.getFileDescriptor());
                }
            } catch (IOException ex) {

            }
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            assert bitmap != null;
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            upload_image= stream.toByteArray();
            selected_media = true;
            ParseUser user = ParseUser.getCurrentUser();
            System.out.println(user.get("username"));
            profile_pic.setVisibility(View.VISIBLE);
            ParseFile file = new ParseFile("photo.png", upload_image);
            try {
                file.save();
                System.out.println("save");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            user.put("Photo", file);

            user.saveInBackground();


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
