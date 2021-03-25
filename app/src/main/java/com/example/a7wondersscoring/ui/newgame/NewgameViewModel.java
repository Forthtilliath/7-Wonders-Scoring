package com.example.a7wondersscoring.ui.newgame;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewgameViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NewgameViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is newgame fragment");
    }

    public NewgameViewModel(String text) {
        mText = new MutableLiveData<>();
        mText.setValue(text);
    }

    public LiveData<String> getText() {
        return mText;
    }
}