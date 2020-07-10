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

public class FollowingFragment extends Fragment {

    private RecyclerView recyclerView;
    List<UserProfile> followingist;
    UserProfile following;

    public FollowingFragment() {
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
        View root = inflater.inflate(R.layout.fragment_following, container, false);
        recyclerView = root.findViewById(R.id.following_recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        followingist = new ArrayList<>();

        following = new UserProfile("qasim_123","Qasim Khan",R.drawable.image);
        followingist.add(following);
        following = new UserProfile("sharjeel.4u","M. sharjeel Maqsood",R.drawable.image2);
        followingist.add(following);

        final FriendsItemAdapter adapter = new FriendsItemAdapter(getActivity(),followingist);
        recyclerView.setAdapter(adapter);

        SearchView searchView = root.findViewById(R.id.following_search);
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