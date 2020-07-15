package com.selflearning.starcover.ui.profile;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
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
import com.selflearning.starcover.uploading.RecordFragment;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private RecyclerView coversProfileView;
    List<Cover> coverList;
    Cover cover;
    String name;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile,container,false);

        coversProfileView =(RecyclerView) root.findViewById(R.id.profile_recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        coversProfileView.setLayoutManager(layoutManager);

        coverList = new ArrayList<>();
        //getting newly recorded song
       String recordPath=getActivity().getExternalFilesDir("/").getAbsolutePath();



        //UI TESTING CODE

        File file = new File(recordPath);
        String[] fileNames = file.list();
        for (String name : fileNames) {
            Uri uri = Uri.fromFile(new File(recordPath + "/" + name));
            cover = new Cover("qasim_123",R.drawable.image,name.substring(11),"Artist","6 : 33","567",R.drawable.thumbnail,uri);
            coverList.add(cover);
        }

       /*cover = new Cover("sharjeel-123",R.drawable.image,"My Song","Artist","6 : 33","567",R.drawable.thumbnail2,uri);
        coverList.add(cover);
        coverList.add(cover);
        cover = new Cover("qasim_123",R.drawable.image,"My Song","Artist","6 : 33","567",R.drawable.thumbnail);
        coverList.add(cover);
        cover = new Cover("qasim_123",R.drawable.image,"My Song","Artist","6 : 33","567",R.drawable.thumbnail);
        coverList.add(cover);
        cover = new Cover("qasim_123",R.drawable.image,"My Song","Artist","6 : 33","567",R.drawable.thumbnail);
        coverList.add(cover);
        cover = new Cover("qasim_123",R.drawable.image,"My Song","Artist","6 : 33","567",R.drawable.thumbnail);
        coverList.add(cover);*/

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