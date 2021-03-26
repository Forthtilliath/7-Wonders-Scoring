package com.example.a7wondersscoring.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Accueil");
        //mText.setValue(getString(R.string.text_home));
    }

    public LiveData<String> getText() {
        return mText;
    }
}