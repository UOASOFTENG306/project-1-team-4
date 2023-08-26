package com.example.softeng306_application.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.softeng306_application.Adaptor.CategoryRecyclerAdapter;
import com.example.softeng306_application.Adaptor.TopRatedRecylerAdapter;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.CategoryType;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.MainViewModel;
import com.example.softeng306_application.dataprovider.RestaurantFirestoreDataProvider;
import com.example.softeng306_application.dataprovider.UserFirestoreDataProvider;

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private CategoryRecyclerAdapter categoryRecyclerAdapter;
    private TopRatedRecylerAdapter topRatedAdapter;

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
//        RestaurantFirestoreDataProvider restaurantFirestoreDataProvider = new RestaurantFirestoreDataProvider();
//        restaurantFirestoreDataProvider.addRestaurantToFirestore();
        /**UserFirestoreDataProvider userFirestoreDataProvider = new UserFirestoreDataProvider();
        userFirestoreDataProvider.addFavouritesToDB();**/
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
        vh.topRatedRecyclerView = findViewById(R.id.recview_top_rated);

        // Binding CategoryRecyclerAdapter
        vh.categoryRecyclerView = findViewById(R.id.recview_categories);

        // Create adapters passing in the test lists
        topRatedAdapter = new TopRatedRecylerAdapter(this);
        categoryRecyclerAdapter = new CategoryRecyclerAdapter(this, mainViewModel.getCategories());

        mainViewModel.getRandomRestaurantList().observe(this, restaurants -> {
            // Update the adapter with the new list of items
            topRatedAdapter.setTopRatedList(restaurants);
        });

        //Load random restaurants to show
        mainViewModel.getRandomRestaurants();

        // Attach adapter to the recycler view to populate these items
        vh.topRatedRecyclerView.setAdapter(topRatedAdapter);
        vh.categoryRecyclerView.setAdapter(categoryRecyclerAdapter);

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