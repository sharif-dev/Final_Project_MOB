package com.example.project.ui.FaveStar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project.R;

public class FaveStarFragment extends Fragment {

    private FaveStarViewModel faveStarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_fave_stars,container,false);

        faveStarViewModel =
                ViewModelProviders.of(this).get(FaveStarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_fave_stars, container, false);
        //final TextView textView = root.findViewById(R.id.text_stars);
        faveStarViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;


    }
}