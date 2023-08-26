package com.example.softeng306_application.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.softeng306_application.Adaptor.CategoryRecyclerAdapter;
import com.example.softeng306_application.Adaptor.RandomRecylerAdapter;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private CategoryRecyclerAdapter categoryRecyclerAdapter;
    private RandomRecylerAdapter randomRecylerAdapter;

    private class ViewHolder{
        TextView usernameText;
        ImageButton logoutButton;
        CardView favouriteCardview;
        RecyclerView topRatedRecyclerView;
        RecyclerView categoryRecyclerView;
        LinearLayout customSearchBar;
        EditText searchEditText;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        ViewHolder vh = new ViewHolder();
        vh.logoutButton = findViewById(R.id.btn_logout);
        vh.usernameText = findViewById(R.id.txt_username);
        vh.favouriteCardview = findViewById(R.id.cardview_favourites);
        mainViewModel.getUserInfo().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                vh.usernameText.setText(documentSnapshot.getString("username"));
            }
        });

        vh.customSearchBar = findViewById(R.id.customSearchBar);
        vh.searchEditText = findViewById(R.id.searchEditText);
        vh.searchEditText.setInputType(InputType.TYPE_NULL);
        vh.searchEditText.setFocusable(false);
        vh.searchEditText.setClickable(true);

        // Binding TopRatedRecyclerAdapter
        vh.topRatedRecyclerView = findViewById(R.id.recview_random);

        // Binding CategoryRecyclerAdapter
        vh.categoryRecyclerView = findViewById(R.id.recview_categories);

        // Create adapters passing in the test lists
        randomRecylerAdapter = new RandomRecylerAdapter(this);
        categoryRecyclerAdapter = new CategoryRecyclerAdapter(this, mainViewModel.getCategories());

        // Attach adapter to the recycler view to populate these items
        vh.topRatedRecyclerView.setAdapter(randomRecylerAdapter);
        vh.categoryRecyclerView.setAdapter(categoryRecyclerAdapter);

        mainViewModel.getRandomRestaurantList().observe(this, restaurants -> {
            // Update the adapter with the new list of items
            randomRecylerAdapter.setRandmoList(restaurants);
        });

        //Load random restaurants to show
        mainViewModel.getRandomRestaurants();

        // Set layout manager to position the items
        // Set Horizontal Layout Manager for topRatedRecyclerView
        LinearLayoutManager horizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        vh.topRatedRecyclerView.setLayoutManager(horizontalLayout);

        // Set Vertical Layout Manager for categoryRecyclerView
        LinearLayoutManager verticalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        vh.categoryRecyclerView.setLayoutManager(verticalLayout);

        //OnClickListeners
        clickLogout(vh);
        clickFavourites(vh);
        clickSearchBar(vh);
    }

    private void clickSearchBar(ViewHolder vh) {
        vh.searchEditText.setOnClickListener(v -> {
            Intent listIntent = new Intent(this, ListActivity.class);
            listIntent.putExtra("SEARCH", true);
            startActivity(listIntent);
        });
    }

    private void clickLogout(ViewHolder vh){
        vh.logoutButton.setOnClickListener(v -> {
            mainViewModel.logout();
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        });
    }
    private void clickFavourites(ViewHolder vh){
        vh.favouriteCardview.setOnClickListener(v -> {
            Intent listIntent = new Intent(this, ListActivity.class);
            listIntent.putExtra("FAVOURITES", true);
            startActivity(listIntent);
        });
    }

}