package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodapp.databinding.ActivityLoginBinding;
import com.example.foodapp.databinding.ActivitySignBinding;
import com.example.foodapp.model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private String email;
    private String password;
    private String userName;
    private DatabaseReference database;

    private ActivitySignBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Initialize view binding
        binding = ActivitySignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.createUserButton.setOnClickListener(view -> {

            //get text from editText
            userName = binding.name.getText().toString().trim();
            email = binding.emailOrPhone.getText().toString().trim();
            password = binding.password.getText().toString().trim();

            if (userName.isBlank() || email.isBlank() || password.isBlank()){
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
            }else {
                createAccount(email,password);
            }

            //Intent intent = new Intent(signActivity.this, LoginActivity.class);
            //startActivity(intent);
        });

        // Initialize Firebase Auth and Database Reference
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();


        // Set click listener for login button
        binding.alreadyhaveaccount.setOnClickListener(view -> {
            Intent intent = new Intent(signActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void createAccount(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,task ->{
        if (task.isSuccessful()){
            Toast.makeText(this,"Account Created Successfully", Toast.LENGTH_SHORT).show();
            saveUserData();
            Intent intent = new Intent(signActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "Account Creation Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("Account", "createAccount: Failure",task.getException());
        }
        });
    }

    //save data into database
    private void saveUserData() {
        //get text from editText
        userName = binding.name.getText().toString().trim();
        email = binding.emailOrPhone.getText().toString().trim();
        password = binding.password.getText().toString().trim();
//create ther usermodel
        UserModel user = new UserModel(userName, email, password);
//get the uid
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //save user data firebase database
        database.child("user").child(userId).setValue(user);

    }
}