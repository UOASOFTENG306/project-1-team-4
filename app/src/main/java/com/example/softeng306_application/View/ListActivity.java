package com.example.softeng306_application.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.softeng306_application.Adaptor.CategoryRecyclerAdapter;
import com.example.softeng306_application.Adaptor.RestaurantRecyclerAdapter;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.ListViewModel;
import com.example.softeng306_application.ViewModel.MainViewModel;

public class ListActivity extends AppCompatActivity implements Activity  {

    // TODO: delete; for testing purposes, just to see if restaurant recycler view gets populated.
    private MainViewModel mainViewModel;
    private ListViewModel listViewModel;
    private RestaurantRecyclerAdapter restaurantAdapter;

    private class ViewHolder {
        RecyclerView restaurantRecyclerView;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ViewHolder vh = new ViewHolder();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);


        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        Intent intent = getIntent();
        if (intent != null) {
            Category category = intent.getParcelableExtra("CATEGORY");
            listViewModel.setCategory(category);
        }

        // Bind RestaurantRecyclerAdapter
        vh.restaurantRecyclerView = findViewById(R.id.recview_restaurant_list);
        restaurantAdapter = new RestaurantRecyclerAdapter(this, mainViewModel.getTopRatedRestaurants());
        vh.restaurantRecyclerView.setAdapter(restaurantAdapter);

        // Set Vertical Layout Manager for categoryRecyclerView
        LinearLayoutManager verticalLayout = new LinearLayoutManager(ListActivity.this, LinearLayoutManager.VERTICAL, false);
        vh.restaurantRecyclerView.setLayoutManager(verticalLayout);
    }
}