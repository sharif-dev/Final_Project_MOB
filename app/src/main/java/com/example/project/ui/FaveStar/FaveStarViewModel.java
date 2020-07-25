package com.example.project.ui.FaveStar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FaveStarViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FaveStarViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("This is favestar fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}