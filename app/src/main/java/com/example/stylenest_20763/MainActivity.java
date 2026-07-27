package com.example.stylenest_20763;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 carouselViewPager;
    private Button btnMpesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        carouselViewPager = findViewById(R.id.carouselViewPager);
        btnMpesa = findViewById(R.id.btnMpesa);

        setupCarousel();
        setupMpesa();
    }

    private void setupCarousel() {
        List<String> images = new ArrayList<>();
        images.add("https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&w=800&q=60");
        images.add("https://images.unsplash.com/photo-1505740420928-5e560c06d30e?auto=format&fit=crop&w=800&q=60");
        images.add("https://images.unsplash.com/photo-1542291026-7eec264c27ff?auto=format&fit=crop&w=800&q=60");

        CarouselAdapter adapter = new CarouselAdapter(images);
        carouselViewPager.setAdapter(adapter);
    }

    private void setupMpesa() {
        if (btnMpesa != null) {
            btnMpesa.setOnClickListener(v -> showMpesaPrompt());
        }
    }

    private void showMpesaPrompt() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("M-Pesa Payment")
                .setMessage("Enter your phone number to receive the M-Pesa STK Push.")
                .setView(R.layout.mpesa_input_dialog)
                .setPositiveButton("Pay", (dialog, which) -> {
                    Toast.makeText(MainActivity.this, "STK Push sent! Please enter your PIN on your phone.", Toast.LENGTH_LONG).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
