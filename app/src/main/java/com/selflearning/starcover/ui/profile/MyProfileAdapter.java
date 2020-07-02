package com.selflearning.starcover.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.selflearning.starcover.Logic.Cover;
import com.selflearning.starcover.R;

import java.util.List;

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
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {

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


    public ProfileViewHolder(@NonNull View itemView) {
        super(itemView);

        coverThumbnail = (ImageView) itemView.findViewById(R.id.profile_cover_thumbnail);
        coverName = (TextView) itemView.findViewById(R.id.profile_cover_song);
        coverArtist = (TextView) itemView.findViewById(R.id.profile_cover_artist);
        coverDuration = (TextView) itemView.findViewById(R.id.profile_cover_duration);
        coverLikes = (TextView) itemView.findViewById(R.id.profile_cover_likes);
        cardView = (CardView) itemView.findViewById(R.id.profile_cover_card);

    }
}
