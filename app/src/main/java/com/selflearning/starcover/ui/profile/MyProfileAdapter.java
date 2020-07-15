package com.selflearning.starcover.ui.profile;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.selflearning.starcover.Logic.Cover;
import com.selflearning.starcover.R;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class MyProfileAdapter extends RecyclerView.Adapter<ProfileViewHolder>{

    private Context context;
    private List<Cover> coverList;

    public MyProfileAdapter(Context context, List<Cover> coverList) {
        this.context = context;
        this.coverList = coverList;
    }


    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_recycler_cover_item,parent,false );
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileViewHolder holder, int position) {

        holder.coverThumbnail.setImageResource(R.drawable.thumbnail3);
        holder.coverName.setText(coverList.get(position).getCoverName());
        holder.coverArtist.setText(coverList.get(position).getCoverArtist());
        holder.coverDuration.setText(coverList.get(position).getCoverDuration());
        holder.coverLikes.setText(coverList.get(position).getCoverLikes());

    }

    @Override
    public int getItemCount() {
        return coverList.size();
    }
}

class ProfileViewHolder extends RecyclerView.ViewHolder{

    ImageView coverThumbnail;
    TextView coverName, coverArtist, coverDuration, coverLikes;
    CardView cardView;
    ImageView ppButton;
    MediaPlayer mplayer;

    public ProfileViewHolder(@NonNull final View itemView) {
        super(itemView);

        coverThumbnail = (ImageView) itemView.findViewById(R.id.profile_cover_thumbnail);
        coverName = (TextView) itemView.findViewById(R.id.profile_cover_song);
        coverArtist = (TextView) itemView.findViewById(R.id.profile_cover_artist);
        coverDuration = (TextView) itemView.findViewById(R.id.profile_cover_duration);
        coverLikes = (TextView) itemView.findViewById(R.id.profile_cover_likes);
        cardView = (CardView) itemView.findViewById(R.id.profile_cover_card);
        ppButton = (ImageView) itemView.findViewById(R.id.profile_player_button);


        ppButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mplayer = new MediaPlayer();

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    try {
                        mplayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/starcover.appspot.com/o/music%2FChad_Crouch_-_Shipping_Lanes.mp3?alt=media&token=eebc3e10-d16f-45f3-960b-a469c93c1e5e");
                        mplayer.prepare();
                        mplayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    mplayer.stop();

                }

                return false;


            }
        });
        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(cardView.getContext(),itemView);
                popupMenu.getMenuInflater().inflate(R.menu.profile_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        return false;
                    }
                });
                popupMenu.show();
                return true;
            }
        });

    }


}
