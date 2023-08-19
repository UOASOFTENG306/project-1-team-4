package com.example.softeng306_application.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.ListViewModel;

public class ListActivity extends AppCompatActivity implements Activity  {
    private ListViewModel listViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);

        Intent intent = getIntent();
        if (intent != null) {
            Category category = intent.getParcelableExtra("CATEGORY");
        }
    }
}