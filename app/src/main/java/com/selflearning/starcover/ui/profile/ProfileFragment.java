package com.selflearning.starcover.ui.profile;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.selflearning.starcover.Logic.Cover;
import com.selflearning.starcover.R;
import com.selflearning.starcover.ui.home.MyHomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private RecyclerView coversProfileView;
    List<Cover> coverList;
    Cover cover;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.profile_fragment,container,false);
        
//        final TextView textView = root.findViewById(R.id.text_profile);
//        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        coversProfileView =(RecyclerView) root.findViewById(R.id.profile_recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        coversProfileView.setLayoutManager(layoutManager);

        coverList = new ArrayList<>();


        //UI TESTING CODE

        cover = new Cover("qasim_123",R.drawable.image,"My Song","Artist","6 : 33","567",R.drawable.thumbnail);
        coverList.add(cover);
        cover = new Cover("sharjeel-123",R.drawable.image,"My Song","Artist","6 : 33","567",R.drawable.thumbnail2);
        coverList.add(cover);
        cover = new Cover("qasim_123",R.drawable.image,"My Song","Artist","6 : 33","567",R.drawable.thumbnail);
        coverList.add(cover);
        cover = new Cover("qasim_123",R.drawable.image,"My Song","Artist","6 : 33","567",R.drawable.thumbnail);
        coverList.add(cover);
        cover = new Cover("qasim_123",R.drawable.image,"My Song","Artist","6 : 33","567",R.drawable.thumbnail);
        coverList.add(cover);
        cover = new Cover("qasim_123",R.drawable.image,"My Song","Artist","6 : 33","567",R.drawable.thumbnail);
        coverList.add(cover);
        cover = new Cover("qasim_123",R.drawable.image,"My Song","Artist","6 : 33","567",R.drawable.thumbnail);
        coverList.add(cover);

        //END OF UI TEST CODE

        MyProfileAdapter myAdapter = new MyProfileAdapter(getActivity(),coverList);
        coversProfileView.setAdapter(myAdapter);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}