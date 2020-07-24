package com.example.project.ui.UserPage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;




public class UserPageViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UserPageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is User page fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}



