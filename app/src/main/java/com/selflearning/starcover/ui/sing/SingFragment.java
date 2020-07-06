package com.selflearning.starcover.ui.sing;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.selflearning.starcover.Logic.Instrumental;
import com.selflearning.starcover.R;
import com.selflearning.starcover.ui.profile.ProfileViewModel;

import java.util.ArrayList;
import java.util.List;

public class SingFragment extends Fragment {

    private SingViewModel singViewModel;
    private RecyclerView instrumentalsView;
    List<Instrumental> instrumentalList;
    Instrumental instrumental;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        singViewModel = ViewModelProviders.of(this).get(SingViewModel.class);
        View root = inflater.inflate(R.layout.sing_fragment,container,false);

        instrumentalsView =(RecyclerView) root.findViewById(R.id.sing_recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        instrumentalsView.setLayoutManager(layoutManager);

        instrumentalsView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        instrumentalList = new ArrayList<>();

        instrumental = new Instrumental("ab Song","Artist",R.drawable.thumbnail,23);
        instrumentalList.add(instrumental);
        instrumental = new Instrumental("bc song","Artist",R.drawable.thumbnail3,67);
        instrumentalList.add(instrumental);
        instrumental = new Instrumental("cd Song","Artist",R.drawable.thumbnail,23);
        instrumentalList.add(instrumental);
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
        instrumentalList.add(instrumental);

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