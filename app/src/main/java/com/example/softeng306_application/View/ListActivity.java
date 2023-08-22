package com.example.softeng306_application.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.ImageButton;
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
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.ListViewModel;
import com.example.softeng306_application.ViewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements Activity  {

    private MainViewModel mainViewModel;
    private ListViewModel listViewModel;
    private RestaurantRecyclerAdapter restaurantAdapter;
    private CategoryDropdownAdapter adapterCategoryItems;
    private ArrayAdapter<String> adapterItems;

    private class ViewHolder {
        AutoCompleteTextView autoCompleteTextView;
        TextView emptyListText;
        RecyclerView restaurantRecyclerView;
        Button backButton;
        SearchView searchView;
        ImageButton backButton;
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
        vh.searchView = findViewById(R.id.inputText_search);
        vh.searchView.clearFocus();

        vh.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                listViewModel.filterList(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listViewModel.filterList(s);
                return false;
            }
        });

        // Bind RestaurantAdapter
        adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_list_item, listViewModel.getAllCategoryNameOptions());
        vh.autoCompleteTextView.setAdapter(adapterItems);

        // Bind RestaurantRecyclerAdapter
        restaurantAdapter = new RestaurantRecyclerAdapter(this);
        vh.restaurantRecyclerView.setAdapter(restaurantAdapter);

        // Set Vertical Layout Manager for categoryRecyclerView
        LinearLayoutManager verticalLayout = new LinearLayoutManager(ListActivity.this, LinearLayoutManager.VERTICAL, false);
        vh.restaurantRecyclerView.setLayoutManager(verticalLayout);
        listViewModel.setAllCategories();

        Intent intent = getIntent();
        if (intent != null) {
            if(intent.hasExtra("CATEGORY")){
                Category category = intent.getParcelableExtra("CATEGORY");
                listViewModel.setCategory(category);
                vh.autoCompleteTextView.setText(category.getCategoryType(), false);
                restaurantAdapter.setRestaurants(listViewModel.getRestaurantsTest());

            }
            if(intent.hasExtra("FAVOURITES")){
                Boolean isFavourite = intent.getBooleanExtra("FAVOURITE", false);
                listViewModel.setFavourite(isFavourite);
                restaurantAdapter.setRestaurants(listViewModel.getFavouriteRestaurants());
            }
            if(intent.hasExtra("SEARCH")){
                String searchQuery = intent.getStringExtra("SEARCH");
                restaurantAdapter.setRestaurants(listViewModel.getRestaurantsTest());
                vh.searchView.setQuery(searchQuery, false);
            }
        }


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
                String selectedCategory = (String) adapterView.getItemAtPosition(i);
                vh.autoCompleteTextView.setText(selectedCategory, false);
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