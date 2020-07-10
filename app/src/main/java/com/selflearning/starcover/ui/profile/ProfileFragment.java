package com.selflearning.starcover.ui.profile;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.selflearning.starcover.Logic.Cover;
import com.selflearning.starcover.R;
import com.selflearning.starcover.friends.FriendsActivity;

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
        View root = inflater.inflate(R.layout.fragment_profile,container,false);
        
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

        root.findViewById(R.id.friends_intent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FriendsActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}