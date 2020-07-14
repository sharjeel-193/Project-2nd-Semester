package com.selflearning.starcover.ui.sing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.selflearning.starcover.Logic.Instrumental;
import com.selflearning.starcover.MainActivity;
import com.selflearning.starcover.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

     public class    SingFragment extends Fragment {

    private SingViewModel singViewModel;
    private RecyclerView instrumentalsView;
    List<Instrumental> instrumentalList;
    Instrumental instrumental;
    private StorageReference mStorageRef;

    List<String> songNames;
    List<String> songUrls;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        singViewModel = ViewModelProviders.of(this).get(SingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sing,container,false);

        MainActivity main = (MainActivity) getActivity();
        songNames = main.sendNames();
        songUrls = main.sendUrls();

        instrumentalsView =(RecyclerView) root.findViewById(R.id.sing_recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        instrumentalsView.setLayoutManager(layoutManager);

        instrumentalsView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        instrumentalList = new ArrayList<>();
        Log.d("Qasim", "Size: " + songUrls.size());
        for(int i = 0; i < songNames.size(); i++) {
            String url = "";
            if (i >= songUrls.size())
                url = null;
            else
                url = songUrls.get(i);
            if (i % 2 == 0 )
                instrumentalList.add(new Instrumental(songNames.get(i), "Artist",
                        R.drawable.thumbnail, 23, url));

            else
                instrumentalList.add(new Instrumental(songNames.get(i), "Artist",
                        R.drawable.thumbnail3, 67, url));

        }

        final MySingAdapter adapter = new MySingAdapter(getActivity(),instrumentalList);
        instrumentalsView.setAdapter(adapter);

        SearchView searchView = root.findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return root;
    }


     }