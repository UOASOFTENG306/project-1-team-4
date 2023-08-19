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
    String[] categories = {"FAST-FOOD", "EUROPEAN", "ASIAN", "CAFE"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    // TODO: delete; for testing purposes, just to see if restaurant recycler view gets populated.
    private MainViewModel mainViewModel;
    private ListViewModel listViewModel;
    private RestaurantRecyclerAdapter restaurantAdapter;
    private Category category;

    private class ViewHolder {
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
        Intent intent = getIntent();
        if (intent != null) {
            List<Category> categoryList = new ArrayList<Category>();
            category = intent.getParcelableExtra("CATEGORY");
            categoryList.add(category);
            listViewModel.setCategory(categoryList);
        }

        // Bind RestaurantRecyclerAdapter
        vh.restaurantRecyclerView = findViewById(R.id.recview_restaurant_list);
        restaurantAdapter = new RestaurantRecyclerAdapter(this, listViewModel.getRestaurantsTest());
        vh.restaurantRecyclerView.setAdapter(restaurantAdapter);
        vh.emptyListText = findViewById(R.id.txt_emptyList);
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

        // TODO: delete; for testing purposes
        autoCompleteTextView = findViewById(R.id.dropdown_category);
        adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_list_item, categories);

        listViewModel.getRestaurantList().observe(this, restaurants -> {
            // Update the adapter with the new list of items
            restaurantAdapter.setRestaurants(restaurants);
        });

        listViewModel.getEmptyMessageVisibility().observe(this, visibility -> {
            vh.emptyListText.setVisibility(visibility);
        });

        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setText(category.getCategoryType(), false);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedCategory = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(ListActivity.this, "Category: " + selectedCategory, Toast.LENGTH_SHORT).show();

                autoCompleteTextView.setText(selectedCategory, false);
                // Which category was selected
                Category category = null;
                if (selectedCategory.equals("FAST-FOOD")) { category = new FastFood(); }
                else if (selectedCategory.equals("ASIAN")) { category = new Asian(); }
                else if (selectedCategory.equals("EUROPEAN")) { category = new European(); }
                else if (selectedCategory.equals("CAFE")) { category = new Cafe(); }

                List<Category> categoryList = new ArrayList<>();
                categoryList.add(category);

                listViewModel.setCategory(categoryList);
                restaurantAdapter.setRestaurants(listViewModel.getRestaurantsTest());
            }
        });

    }


    private void showMainActivity(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

}