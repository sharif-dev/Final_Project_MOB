package com.example.project.ui.Search;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class SearchFragment extends Fragment {


    EditText Search;
    FloatingActionButton button;
    String search;
    //private HomeViewModel homeViewModel;
    RecyclerView recyclerView;
    ArrayList<post_data> post_dataArrayList;
    myAdaptor myAdaptor;
    Hashtable<String, ParseFile> my_dict = new Hashtable<String, ParseFile>();
    ParseFile temp;


    @SuppressLint("WrongViewCast")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);
        Search=(EditText) root.findViewById(R.id.search_text_id);
        button = root.findViewById(R.id.search_button);


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

                } else {

                }
            }
        });


        recyclerView = root.findViewById(R.id.search_recyclerv);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        post_dataArrayList = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if (Search.getText()!=null){
                    search = Search.getText().toString();
                    search = search + " ";
                    System.out.println(search);
                    ParseQuery<ParseObject> query = new ParseQuery<>("tweets");
                    query.whereContains("Text",search);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> list, ParseException e) {
                            if (e == null) {
                                if (list.size() > 0) {
                                    for (int i = 0; i < list.size(); i++) {
                                        ParseObject tweet = list.get(i);
                                        ParseUser user = ParseUser.getCurrentUser();
                                        temp=my_dict.get(tweet.getString("User_username"));
                                        post_dataArrayList.add(new post_data(tweet.getObjectId() , tweet.getString("User_name") , tweet.getString("User_username") , tweet.getInt("Like"), tweet.getString("Text"), tweet.getParseFile("Photo"), temp,tweet.getParseFile("Movie"),false,false  ));
                                    }
                                    myAdaptor = new myAdaptor(post_dataArrayList);
                                    recyclerView.setAdapter(myAdaptor);
                                }

                            }
                        }
                    });
                }


            }
        });



        return root;


    }



}