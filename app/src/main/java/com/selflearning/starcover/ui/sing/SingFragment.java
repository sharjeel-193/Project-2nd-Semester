package com.selflearning.starcover.ui.sing;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.selflearning.starcover.R;
import com.selflearning.starcover.ui.profile.ProfileViewModel;

public class SingFragment extends Fragment {

    private SingViewModel singViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        singViewModel = ViewModelProviders.of(this).get(SingViewModel.class);
        View root = inflater.inflate(R.layout.sing_fragment,container,false);
//        final TextView textView = root.findViewById(R.id.text_sing);
//        singViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

}