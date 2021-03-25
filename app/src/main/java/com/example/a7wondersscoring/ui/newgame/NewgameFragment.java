package com.example.a7wondersscoring.ui.newgame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.a7wondersscoring.R;

public class NewgameFragment extends Fragment {

    private NewgameViewModel newgameViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newgameViewModel =
                new ViewModelProvider(this).get(NewgameViewModel.class);
        View root = inflater.inflate(R.layout.fragment_newgame, container, false);
        final TextView textView = root.findViewById(R.id.text_newgame);
        newgameViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}