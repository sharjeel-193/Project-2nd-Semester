package com.selflearning.starcover.ui.notifications;

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

import com.selflearning.starcover.Logic.Notification;
import com.selflearning.starcover.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private RecyclerView notificationsView;
    List<Notification> notificationsList;
    Notification notification;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        notificationsView = (RecyclerView) root.findViewById(R.id.notification_recycler_iew);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(),1);
        notificationsView.setLayoutManager(manager);

        notificationsList = new ArrayList<>();

//        final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        //UI TESTING CODE

        notification = new Notification("New Follower","A person Followed You",R.drawable.image);
        notificationsList.add(notification);
        notification = new Notification("New Like","Now your likes get to the number of 200",R.drawable.image2);
        notificationsList.add(notification);
        notification = new Notification("New Follower","A person Followed You",R.drawable.image);
        notificationsList.add(notification);
        notification = new Notification("New Like","Now your likes get to the number of 200",R.drawable.image2);
        notificationsList.add(notification);
        notification = new Notification("New Follower","A person Followed You",R.drawable.image);
        notificationsList.add(notification);
        notification = new Notification("New Like","Now your likes get to the number of 200",R.drawable.image2);
        notificationsList.add(notification);
        notification = new Notification("New Follower","A person Followed You",R.drawable.image);
        notificationsList.add(notification);
        notification = new Notification("New Like","Now your likes get to the number of 200",R.drawable.image2);
        notificationsList.add(notification);
        notification = new Notification("New Follower","A person Followed You",R.drawable.image);
        notificationsList.add(notification);
        notification = new Notification("New Like","Now your likes get to the number of 200",R.drawable.image2);
        notificationsList.add(notification);
        notification = new Notification("New Follower","A person Followed You",R.drawable.image);
        notificationsList.add(notification);
        notification = new Notification("New Like","Now your likes get to the number of 200",R.drawable.image2);
        notificationsList.add(notification);
        notification = new Notification("New Follower","A person Followed You",R.drawable.image);
        notificationsList.add(notification);
        notification = new Notification("New Like","Now your likes get to the number of 200",R.drawable.image2);
        notificationsList.add(notification);
        notification = new Notification("New Follower","A person Followed You",R.drawable.image);
        notificationsList.add(notification);
        notification = new Notification("New Like","Now your likes get to the number of 200",R.drawable.image2);
        notificationsList.add(notification);
        notification = new Notification("New Follower","A person Followed You",R.drawable.image);
        notificationsList.add(notification);
        notification = new Notification("New Like","Now your likes get to the number of 200",R.drawable.image2);
        notificationsList.add(notification);


        //END OF TEST CODE

        MyNotificationAdapter adapter = new MyNotificationAdapter(getActivity(),notificationsList);
        notificationsView.setAdapter(adapter);

        return root;
    }
}