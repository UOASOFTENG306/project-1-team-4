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

import com.example.softeng306_application.Adaptor.TopRatedRecylerAdapter;
import com.example.softeng306_application.Model.TopRated;
import com.example.softeng306_application.R;
import com.example.softeng306_application.Repository.UserRepository;
import com.example.softeng306_application.ViewModel.LoginViewModel;
import com.example.softeng306_application.ViewModel.MainViewModel;
import com.example.softeng306_application.ViewModel.RegisterViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private class ViewHolder{
        TextView usernameText;
        Button logoutButton;
        RecyclerView topRatedRecyclerView;
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

        // Initialize contacts
        ArrayList<TopRated> topRatedList = new ArrayList<TopRated>();
        topRatedList.add(new TopRated("Restaurant 1"));
        topRatedList.add(new TopRated("Restaurant 2"));
        topRatedList.add(new TopRated("Restaurant 3"));
        topRatedList.add(new TopRated("Restaurant 4"));
        topRatedList.add(new TopRated("Restaurant 5"));
        topRatedList.add(new TopRated("Restaurant 6"));
        topRatedList.add(new TopRated("Restaurant 7"));
        topRatedList.add(new TopRated("Restaurant 8"));
        topRatedList.add(new TopRated("Restaurant 9"));

        // Create adapter passing in the test list
        TopRatedRecylerAdapter topRatedAdapter = new TopRatedRecylerAdapter(this, topRatedList);
        // Attach adapter to the recycler view to populate these items
        vh.topRatedRecyclerView.setAdapter(topRatedAdapter);
        // Set layout manager to position the items
        // Set Horizontal Layout Manager for Recycler view
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        vh.topRatedRecyclerView.setLayoutManager(HorizontalLayout);

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