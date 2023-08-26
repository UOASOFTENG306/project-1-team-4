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

public class RandomRecylerAdapter extends RecyclerView.Adapter<RandomRecylerAdapter.RandomViewHolder> {
    Context context;
    private List<Restaurant> randomList = new ArrayList<>();

    public RandomRecylerAdapter(Context context) {
        this.context = context;
    }

    public void setRandmoList(List<Restaurant> randomList) {
        this.randomList = randomList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RandomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomViewHolder(LayoutInflater.from(context).inflate(R.layout.random_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RandomRecylerAdapter.RandomViewHolder holder, int position) {
        Restaurant restaurant = randomList.get(position);
        String colourHex = randomList.get(position).getCategory().getBorderColour();
        holder.restaurantName.setText(randomList.get(position).getName());
        holder.logoImage.setImageResource(showImage(randomList.get(position)));
        holder.randomCardView.setStrokeColor(Color.parseColor(colourHex));
        holder.backgroundImage.setImageResource(showImageBackground(randomList.get(position)));
        holder.itemView.setOnClickListener(v -> onRestaurantClick(restaurant));
    }

    private int showImage(Restaurant restaurant) {
        int i = context.getResources().getIdentifier(restaurant.getLogoImage(), "drawable", context.getPackageName());
        return i;
    }

    private int showImageBackground(Restaurant restaurant) {
        int i = context.getResources().getIdentifier("back" + restaurant.getRestaurantID()+ "_1", "drawable", context.getPackageName());
        return i;
    }

    @Override
    public int getItemCount() {
        return randomList.size();
    }

    private void onRestaurantClick(Restaurant restaurant){
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("RESTAURANT", restaurant);
        context.startActivity(intent);
    }

    public class RandomViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantName;
        ImageView logoImage, backgroundImage;
        MaterialCardView randomCardView;

        public RandomViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName =  itemView.findViewById(R.id.textview_random);
            logoImage =  itemView.findViewById(R.id.imgview_main_logo);
            randomCardView = itemView.findViewById(R.id.mtrlcardview_random);
            backgroundImage = itemView.findViewById(R.id.img_bg);
        }

    }
}