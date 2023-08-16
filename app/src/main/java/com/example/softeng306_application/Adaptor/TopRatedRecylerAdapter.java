package com.example.softeng306_application.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Model.TopRated;
import com.example.softeng306_application.R;

import java.util.List;

public class TopRatedRecylerAdapter extends RecyclerView.Adapter<TopRatedRecylerAdapter.TopRatedViewHolder> {
    Context context;
    private List<Restaurant> topRatedList;

    public TopRatedRecylerAdapter(Context context, List<Restaurant> topRatedList) {
        this.context = context;
        this.topRatedList = topRatedList;
    }

    @NonNull
    @Override
    public TopRatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TopRatedViewHolder(LayoutInflater.from(context).inflate(R.layout.top_rated_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TopRatedRecylerAdapter.TopRatedViewHolder holder, int position) {
        holder.topRatedImage.setText(topRatedList.get(position).getLogoImage());
    }

    @Override
    public int getItemCount() {
        return topRatedList.size();
    }

    public class TopRatedViewHolder extends RecyclerView.ViewHolder {
        TextView topRatedImage;

        public TopRatedViewHolder(@NonNull View itemView) {
            super(itemView);
            topRatedImage = (TextView) itemView.findViewById(R.id.textview_top_rated);
        }

    }
}
