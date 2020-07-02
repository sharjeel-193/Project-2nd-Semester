package com.selflearning.starcover.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.selflearning.starcover.Logic.Notification;
import com.selflearning.starcover.R;

import java.util.List;

public class MyNotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder>{

    private Context context;
    private List<Notification> notificationsList;

    public MyNotificationAdapter(Context context, List<Notification> notificationsList) {
        this.context = context;
        this.notificationsList = notificationsList;
    }


    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_recycler_item,parent,false );
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {

        holder.thumbnail.setImageResource(notificationsList.get(position).getThumbnailId());
        holder.text.setText(notificationsList.get(position).getText());
        holder.heading.setText(notificationsList.get(position).getHeading());

    }

    @Override
    public int getItemCount() {
        return notificationsList.size();
    }
}

class NotificationViewHolder extends RecyclerView.ViewHolder{

    ImageView thumbnail;
    TextView heading;
    TextView text;
    CardView card;

    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);

        thumbnail = (ImageView) itemView.findViewById(R.id.notification_thumbnail);
        heading = (TextView) itemView.findViewById(R.id.notification_heading);
        text = (TextView) itemView.findViewById(R.id.notification_text);
        card = (CardView) itemView.findViewById(R.id.notification_card);

    }
}
