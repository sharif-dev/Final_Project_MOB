package com.example.project.ui.UserPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.project.R;
import com.example.project.ui.Search.SearchViewModel;
import com.google.android.material.tabs.TabLayout;

public class UserPageFragment extends Fragment {

    UserPageViewModel userPageViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        userPageViewModel =
                ViewModelProviders.of(this).get(UserPageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_user_page, container, false);
        final TabLayout tabLayout= root.findViewById(R.id.tabLayout);
        //final TabLayout tab1=root.findViewById(R.id.tab1);
        final TextView textView = root.findViewById(R.id.textView);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {
                textView.setText(tab.getText());
            /*    faveStarViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        textView.setText(tab.getText());
                    }
                });*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return root;
        //return inflater.inflate(R.layout.fragment_user_page,container,false);
    }
}
