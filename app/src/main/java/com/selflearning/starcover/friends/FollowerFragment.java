package com.selflearning.starcover.friends;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.selflearning.starcover.Logic.UserProfile;
import com.selflearning.starcover.R;

import java.util.ArrayList;
import java.util.List;


public class FollowerFragment extends Fragment {

    private RecyclerView recyclerView;
    List<UserProfile> followersList;
    UserProfile follower;

    public FollowerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_follower, container, false);

        recyclerView = root.findViewById(R.id.follwers_recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        followersList = new ArrayList<>();

        follower = new UserProfile("qasim_123","Qasim Khan",R.drawable.image);
        followersList.add(follower);
        follower = new UserProfile("sharjeel.4u","M. sharjeel Maqsood",R.drawable.image2);
        followersList.add(follower);

        final FriendsItemAdapter adapter = new FriendsItemAdapter(getActivity(),followersList);
        recyclerView.setAdapter(adapter);

        SearchView searchView = root.findViewById(R.id.follower_search);
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