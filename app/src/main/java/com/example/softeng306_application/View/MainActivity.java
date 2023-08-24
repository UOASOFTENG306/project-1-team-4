package com.example.softeng306_application.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
        ImageButton logoutButton, listButton, mainButton, favouritesButton, searchButton;
        CardView favouriteCardview;
        RecyclerView topRatedRecyclerView;
        RecyclerView categoryRecyclerView;
        SearchView searchView;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestaurantFirestoreDataProvider restaurantFirestoreDataProvider = new RestaurantFirestoreDataProvider();
        restaurantFirestoreDataProvider.addRestaurantToFirestore();

        /**UserFirestoreDataProvider userFirestoreDataProvider = new UserFirestoreDataProvider();
        userFirestoreDataProvider.addFavouritesToDB();**/

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // ViewHolder
        ViewHolder vh = new ViewHolder();
        vh.logoutButton = findViewById(R.id.btn_logout);
        vh.usernameText = findViewById(R.id.txt_username);
        vh.logoutButton = findViewById(R.id.btn_logout);
        vh.listButton = findViewById(R.id.btn_list);
        vh.mainButton = findViewById(R.id.btn_main);
        vh.favouritesButton = findViewById(R.id.btn_favourites);
        vh.searchButton = findViewById(R.id.btn_search);

        vh.searchView = findViewById(R.id.inputText_search);
        vh.searchView.clearFocus();



//        mainViewModel.getUserInfo().addOnSuccessListener(documentSnapshot -> {
//            if (documentSnapshot.exists()) {
//                vh.usernameText.setText(documentSnapshot.getString("username"));
//            }
//        });

        // RECYCLER ADAPTERS
        vh.topRatedRecyclerView = findViewById(R.id.recview_top_rated);
        vh.categoryRecyclerView = findViewById(R.id.recview_categories);

        topRatedAdapter = new TopRatedRecylerAdapter(this, mainViewModel.getTopRatedRestaurants());
        categoryRecyclerAdapter = new CategoryRecyclerAdapter(this, mainViewModel.getCategories());

        vh.topRatedRecyclerView.setAdapter(topRatedAdapter);
        vh.categoryRecyclerView.setAdapter(categoryRecyclerAdapter);

        // For Portrait mode
//        LinearLayoutManager horizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
//        vh.topRatedRecyclerView.setLayoutManager(horizontalLayout);

        // For Landscape mode
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        vh.topRatedRecyclerView.setLayoutManager(gridLayoutManager);

        LinearLayoutManager verticalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        vh.categoryRecyclerView.setLayoutManager(verticalLayout);

        // OnClickListeners
        clickSearchBar(vh);
        clickNavFavourites(vh);
        clickNavLogout(vh);
        clickNavSearch(vh, new View(this));
        clickNavMain(vh);
        clickNavList(vh);
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

    // This method implements a listener for the navbar search button which simulates clicking the search bar at the top, bringing up the keyboard
    private void clickNavSearch(ViewHolder vh, View v) {
        vh.searchButton.setOnClickListener(view -> {
            vh.searchView.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        });
    }
    private void clickNavLogout(ViewHolder vh){
        vh.logoutButton.setOnClickListener(v -> {
            mainViewModel.logout();
            showLoginActivity(v);
        });
    }
    private void clickNavFavourites(ViewHolder vh){
        vh.favouritesButton.setOnClickListener(v -> showListActivity(v));
    }
    private void clickNavMain(ViewHolder vh) {
        vh.mainButton.setOnClickListener(v -> {
            Toast.makeText(this,"Already on Main Menu", Toast.LENGTH_SHORT).show();
        });
    }
    private void clickNavList(ViewHolder vh) {
        vh.listButton.setOnClickListener(v -> {
            showListActivitySearch("");
        });
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