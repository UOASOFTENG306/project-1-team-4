package com.example.softeng306_application.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.LoginViewModel;
import com.example.softeng306_application.ViewModel.RegisterViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;

public class RegisterActivity extends AppCompatActivity {

    private class ViewHolder{
        TextInputEditText editTextEmail, editTextPassword, editTextUsername;
        Button registerButton;
    }
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ViewHolder vh = new ViewHolder();

        vh.editTextEmail = findViewById(R.id.email);
        vh.editTextPassword = findViewById(R.id.password);
        vh.editTextUsername = findViewById(R.id.username);
        vh.registerButton = findViewById(R.id.btn_register);
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        vh.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, username, password;
                email = String.valueOf(vh.editTextEmail.getText());
                username = String.valueOf(vh.editTextUsername.getText());
                password = String.valueOf(vh.editTextPassword.getText());

                // Not adding in username for now
                registerViewModel.register(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Account successfully created.",
                                    Toast.LENGTH_SHORT).show();
                            showMainActivity(v);
                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(RegisterActivity.this, "Account failed to be created.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
    public void showMainActivity(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}