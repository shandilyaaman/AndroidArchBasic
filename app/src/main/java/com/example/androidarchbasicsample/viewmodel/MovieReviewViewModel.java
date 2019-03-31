package com.example.androidarchbasicsample.viewmodel;

import android.app.Application;

import com.example.androidarchbasicsample.entity.MovieReview;
import com.example.androidarchbasicsample.repo.MovieReviewRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MovieReviewViewModel extends AndroidViewModel {
    private MovieReviewRepository mRepository;
    private LiveData<List<MovieReview>> mAllMovies;

    public MovieReviewViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MovieReviewRepository(application);
        mAllMovies = mRepository.getmAllMovies();
    }

    public LiveData<List<MovieReview>> getmAllMovies() {
        return mAllMovies;
    }

    public void insert(MovieReview movieReview) {
        mRepository.insert(movieReview);
    }
}
