package com.example.softeng306_application.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.example.softeng306_application.ViewModel.DetailsViewModel;
import com.example.softeng306_application.ViewModel.ListViewModel;
import com.example.softeng306_application.ViewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements Activity {

    private MainViewModel mainViewModel;
    private ListViewModel listViewModel;
    private DetailsViewModel detailsViewModel;
    private RestaurantRecyclerAdapter restaurantAdapter;
    private CategoryDropdownAdapter adapterCategoryItems;
    private ArrayAdapter<String> adapterItems;

    private class ViewHolder {
        AutoCompleteTextView autoCompleteTextView;
        TextView emptyListText;
        RecyclerView restaurantRecyclerView;
        SearchView searchView;
        ImageButton backButton;
        View viewLayout;
        LinearLayout customSearchBar;
        EditText searchEditText;
    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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
        vh.customSearchBar = findViewById(R.id.customSearchBar);

        vh.searchEditText = findViewById(R.id.searchEditText);

        vh.viewLayout= findViewById(R.id.layout_list);
        vh.viewLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                closeKeyboard();
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
        listViewModel.setFavourite(false);
        listViewModel.loadFavouriteList();
        listViewModel.setAllCategories();

        //TODO FIX FAVOURITES NOT SHOWING ON LIST AFTER JUST FAVOURITING ON DETAILS
        listViewModel.getFavouritesList().observe(this, restaurants -> {
            // Update the adapter with the new list of items
            restaurantAdapter.setFavouriteRestaurants(restaurants);
        });

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("CATEGORY")) {
                Category category = intent.getParcelableExtra("CATEGORY");
                listViewModel.setCategory(category);
                vh.autoCompleteTextView.setText(category.getCategoryType(), false);
                restaurantAdapter.setRestaurants(listViewModel.getRestaurantsTest());

            } else if (intent.hasExtra("FAVOURITES")) {
                Boolean isFavourite = intent.getBooleanExtra("FAVOURITE", false);
                listViewModel.setFavourite(true);
                restaurantAdapter.setRestaurants(listViewModel.getFavouriteRestaurants());

            } else if (intent.hasExtra("SEARCH")) {
                Boolean isFavourite = intent.getBooleanExtra("SEARCH", false);
                vh.searchEditText.requestFocus();
                restaurantAdapter.setRestaurants(listViewModel.getRestaurantsTest());

            }else {
                restaurantAdapter.setRestaurants(listViewModel.getRestaurantsTest());
            }
        }

        vh.searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String query = charSequence.toString();
                listViewModel.filterList(query);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

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

        listViewModel.getFavouritesList().observe(this, restaurants -> {
            // Update the adapter with the new list of items
            restaurantAdapter.setFavouriteRestaurants(restaurants);
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

        @Override
        protected void onResume() {
            super.onResume();
            listViewModel.loadFavouriteList();
            if (listViewModel.getFavourite()) {
                listViewModel.getFavouriteRestaurants();
            } else {
                listViewModel.getRestaurantsTest();
            }
            listViewModel.getRestaurantList().observe(this, restaurants -> {
                // Update the adapter with the new list of items
                restaurantAdapter.setRestaurants(restaurants);
            });

            listViewModel.getFavouritesList().observe(this, restaurants -> {
                // Update the adapter with the new list of items
                restaurantAdapter.setFavouriteRestaurants(restaurants);
            });


        }

        private void showMainActivity(View v){
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
        }
    }
