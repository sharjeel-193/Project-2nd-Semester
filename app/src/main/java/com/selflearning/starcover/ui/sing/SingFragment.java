package com.selflearning.starcover.ui.sing;

import androidx.lifecycle.ViewModelProviders;

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
import com.selflearning.starcover.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SingFragment extends Fragment {

    private SingViewModel singViewModel;
    private RecyclerView instrumentalsView;
    List<Instrumental> instrumentalList;
    Instrumental instrumental;
    private StorageReference mStorageRef;

    List<String> songNames = new ArrayList<>();
    List<String> songUrls = new ArrayList<>();

    public void onCreate() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        singViewModel = ViewModelProviders.of(this).get(SingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sing,container,false);

        mStorageRef = FirebaseStorage.getInstance().getReference("music");
        mStorageRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference prefix : listResult.getPrefixes()) {
                            // All the prefixes under listRef.
                            // You may call listAll() recursively on them.
                        }

                        for (StorageReference item : listResult.getItems()) {
                            // All the items under listRef.
                            Log.d("Qasim", item.getName());
                            songNames.add(item.getName());
                            item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    songUrls.add(uri.toString());
                                }
                            });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Uh-oh, an error occurred!
                    }
                });

        Log.d("Qasim1", String.valueOf(songNames.size()));
        instrumentalsView =(RecyclerView) root.findViewById(R.id.sing_recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        instrumentalsView.setLayoutManager(layoutManager);

        instrumentalsView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        instrumentalList = new ArrayList<>();


        for(int i = 0; i < songNames.size(); i++) {
            if (i % 2 == 0 ) {
                instrumentalList.add(new Instrumental(songNames.get(i), "Artist",
                        R.drawable.thumbnail, 23, songUrls.get(i)));
            } else {
                instrumentalList.add(new Instrumental(songNames.get(i), "Artist",
                        R.drawable.thumbnail3, 67, songUrls.get(i)));
            }

        }
        Log.d("Qasim", String.valueOf(songNames.size()));
        Log.d("Qasim", "Hi");
        instrumental = new Instrumental("ab Song","Artist",R.drawable.thumbnail,23, "www,gooog.eom");
        instrumentalList.add(instrumental);
        instrumental = new Instrumental("bc song","Artist",R.drawable.thumbnail3,67, "www,gooog.eom");
        instrumentalList.add(instrumental);
        instrumental = new Instrumental("cd Song","Artist",R.drawable.thumbnail,23, "www,gooog.eom");
        /*instrumentalList.add(instrumental);
        instrumental = new Instrumental("de song","Artist",R.drawable.thumbnail3,67);
        instrumentalList.add(instrumental);
        instrumental = new Instrumental("df Song","Artist",R.drawable.thumbnail,23);
        instrumentalList.add(instrumental);
        instrumental = new Instrumental("fg song","Artist",R.drawable.thumbnail3,67);
        instrumentalList.add(instrumental);
        instrumental = new Instrumental("ag Song","Artist",R.drawable.thumbnail,23);
        instrumentalList.add(instrumental);
        instrumental = new Instrumental("bg song","Artist",R.drawable.thumbnail3,67);
        instrumentalList.add(instrumental);
        instrumental = new Instrumental("cd Song","Artist",R.drawable.thumbnail,23);
        instrumentalList.add(instrumental);
        instrumental = new Instrumental("do song","Artist",R.drawable.thumbnail3,67);
        instrumentalList.add(instrumental);
        instrumental = new Instrumental("dz Song","Artist",R.drawable.thumbnail,23);
        instrumentalList.add(instrumental);
        instrumental = new Instrumental("gf song","Artist",R.drawable.thumbnail3,67);
        instrumentalList.add(instrumental);*/

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