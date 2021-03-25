package com.example.a7wondersscoring.ui.players;

import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

public class PlayersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlayersViewModel() {
        System.out.println("a");
        mText = new MutableLiveData<>();
        mText.setValue("This is players fragment");
    }

    public PlayersViewModel(String text) {
        System.out.println("b");
        mText = new MutableLiveData<>();
        mText.setValue(text);
    }

    public LiveData<String> getText() {
        return mText;
    }
}