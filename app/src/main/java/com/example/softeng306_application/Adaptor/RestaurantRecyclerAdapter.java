package com.example.softeng306_application.Adaptor;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.CategoryType;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.R;
import com.google.android.material.card.MaterialCardView;
import com.example.softeng306_application.ViewModel.ListViewModel;

import java.util.List;

public class RestaurantRecyclerAdapter extends RecyclerView.Adapter<RestaurantRecyclerAdapter.RestaurantViewHolder> {
    Context context;
    private ListViewModel listViewModel;
    private List<Restaurant> restaurants;
    private List<Category> categoryList;

    // TODO: instead of a list of restaurants as an argument to the constructor, it should take in a category type THEN populate a list of restaurants belonging to this type.
    public RestaurantRecyclerAdapter(Context context, List<Restaurant> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
//        this.category = category;

    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestaurantViewHolder(LayoutInflater.from(context).inflate(R.layout.restaurant_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RestaurantRecyclerAdapter.RestaurantViewHolder holder, int position) {
        holder.restaurantName.setText(restaurants.get(position).getName());
        holder.price.setText(restaurants.get(position).getPrice());
        holder.logoImage.setImageResource(showImage(restaurants.get(position)));

        // TODO: handle condition for favourite or not
        holder.favouriteHeart.setVisibility(View.VISIBLE);
    }

    private int showImage(Restaurant restaurant) {
        int i = context.getResources().getIdentifier(restaurant.getLogoImage(), "drawable", context.getPackageName());
        return i;
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{
        ImageView logoImage, favouriteHeart;
        TextView restaurantName, price;
        MaterialCardView restaurantItem;
        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            logoImage = itemView.findViewById(R.id.img_logo);
            restaurantName = itemView.findViewById(R.id.txt_restaurant_name);
            price = itemView.findViewById(R.id.txt_price);
            favouriteHeart = itemView.findViewById(R.id.img_favourite);
            restaurantItem = itemView.findViewById(R.id.mtrlcardview_restaurant);
        }

    }
}
