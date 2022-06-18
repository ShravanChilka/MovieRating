package com.example.movieratings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Details extends AppCompatActivity {

    TextView title,overview,likes,rating;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie);

        Bundle bundle = getIntent().getExtras();
        String mTitle = bundle.getString("title");
        String mOverview = bundle.getString("overview");
        int mLikes = bundle.getInt("likes");
        double mRating = bundle.getDouble("rating");
        String mPoster = bundle.getString("poster");

        title = findViewById(R.id.title_1);
        overview = findViewById(R.id.overview_1);
        likes = findViewById(R.id.likes_1);
        rating = findViewById(R.id.rating_1);
        poster = findViewById(R.id.poster_1);

        Glide.with(this).load(mPoster).into(poster);

        rating.setText(String.valueOf(mRating));
        overview.setText(mOverview);
        likes.setText("("+String.valueOf(mLikes)+")");
        title.setText(mTitle);

    }
}