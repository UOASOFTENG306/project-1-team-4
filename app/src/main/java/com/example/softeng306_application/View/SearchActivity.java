package com.example.softeng306_application.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.SearchViewModel;

public class SearchActivity extends AppCompatActivity implements Activity  {

    private SearchViewModel searchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

}