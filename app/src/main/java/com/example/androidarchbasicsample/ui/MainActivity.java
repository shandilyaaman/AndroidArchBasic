package com.example.androidarchbasicsample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidarchbasicsample.R;
import com.example.androidarchbasicsample.adapter.MovieReviewListAdapter;
import com.example.androidarchbasicsample.entity.MovieReview;
import com.example.androidarchbasicsample.viewmodel.MovieReviewViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_MOVIE_ACTIVITY_REQUEST_CODE = 1;
    private MovieReviewViewModel movieReviewViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieReviewViewModel = ViewModelProviders.of(this).get(MovieReviewViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        final MovieReviewListAdapter adapter = new MovieReviewListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieReviewViewModel.getmAllMovies().observe(this, new Observer<List<MovieReview>>() {
            @Override
            public void onChanged(List<MovieReview> words) {
                adapter.setWords(words);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewMovieActivity.class);
                startActivityForResult(intent, NEW_MOVIE_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_MOVIE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String name = data.getStringExtra(NewMovieActivity.NAME);
            String review = data.getStringExtra(NewMovieActivity.REVIEW);
            MovieReview word = new MovieReview(name, review);
            movieReviewViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Not Saved",
                    Toast.LENGTH_LONG).show();
        }
    }
}
