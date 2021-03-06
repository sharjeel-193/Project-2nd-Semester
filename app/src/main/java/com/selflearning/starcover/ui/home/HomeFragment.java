package com.selflearning.starcover.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selflearning.starcover.Logic.Cover;
import com.selflearning.starcover.MainActivity;
import com.selflearning.starcover.R;
import com.selflearning.starcover.uploading.RecordFragment;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView coversHomeView;
    List<Cover> coverList;
    Cover cover;
String name;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        coversHomeView =(RecyclerView) root.findViewById(R.id.cover_posts_feed);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        coversHomeView.setLayoutManager(layoutManager);

        coverList = new ArrayList<>();
        String recordPath=getActivity().getExternalFilesDir("/").getAbsolutePath();
        //UI TESTING CODE

        File file = new File(recordPath);
        String[] fileNames = file.list();
        for (String name : fileNames) {
            Uri uri = Uri.fromFile(new File(recordPath + "/" + name));
            cover = new Cover("qasim_123",R.drawable.image,name.substring(11),"Artist","6 : 33","567",R.drawable.thumbnail,uri);
            coverList.add(cover);
        }

        //END OF UI TEST CODE

        MyHomeAdapter myAdapter = new MyHomeAdapter(getActivity(),coverList);
        coversHomeView.setAdapter(myAdapter);


        return root;
    }



}