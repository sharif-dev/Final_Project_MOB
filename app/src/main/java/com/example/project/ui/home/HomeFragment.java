package com.example.project.ui.home;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.MainActivity;
import com.example.project.R;
import com.example.project.myAdaptor;
import com.example.project.post_data;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;
    RecyclerView recyclerView;
    ArrayList<post_data> post_dataArrayList;
    myAdaptor myAdaptor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.home_recyclerv);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        post_dataArrayList=new ArrayList<>();
        ParseQuery<ParseObject> query = new ParseQuery<>("tweets");
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    if (list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            ParseObject tweet = list.get(i);
//                            ParseRelation<ParseObject> relation1 = ParseUser.getCurrentUser().getRelation("Likes");
//                            ParseQuery<ParseObject> query1 = relation1.getQuery();
                            System.out.println(">>>>>>>>>>>>> photo home : " + tweet.getParseFile("Photo"));
                            post_dataArrayList.add(new post_data(tweet.getObjectId() , tweet.getString("User_name") , tweet.getString("User_username") , tweet.getInt("Like"), tweet.getString("Text"), tweet.getParseFile("Photo"), R.drawable.maryam, null ));
                        }
                        myAdaptor = new myAdaptor(post_dataArrayList);
                        //recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(myAdaptor);
                    }

                }
            }
        });

//        post_dataArrayList.add(new post_data("maryam","marvt","i'm tired",R.drawable.photo_post,R.drawable.maryam,null));
//        post_dataArrayList.add(new post_data("zahra","zari","I'm selfish, impatient and a little insecure. I make mistakes, I am out of control and at times hard to handle. But if you can't handle me at my worst, then you sure as hell don't deserve me at my best",null,R.drawable.ahmadinejhad,null));
//        post_dataArrayList.add(new post_data("ali","ali88","Motivational quotes can help you reach your potential each day.",null,R.drawable.hasan,null));
//        post_dataArrayList.add(new post_data("ahmad","ah_k","And if you’re on the verge of giving up or struggling to push yourself to the next level",R.drawable.jungle,null,null));
//        post_dataArrayList.add(new post_data("sogand","so_zamani"," These motivational quotes will give you the jumpstart your day needs, so don’t forget to bookmark this page",null,R.drawable.obama,null));
//        post_dataArrayList.add(new post_data("zeinab","z_e","har har har",R.drawable.baby,null,null));
//        post_dataArrayList.add(new post_data("saeed","saeede_hamidi","dg base",null,R.drawable.maryam,null));
//        post_dataArrayList.add(new post_data("fateme","fat9988","What Is Motivation",null,R.drawable.ahmadinejhad,null));
//        post_dataArrayList.add(new post_data("mina","mina_r"," If I’m feeling like I’m in a rut, motivational songs like “You’re a Superstar” by Love Inc. picks me up.",null,R.drawable.ahmadinejhad,null));




       /* for( int i=0;i<6;i++){
            Log.i("print haha", post_dataArrayList.get(i).toString());


        }*/

        return root;


    }


}