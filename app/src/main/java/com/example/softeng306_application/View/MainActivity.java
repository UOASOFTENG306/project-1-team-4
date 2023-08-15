package com.example.softeng306_application.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;

import com.example.softeng306_application.R;
import com.example.softeng306_application.Repository.UserRepository;
import com.example.softeng306_application.ViewModel.LoginViewModel;
import com.example.softeng306_application.ViewModel.MainViewModel;
import com.example.softeng306_application.ViewModel.RegisterViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private class ViewHolder{
        Button logoutButton;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

    }
}