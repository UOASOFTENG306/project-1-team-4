package com.example.softeng306_application.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.softeng306_application.Adaptor.CategoryRecyclerAdapter;
import com.example.softeng306_application.Adaptor.RestaurantRecyclerAdapter;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.ListViewModel;
import com.example.softeng306_application.ViewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements Activity  {

    // TODO: delete; for testing purposes, just to see if restaurant recycler view gets populated.
    private MainViewModel mainViewModel;
    private ListViewModel listViewModel;
    private RestaurantRecyclerAdapter restaurantAdapter;

    private class ViewHolder {
        RecyclerView restaurantRecyclerView;
        Button backButton;
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
            List<Category> categoryList = new ArrayList<Category>();
            Category category = intent.getParcelableExtra("CATEGORY");
            categoryList.add(category);
            listViewModel.setCategory(categoryList);
        }

        // Bind RestaurantRecyclerAdapter
        vh.restaurantRecyclerView = findViewById(R.id.recview_restaurant_list);
        restaurantAdapter = new RestaurantRecyclerAdapter(this, listViewModel.getRestaurantsTest());
        vh.restaurantRecyclerView.setAdapter(restaurantAdapter);

        // Set Vertical Layout Manager for categoryRecyclerView
        LinearLayoutManager verticalLayout = new LinearLayoutManager(ListActivity.this, LinearLayoutManager.VERTICAL, false);
        vh.restaurantRecyclerView.setLayoutManager(verticalLayout);


        vh.backButton = findViewById(R.id.btn_back);
        vh.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainActivity(v);
            }
        });
    }

    private void showMainActivity(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

}