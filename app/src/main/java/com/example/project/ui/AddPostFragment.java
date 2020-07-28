package com.example.project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project.AddPostActivity;
import com.example.project.R;
import com.example.project.SignInActivity;

public class AddPostFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Intent myIntent = new Intent(getContext(), AddPostActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(myIntent);
        //this.getActivity().finish();

        View root = inflater.inflate(R.layout.fragment_addpost, container, false);

        return root;


    }
}
