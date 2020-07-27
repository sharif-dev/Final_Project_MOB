package com.example.project.ui.UserPage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.MainActivity;
import com.example.project.R;
import com.example.project.SignInActivity;
import com.example.project.post_data;
import com.google.android.material.tabs.TabLayout;
import com.example.project.myAdaptor;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


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
        
        // you liked:
        post_likedArrayList=new ArrayList<>();
        post_likedArrayList.add(new post_data("maryam".toString(),"marvt","i'm tired"));
        post_likedArrayList.add(new post_data("zahra".toString(),"zari","I'm selfish, impatient and a little insecure. I make mistakes, I am out of control and at times hard to handle. But if you can't handle me at my worst, then you sure as hell don't deserve me at my best"));
        post_likedArrayList.add(new post_data("ali","ali88","Motivational quotes can help you reach your potential each day."));
        post_likedArrayList.add(new post_data("ahmad","ah_k","And if you’re on the verge of giving up or struggling to push yourself to the next level"));
        post_likedArrayList.add(new post_data("sogand","so_zamani"," These motivational quotes will give you the jumpstart your day needs, so don’t forget to bookmark this page"));
        post_likedArrayList.add(new post_data("zeinab","z_e","har har har"));
        post_likedArrayList.add(new post_data("saeed","saeede_hamidi","dg base"));
        post_likedArrayList.add(new post_data("fateme","fat9988","What Is Motivation"));
        post_likedArrayList.add(new post_data("mina","mina_r"," If I’m feeling like I’m in a rut, motivational songs like “You’re a Superstar” by Love Inc. picks me up."));

        //you disliked:

        post_dislikedArrayList=new ArrayList<>();
        post_dislikedArrayList.add(new post_data("dis".toString(),"marvt","you disliked me"));
        post_dislikedArrayList.add(new post_data("dislike".toString(),"zari","I'm selfish, impatient and a little insecure. I make mistakes, I am out of control and at times hard to handle. But if you can't handle me at my worst, then you sure as hell don't deserve me at my best"));
        post_dislikedArrayList.add(new post_data("jsajsk","ali88","Motivational quotes can help you reach your potential each day."));
        post_dislikedArrayList.add(new post_data("ahmad","ah_k","And if you’re on the verge of giving up or struggling to push yourself to the next level"));
        post_dislikedArrayList.add(new post_data("sogand","so_zamani"," These motivational quotes will give you the jumpstart your day needs, so don’t forget to bookmark this page"));
        post_dislikedArrayList.add(new post_data("zeinab","z_e","har har har"));
        post_dislikedArrayList.add(new post_data("saeed","saeede_hamidi","dg base"));
        post_dislikedArrayList.add(new post_data("fateme","fat9988","What Is Motivation"));
        post_dislikedArrayList.add(new post_data("mina","mina_r"," If I’m feeling like I’m in a rut, motivational songs like “You’re a Superstar” by Love Inc. picks me up."));

        recyclerView = root.findViewById(R.id.profile_recycler);

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

                    //myAdaptor2 = new myAdaptor(post_likedArrayList);
                }else {
                    //myAdaptor = new myAdaptor(post_dislikedArrayList);
                    recyclerView.setAdapter(myAdaptor2);

                }
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////
        profile_pic=root.findViewById(R.id.profile_image);

        profile_pic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                openGallery();
            }
        });
        ////////////////////////////////////////////////////

      /*  Button log_out=root.findViewById(R.id.button_logout);

        log_out.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(getContext(), SignInActivity.class);
                getActivity().startActivity(myIntent);
            }
        });
*/
        ///////////////////////////////////



        return root;

    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @SuppressLint("RestrictedApi")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            Log.i("image selected", "set");
            profile_pic.setImageURI(imageUri);
        }
    }







}
