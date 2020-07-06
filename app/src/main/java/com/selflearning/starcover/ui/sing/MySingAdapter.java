package com.selflearning.starcover.ui.sing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selflearning.starcover.Logic.Instrumental;
import com.selflearning.starcover.R;

import java.util.ArrayList;
import java.util.List;

public class MySingAdapter extends RecyclerView.Adapter<SingViewHolder> implements Filterable {

    private Context context;
    private List<Instrumental> instrumentalList;
    private List<Instrumental> filteredInstrumentalList;

    public MySingAdapter(Context context, List<Instrumental> instrumentalList) {
        this.context = context;
        this.instrumentalList = instrumentalList;
    }
    @NonNull
    @Override
    public SingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sing_recycler_item,parent,false );
        return new SingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingViewHolder holder, int position) {

        holder.instrumentalThumbnai.setImageResource(instrumentalList.get(position).getInstrumentalImageId());
        holder.instrumentalName.setText(instrumentalList.get(position).getSongName());
        holder.instrumentalArtist.setText(instrumentalList.get(position).getArtistName());
        holder.instrumentalDuration.setText(String.valueOf(instrumentalList.get(position).getDuration()));

    }

    @Override
    public int getItemCount() {
        return instrumentalList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Instrumental> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0){
                    filteredList.addAll(instrumentalList);
                } else {
                    String fliterPattern = constraint.toString().toLowerCase().trim();
                    for (Instrumental instrumental: instrumentalList){
                        if (instrumental.getSongName().toLowerCase().contains(fliterPattern)){
                            filteredList.add(instrumental);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                instrumentalList.clear();
                instrumentalList.addAll((List) filterResults.values);
                notifyDataSetChanged();

            }
        };
    }
}
class SingViewHolder extends RecyclerView.ViewHolder{

    ImageView instrumentalThumbnai;
    TextView instrumentalName, instrumentalArtist, instrumentalDuration;
    LinearLayout layout;

    public SingViewHolder(@NonNull View itemView) {
        super(itemView);

        instrumentalThumbnai = (ImageView) itemView.findViewById(R.id.search_instrumental_thumbanil);
        instrumentalName = (TextView) itemView.findViewById(R.id.search_instrumental_name);
        instrumentalArtist = (TextView) itemView.findViewById(R.id.search_instrumental_artist);
        instrumentalDuration = (TextView) itemView.findViewById(R.id.search_instrumental_duration);

    }
}
