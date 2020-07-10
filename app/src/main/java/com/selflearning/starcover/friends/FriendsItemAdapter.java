package com.selflearning.starcover.friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selflearning.starcover.Logic.UserProfile;
import com.selflearning.starcover.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsItemAdapter extends RecyclerView.Adapter<FriendsViewHolder> implements Filterable {

    private Context context;
    private List<UserProfile> friendsList;
    boolean isfollowing = true;

    public FriendsItemAdapter(Context context, List<UserProfile> friendsList) {
        this.context = context;
        this.friendsList = friendsList;
    }

    @Override
    public FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_recycler_item,parent,false );
        return new FriendsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsViewHolder holder, int position) {
        holder.friendsDp.setImageResource(friendsList.get(position).getUserDpId());
        holder.friendsId.setText(friendsList.get(position).getUserId());
        holder.friendsName.setText(friendsList.get(position).getUserName());
        if (isfollowing){
            holder.friendsStatus.setText("Following");
        } else {
            holder.friendsStatus.setText("Follow");
        }


    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                return null;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            }
        };
    }
}
class FriendsViewHolder extends RecyclerView.ViewHolder{

    CircleImageView friendsDp;
    TextView friendsId, friendsName;
    Button friendsStatus;
    LinearLayout layout;

    public FriendsViewHolder(@NonNull View itemView) {
        super(itemView);

        friendsDp = itemView.findViewById(R.id.friends_dp);
        friendsId = itemView.findViewById(R.id.friends_id);
        friendsName = itemView.findViewById(R.id.friends_username);
        friendsStatus = itemView.findViewById(R.id.friends_status_button);
        layout = itemView.findViewById(R.id.friends_recycler_item_layout);
    }
}
