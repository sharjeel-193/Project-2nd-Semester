package com.selflearning.starcover.ui.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.selflearning.starcover.Logic.Cover;
import com.selflearning.starcover.R;

import java.io.IOException;
import java.util.List;

public class MyProfileAdapter extends RecyclerView.Adapter<ProfileViewHolder>{
     boolean isPlaying = false;
    private Context context;
    private List<Cover> coverList;
    MediaPlayer mplayer;

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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final ProfileViewHolder holder, final int position) {

        holder.coverThumbnail.setImageResource(R.drawable.thumbnail3);
        holder.coverName.setText(coverList.get(position).getCoverName());
        holder.coverArtist.setText(coverList.get(position).getCoverArtist());
        holder.coverDuration.setText(coverList.get(position).getCoverDuration());
        holder.coverLikes.setText(coverList.get(position).getCoverLikes());
        mplayer = new MediaPlayer();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Uri uri = coverList.get(position).getUri();
                if (isPlaying) {
                    // mplayer.stop();
                    mplayer.reset();
                    isPlaying = false;
                }
                else{
                    try {
                        mplayer.setDataSource(String.valueOf(uri));
                        mplayer.prepare();
                        mplayer.start();
                        isPlaying = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
               // isPlaying = !isPlaying;
                }

        });
       /* holder.cardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {



                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    mplayer.stop();

                }

                return false;
            }
        });*/

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


    public ProfileViewHolder(@NonNull final View itemView) {
        super(itemView);

        coverThumbnail = (ImageView) itemView.findViewById(R.id.profile_cover_thumbnail);
        coverName = (TextView) itemView.findViewById(R.id.profile_cover_song);
        coverArtist = (TextView) itemView.findViewById(R.id.profile_cover_artist);
        coverDuration = (TextView) itemView.findViewById(R.id.profile_cover_duration);
        coverLikes = (TextView) itemView.findViewById(R.id.profile_cover_likes);
        cardView = (CardView) itemView.findViewById(R.id.profile_cover_card);

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
