package com.example.softeng306_application.Adaptor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softeng306_application.Entity.Restaurant;
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
        holder.restaurantName.setText(topRatedList.get(position).getName());
        holder.logoImage.setImageResource(topRatedList.get(position).getLogoImageUrl());
    }

    @Override
    public int getItemCount() {
        return topRatedList.size();
    }

    public class TopRatedViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantName;
        ImageView logoImage;

        public TopRatedViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = (TextView) itemView.findViewById(R.id.textview_top_rated);
            logoImage = (ImageView) itemView.findViewById(R.id.imgview_main_logo);
        }

    }
}
