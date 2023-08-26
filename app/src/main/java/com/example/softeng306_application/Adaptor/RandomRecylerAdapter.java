package com.example.softeng306_application.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.R;
import com.example.softeng306_application.View.DetailsActivity;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class RandomRecylerAdapter extends RecyclerView.Adapter<RandomRecylerAdapter.TopRatedViewHolder> {
    Context context;
    private List<Restaurant> randmoList = new ArrayList<>();

    public RandomRecylerAdapter(Context context) {
        this.context = context;
    }

    public void setRandmoList(List<Restaurant> randmoList) {
        this.randmoList = randmoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopRatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TopRatedViewHolder(LayoutInflater.from(context).inflate(R.layout.random_restaurant_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RandomRecylerAdapter.TopRatedViewHolder holder, int position) {
        Restaurant restaurant = randmoList.get(position);
        String colourHex = randmoList.get(position).getCategory().getBorderColour();
        holder.restaurantName.setText(randmoList.get(position).getName());
        holder.logoImage.setImageResource(showImage(randmoList.get(position)));
        holder.topratedCardview.setStrokeColor(Color.parseColor(colourHex));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRestaurantClick(restaurant);
            }
        });
    }

    private int showImage(Restaurant restaurant) {
        int i = context.getResources().getIdentifier(restaurant.getLogoImage(), "drawable", context.getPackageName());
        return i;
    }

    @Override
    public int getItemCount() {
        return randmoList.size();
    }

    private void onRestaurantClick(Restaurant restaurant){
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("RESTAURANT", restaurant);
        context.startActivity(intent);
    }

    public class TopRatedViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantName;
        ImageView logoImage;
        MaterialCardView topratedCardview;
        public TopRatedViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName =  itemView.findViewById(R.id.textview_random);
            logoImage =  itemView.findViewById(R.id.imgview_main_logo);
            topratedCardview = itemView.findViewById(R.id.mtrlcardview_random);
        }

    }
}