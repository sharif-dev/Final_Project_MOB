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
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import java.util.*;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;
    RecyclerView recyclerView;
    ArrayList<post_data> post_dataArrayList;
    List<String> Tweet_Like = new ArrayList<>();
    List<String> Tweet_Dislike = new ArrayList<>();
    myAdaptor myAdaptor;
    ParseFile temp;
    Hashtable<String,ParseFile > my_dict = new Hashtable<String, ParseFile>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = root.findViewById(R.id.home_recyclerv);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        post_dataArrayList=new ArrayList<>();

        ParseQuery<ParseUser> query1 = ParseUser.getQuery();
        query1.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            ParseObject user = list.get(i);
                            if (user.getParseFile("Photo")!=null) {
                            my_dict.put(user.getString("username"), user.getParseFile("Photo"));
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + my_dict);

                        }}
                    }

                }

            }
        });


        ParseUser user = ParseUser.getCurrentUser();
        ParseRelation<ParseObject> relation1 = user.getRelation("Likes");
        ParseQuery<ParseObject> query2 = relation1.getQuery();
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    if (list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            ParseObject tweet_like = list.get(i);
                            String id_tweet = tweet_like.getObjectId();
                            Tweet_Like.add(id_tweet);
                        }
                    }
                }
            }
        });

        ParseRelation<ParseObject> relation2 = user.getRelation("Dislikes");
        ParseQuery<ParseObject> query3 = relation2.getQuery();
        query3.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    if (list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            ParseObject tweet_like = list.get(i);
                            String id_tweet = tweet_like.getObjectId();
                            Tweet_Dislike.add(id_tweet);
                        }
                    }

                }
            }
        });

        ParseQuery<ParseObject> query = new ParseQuery<>("tweets");
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    if (list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            ParseObject tweet = list.get(i);
                            String id_tweet = tweet.getObjectId();
                            temp=my_dict.get(tweet.getString("User_username"));
                            if (Tweet_Like.contains(id_tweet))
                                post_dataArrayList.add(new post_data(tweet.getObjectId() , tweet.getString("User_name") , tweet.getString("User_username") , tweet.getInt("Like"), tweet.getString("Text"), tweet.getParseFile("Photo"), temp,  tweet.getParseFile("Movie")  , true  , false));
                            else if (Tweet_Dislike.contains(id_tweet))
                                post_dataArrayList.add(new post_data(tweet.getObjectId() , tweet.getString("User_name") , tweet.getString("User_username") , tweet.getInt("Like"), tweet.getString("Text"), tweet.getParseFile("Photo"),temp,  tweet.getParseFile("Movie")  , false , true));
                            else
                                post_dataArrayList.add(new post_data(tweet.getObjectId() , tweet.getString("User_name") , tweet.getString("User_username") , tweet.getInt("Like"), tweet.getString("Text"), tweet.getParseFile("Photo"), temp,  tweet.getParseFile("Movie") , false , false));
                        }

                        myAdaptor = new myAdaptor(post_dataArrayList);
                        recyclerView.setAdapter(myAdaptor);
                    }

                }
            }
        });







        return root;


    }


}