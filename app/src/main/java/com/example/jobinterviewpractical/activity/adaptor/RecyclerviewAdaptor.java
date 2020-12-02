package com.example.jobinterviewpractical.activity.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jobinterviewpractical.R;
import com.example.jobinterviewpractical.activity.model.DetailBean;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerviewAdaptor extends RecyclerView.Adapter<RecyclerviewAdaptor.ViewHolder> implements Filterable {

    private ArrayList<DetailBean.search> searches = new ArrayList<>();
    private ArrayList<DetailBean.search> searchesorigin = new ArrayList<>();

    private Context mcontext;

    public RecyclerviewAdaptor(Context mcontext, ArrayList<DetailBean.search> movielist) {
        searches = movielist;
        searchesorigin = movielist;
        this.mcontext = mcontext;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                ArrayList<DetailBean.search> searchlist = new ArrayList<>();
                if (charString.isEmpty()) {
                    searchlist = searchesorigin;
                } else {
                    for (DetailBean.search row : searchesorigin) {
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            searchlist.add(row);
                        }
                    }


                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = searchlist;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults
                    filterResults) {

                searches = (ArrayList<DetailBean.search>) filterResults.values;
                notifyDataSetChanged();
            }


        };
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.recylelayout, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mcontext)
                .load(searches.get(position).getPoster())
                .into(holder.circle_imagenew);
        holder.txt_moviename.setText(searches.get(position).getTitle());
        holder.txt_movieyear.setText(searches.get(position).getYear());

    }

    @Override
    public int getItemCount() {
        return searches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView circle_imagenew;
        private TextView txt_moviename;
        private TextView txt_movieyear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circle_imagenew = itemView.findViewById(R.id.circle_imagenew);
            txt_moviename = itemView.findViewById(R.id.txt_moviename);
            txt_movieyear = itemView.findViewById(R.id.txt_movieyear);
        }
    }
}

