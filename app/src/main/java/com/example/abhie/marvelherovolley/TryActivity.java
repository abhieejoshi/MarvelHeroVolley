package com.example.abhie.marvelherovolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.facebook.shimmer.ShimmerFrameLayout;

public class TryActivity extends AppCompatActivity {
    ImageView imageViewTry;
    ShimmerFrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try);
        imageViewTry=findViewById(R.id.imageView2);
        container= findViewById(R.id.shimmerlayout2);
        container.startShimmerAnimation();

    }
}
