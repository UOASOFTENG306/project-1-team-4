package com.example.softeng306_application.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softeng306_application.Adaptor.CategoryRecyclerAdapter;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.CategoryType;
import com.example.softeng306_application.Entity.FastFood;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Adaptor.RandomRecylerAdapter;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private CategoryRecyclerAdapter categoryRecyclerAdapter;
    private RandomRecylerAdapter randomRecylerAdapter;

    private class ViewHolder{
        TextView usernameText;
        ImageButton logoutNavButton, favouritesNavButton, listNavButton, mainNavButton, searchNavButton;
        RecyclerView randomRecyclerView;
        RecyclerView categoryRecyclerView;
        LinearLayout customSearchBar;
        EditText searchEditText;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        RestaurantFirestoreDataProvider restaurantFirestoreDataProvider = new RestaurantFirestoreDataProvider();
//        restaurantFirestoreDataProvider.addRestaurantToFirestore();
        /**UserFirestoreDataProvider userFirestoreDataProvider = new UserFirestoreDataProvider();
         userFirestoreDataProvider.addFavouritesToDB();**/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        ViewHolder vh = new ViewHolder();
//        vh.usernameText = findViewById(R.id.txt_username);
//        mainViewModel.getUserInfo().addOnSuccessListener(documentSnapshot -> {
//            if (documentSnapshot.exists()) {
//                vh.usernameText.setText(documentSnapshot.getString("username"));
//            }
//        });

        vh.customSearchBar = findViewById(R.id.customSearchBar);
        vh.searchEditText = findViewById(R.id.searchEditText);
        vh.searchEditText.setInputType(InputType.TYPE_NULL);
        vh.searchEditText.setFocusable(false);
        vh.searchEditText.setClickable(true);


        // Navbar Buttons
        vh.favouritesNavButton = findViewById(R.id.btn_favourites);
        vh.logoutNavButton = findViewById(R.id.btn_logout);
        vh.listNavButton = findViewById(R.id.btn_list);
        vh.searchNavButton = findViewById(R.id.btn_search);
        vh.mainNavButton = findViewById(R.id.btn_main);

        // Binding RandomRecyclerAdapter
        vh.randomRecyclerView = findViewById(R.id.recview_random);

        // Binding CategoryRecyclerAdapter
        vh.categoryRecyclerView = findViewById(R.id.recview_categories);

        // Create adapters passing in the test lists
        randomRecylerAdapter = new RandomRecylerAdapter(this);
        categoryRecyclerAdapter = new CategoryRecyclerAdapter(this, mainViewModel.getCategories());

        // Attach adapter to the recycler view to populate these items
        vh.randomRecyclerView.setAdapter(randomRecylerAdapter);
        vh.categoryRecyclerView.setAdapter(categoryRecyclerAdapter);

        mainViewModel.getRandomRestaurantList().observe(this, restaurants -> {
            // Update the adapter with the new list of items
            randomRecylerAdapter.setRandmoList(restaurants);
        });

        //Load random restaurants to show
        mainViewModel.getRandomRestaurants();

        // For Landscape mode
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
            vh.randomRecyclerView.setLayoutManager(gridLayoutManager);
        } else { // For Portrait mode
            LinearLayoutManager horizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
            vh.randomRecyclerView.setLayoutManager(horizontalLayout);
        }

        LinearLayoutManager verticalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        vh.categoryRecyclerView.setLayoutManager(verticalLayout);

        //OnClickListeners
        clickNavLogout(vh);
        clickNavFavourites(vh);
        clickSearchBar(vh);
        clickNavSearch(vh);
        clickNavMain(vh);
        clickNavList(vh);
    }
    private void clickSearchBar(ViewHolder vh) {
        vh.searchEditText.setOnClickListener(v -> {
            Intent listIntent = new Intent(this, ListActivity.class);
            listIntent.putExtra("SEARCH", true);
            startActivity(listIntent);
        });
    }
    private void clickNavLogout(ViewHolder vh){
        vh.logoutNavButton.setOnClickListener(v -> {
            mainViewModel.logout();
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        });
    }
    private void clickNavFavourites(ViewHolder vh){
        vh.favouritesNavButton.setOnClickListener(v -> {
            Intent listIntent = new Intent(this, ListActivity.class);
            listIntent.putExtra("FAVOURITES", true);
            startActivity(listIntent);
        });
    }
    private void clickNavList(ViewHolder vh) {
        vh.listNavButton.setOnClickListener(v -> {
            Intent listIntent = new Intent(this, ListActivity.class);
            listIntent.putExtra("CATEGORY", new FastFood());
            startActivity(listIntent);
        });
    }
    private void clickNavMain(ViewHolder vh) {
        vh.mainNavButton.setOnClickListener(v -> {
            Toast.makeText(this, "Already on main menu", Toast.LENGTH_SHORT).show();
        });
    }
    private void clickNavSearch(ViewHolder vh) {
        vh.searchNavButton.setOnClickListener(v -> {
            Intent listIntent = new Intent(this, ListActivity.class);
            listIntent.putExtra("SEARCH", true);
            startActivity(listIntent);
        });
    }

}