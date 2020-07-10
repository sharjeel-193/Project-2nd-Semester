package com.selflearning.starcover.friends;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FriendsTabsAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;
    public FriendsTabsAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numOfTabs = behavior;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FollowerFragment();
            case 1:
                return new FollowingFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
