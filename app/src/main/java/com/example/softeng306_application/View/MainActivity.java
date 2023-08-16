package com.example.softeng306_application.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.softeng306_application.Adaptor.CategoryRecyclerAdapter;
import com.example.softeng306_application.Adaptor.TopRatedRecylerAdapter;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.CategoryType;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.MainViewModel;

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private class ViewHolder{
        TextView usernameText;
        Button logoutButton;
        RecyclerView topRatedRecyclerView;
        RecyclerView categoryRecyclerView;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        ViewHolder vh = new ViewHolder();
        vh.logoutButton = findViewById(R.id.btn_logout);
        vh.usernameText = findViewById(R.id.txt_username);
        mainViewModel.getUserInfo().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                vh.usernameText.setText(documentSnapshot.getString("username"));
            }
        });

        // TODO: THIS IS FOR TESTING PURPOSES!!!
        // Binding TopRatedRecyclerAdapter
        vh.topRatedRecyclerView = findViewById(R.id.recview_top_rated);

        // Populate topRatedList
        ArrayList<Restaurant> topRatedList = new ArrayList<Restaurant>();
        Restaurant res1 = new Restaurant();
        res1.setLogoImage("res1");
        Restaurant res2 = new Restaurant();
        res2.setLogoImage("res2");
        Restaurant res3 = new Restaurant();
        res3.setLogoImage("res3");

        topRatedList.add(res1);
        topRatedList.add(res2);
        topRatedList.add(res3);

        // TODO: THIS IS FOR TESTING PURPOSES!!!
        // Binding CategoryRecyclerAdapter
        vh.categoryRecyclerView = findViewById(R.id.recview_categories);

        // Populate categoryList
        ArrayList<Category> categoryList = new ArrayList<Category>();
        Category cat1 = new Category();
        cat1.setCategoryType(CategoryType.CAFE);
        Category cat2 = new Category();
        cat2.setCategoryType(CategoryType.ASIAN);
        Category cat3 = new Category();
        cat3.setCategoryType(CategoryType.EUROPEAN);
        Category cat4 = new Category();
        cat4.setCategoryType(CategoryType.FAST_FOOD);

        categoryList.add(cat1);
        categoryList.add(cat2);
        categoryList.add(cat3);
        categoryList.add(cat4);

        // Create adapters passing in the test lists
        TopRatedRecylerAdapter topRatedAdapter = new TopRatedRecylerAdapter(this, topRatedList);
        CategoryRecyclerAdapter categoryRecyclerAdapter = new CategoryRecyclerAdapter(this, categoryList);

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
    }

    private void clickLogout(ViewHolder vh){
        vh.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.logout();
                showLoginActivity(v);
            }
        });
    }

    private void showLoginActivity(View v) {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }
}