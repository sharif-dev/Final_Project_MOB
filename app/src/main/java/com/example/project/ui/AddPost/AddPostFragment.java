package com.example.project.ui.AddPost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project.R;

import java.util.Objects;


public class AddPostFragment extends Fragment {

    private AddPostViewModel addPostViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        /*
        EditText text= requireView().findViewById(R.id.text_add_post);
        ImageView profile_image=requireView().findViewById(R.id.profile_image_add_post);
        ImageView image_attached=requireView().findViewById(R.id.image_post);
        Button post_button=requireView().findViewById(R.id.post_button);
        Button attach_image=requireView().findViewById(R.id.attach_image);
        return inflater.inflate(R.layout.fragment_add_post,container,false);


         */

        addPostViewModel =
                ViewModelProviders.of(this).get(AddPostViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_post, container, false);
        //final EditText text = root.findViewById(R.id.text_add_post);
        addPostViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //text.setText(s);
            }
        });
        return root;



    }
}
