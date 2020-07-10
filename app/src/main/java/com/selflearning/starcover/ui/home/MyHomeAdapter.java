package com.selflearning.starcover.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyHomeAdapter extends RecyclerView.Adapter<HomeViewHolder>{

    private Context context;
    private List<Cover> coverList;

    public MyHomeAdapter(Context context, List<Cover> coverList) {
        this.context = context;
        this.coverList = coverList;
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_cover_item,parent,false );
        return new HomeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {

        //ORIGINAL CODE

//        holder.userDp.setImageResource(coverList.get(position).getUser().getUserDpId());
//        holder.userId.setText(coverList.get(position).getUser().getUserId());
//        holder.songName.setText(coverList.get(position).getInstrumental().getSongName());
//        holder.artistName.setText(coverList.get(position).getInstrumental().getArtistName());
//        holder.coverDuration.setText(coverList.get(position).getDuration());
//        holder.coverLikes.setText(coverList.get(position).getCoverLikes());
//        holder.thumbnail.setImageResource(coverList.get(position).getInstrumental().getInstrumentalImageId());

        //TEST CODE FOR UI

        holder.userDp.setImageResource(coverList.get(position).getUserDp());
        holder.userId.setText(coverList.get(position).getUserId());
        holder.songName.setText(coverList.get(position).getCoverName());
        holder.artistName.setText(coverList.get(position).getCoverArtist());
        holder.coverDuration.setText(coverList.get(position).getCoverDuration());
        holder.coverLikes.setText(coverList.get(position).getCoverLikes());
        holder.thumbnail.setImageResource(coverList.get(position).getCoverThumbnail());




    }

    @Override
    public int getItemCount() {
        return coverList.size();
    }
}
 class HomeViewHolder extends RecyclerView.ViewHolder{

    CircleImageView userDp;
    TextView userId;
    TextView songName;
    TextView artistName;
    TextView coverDuration;
    ImageView thumbnail;
    TextView coverLikes;
    CardView cardView;

     public HomeViewHolder(@NonNull final View itemView) {
         super(itemView);
         userDp = (CircleImageView) itemView.findViewById(R.id.user_dp);
         userId = (TextView) itemView.findViewById(R.id.user_id);
         songName = (TextView) itemView.findViewById(R.id.cover_instrumental_name);
         artistName = (TextView) itemView.findViewById(R.id.cover_instrumental_artist);
         coverDuration = (TextView) itemView.findViewById(R.id.cover_instrumental_duration);
         thumbnail = (ImageView) itemView.findViewById(R.id.cover_thumbnail);
         coverLikes = (TextView) itemView.findViewById(R.id.user_cover_likes);
         cardView = (CardView) itemView.findViewById(R.id.home_cover_card);

         cardView.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View view) {
                 PopupMenu popupMenu = new PopupMenu(cardView.getContext(),itemView);
                 popupMenu.getMenu().add("Save");
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
