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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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
        SearchView searchView;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RestaurantFirestoreDataProvider restaurantFirestoreDataProvider = new RestaurantFirestoreDataProvider();
        restaurantFirestoreDataProvider.addRestaurantToFirestore();
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
        vh.searchView = findViewById(R.id.inputText_search);
        vh.searchView.clearFocus();

        // Binding TopRatedRecyclerAdapter
        vh.topRatedRecyclerView = findViewById(R.id.recview_top_rated);

        // Binding CategoryRecyclerAdapter
        vh.categoryRecyclerView = findViewById(R.id.recview_categories);

        // Create adapters passing in the test lists
        topRatedAdapter = new TopRatedRecylerAdapter(this, mainViewModel.getTopRatedRestaurants());
        categoryRecyclerAdapter = new CategoryRecyclerAdapter(this, mainViewModel.getCategories());

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
        vh.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showListActivitySearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void showListActivitySearch(CharSequence query) {
        Intent listIntent = new Intent(this, ListActivity.class);
        listIntent.putExtra("SEARCH", query);
        startActivity(listIntent);
    }

    private void clickLogout(ViewHolder vh){
        vh.logoutButton.setOnClickListener(v -> {
            mainViewModel.logout();
            showLoginActivity(v);
        });
    }
    private void clickFavourites(ViewHolder vh){
        vh.favouriteCardview.setOnClickListener(v -> showListActivity(v));
    }
    private void showLoginActivity(View v) {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }
    private void showListActivity(View v){
        Intent listIntent = new Intent(this, ListActivity.class);
        listIntent.putExtra("FAVOURITES", true);
        startActivity(listIntent);
    }

    private void showListActivityFromSearch(View v){
        Intent listIntent = new Intent(this, ListActivity.class);
        listIntent.putExtra("SEARCH", true);
        startActivity(listIntent);
    }
}