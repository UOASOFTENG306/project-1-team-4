package com.example.softeng306_application.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softeng306_application.Adaptor.CategoryDropdownAdapter;
import com.example.softeng306_application.Adaptor.CategoryRecyclerAdapter;
import com.example.softeng306_application.Adaptor.RestaurantRecyclerAdapter;
import com.example.softeng306_application.Entity.Asian;
import com.example.softeng306_application.Entity.Cafe;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.European;
import com.example.softeng306_application.Entity.FastFood;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.ListViewModel;
import com.example.softeng306_application.ViewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements Activity  {

    // TODO: delete; for testing purposes
//    String[] categories = {"FAST-FOOD", "EUROPEAN", "ASIAN", "CAFE"};
    private MainViewModel mainViewModel;
    private ListViewModel listViewModel;
    private RestaurantRecyclerAdapter restaurantAdapter;
    private CategoryDropdownAdapter adapterItems;

    private class ViewHolder {
        AutoCompleteTextView autoCompleteTextView;
        TextView emptyListText;
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


        vh.autoCompleteTextView = findViewById(R.id.dropdown_category);
        vh.restaurantRecyclerView = findViewById(R.id.recview_restaurant_list);
        vh.backButton = findViewById(R.id.btn_back);
        vh.emptyListText = findViewById(R.id.txt_emptyList);
        vh.autoCompleteTextView = findViewById(R.id.dropdown_category);

        Intent intent = getIntent();
        if (intent != null) {
            Category category = intent.getParcelableExtra("CATEGORY");
            listViewModel.setCategory(category);
            vh.autoCompleteTextView.setText(category.getCategoryType(), false);
        }

        // Bind RestaurantAdapter
        adapterItems = new CategoryDropdownAdapter(this, R.layout.dropdown_list_item, listViewModel.getAllCategories());
        vh.autoCompleteTextView.setAdapter(adapterItems);

        // Bind RestaurantRecyclerAdapter
        restaurantAdapter = new RestaurantRecyclerAdapter(this, listViewModel.getRestaurantsTest());
        vh.restaurantRecyclerView.setAdapter(restaurantAdapter);

        // Set Vertical Layout Manager for categoryRecyclerView
        LinearLayoutManager verticalLayout = new LinearLayoutManager(ListActivity.this, LinearLayoutManager.VERTICAL, false);
        vh.restaurantRecyclerView.setLayoutManager(verticalLayout);


        vh.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainActivity(v);
            }
        });

        listViewModel.getRestaurantList().observe(this, restaurants -> {
            // Update the adapter with the new list of items
            restaurantAdapter.setRestaurants(restaurants);
        });

        listViewModel.getEmptyMessageVisibility().observe(this, visibility -> {
            vh.emptyListText.setVisibility(visibility);
        });

        vh.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Category selectedCategory = (Category) adapterView.getItemAtPosition(i);
                vh.autoCompleteTextView.setText(selectedCategory.getCategoryType(), false);
                // Set selected category
                listViewModel.setCategory(selectedCategory);
                restaurantAdapter.setRestaurants(listViewModel.getRestaurantsTest());
            }
        });

    }


    private void showMainActivity(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

}