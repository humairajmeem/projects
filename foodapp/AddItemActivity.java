package com.example.foodapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {
    private ImageView selectedImage;
    private Button selectImageButton;
    private Button backButton;

    // Declare the ActivityResultLauncher for picking an image
    private final ActivityResultLauncher<String> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    // Set the selected image URI to the ImageView
                    selectedImage.setImageURI(uri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // Initialize UI components
        selectedImage = findViewById(R.id.selectedimage);
        selectImageButton = findViewById(R.id.selectimage);
        backButton = findViewById(R.id.backButton);

        // Set up the click listener for the "selectimage" button
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the image picker for "image/*" MIME type
                pickImageLauncher.launch("image/*");
            }
        });

        // Set up the click listener for the "backButton"
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
