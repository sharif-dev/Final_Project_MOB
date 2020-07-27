package com.example.project.ui.home;

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

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;
    RecyclerView recyclerView;
    ArrayList<post_data> post_dataArrayList;
    myAdaptor myAdaptor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_home, container, false);


        post_dataArrayList=new ArrayList<>();
        post_dataArrayList.add(new post_data("maryam".toString(),"marvt","i'm tired"));
        post_dataArrayList.add(new post_data("zahra".toString(),"zari","I'm selfish, impatient and a little insecure. I make mistakes, I am out of control and at times hard to handle. But if you can't handle me at my worst, then you sure as hell don't deserve me at my best"));
        post_dataArrayList.add(new post_data("ali","ali88","Motivational quotes can help you reach your potential each day."));
        post_dataArrayList.add(new post_data("ahmad","ah_k","And if you’re on the verge of giving up or struggling to push yourself to the next level"));
        post_dataArrayList.add(new post_data("sogand","so_zamani"," These motivational quotes will give you the jumpstart your day needs, so don’t forget to bookmark this page"));
        post_dataArrayList.add(new post_data("zeinab","z_e","har har har"));
        post_dataArrayList.add(new post_data("saeed","saeede_hamidi","dg base"));
        post_dataArrayList.add(new post_data("fateme","fat9988","What Is Motivation"));
        post_dataArrayList.add(new post_data("mina","mina_r"," If I’m feeling like I’m in a rut, motivational songs like “You’re a Superstar” by Love Inc. picks me up."));



        recyclerView = root.findViewById(R.id.home_recyclerv);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        myAdaptor = new myAdaptor(post_dataArrayList);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(myAdaptor);
       /* for( int i=0;i<6;i++){
            Log.i("print haha", post_dataArrayList.get(i).toString());


        }*/

        return root;


    }
}