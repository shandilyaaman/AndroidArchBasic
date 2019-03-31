package com.example.androidarchbasicsample.viewmodel;

import android.app.Application;

import com.example.androidarchbasicsample.entity.MovieReview;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class NewMovieActivityViewModel extends AndroidViewModel {
    private MovieReview movieReview = new MovieReview();
    public NewMovieActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public MovieReview getMovie() {
        return movieReview;
    }

    public void setMovieName(String name) {
        movieReview.setMovieName(name);
    }

    public void setMovieReview(String review) {
        movieReview.setReview(review);
    }
}
