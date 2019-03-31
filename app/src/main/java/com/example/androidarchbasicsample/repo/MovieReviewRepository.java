package com.example.androidarchbasicsample.repo;

import android.app.Application;
import android.os.AsyncTask;

import com.example.androidarchbasicsample.MovieReviewRoomDatabase;
import com.example.androidarchbasicsample.dao.MovieDao;
import com.example.androidarchbasicsample.entity.MovieReview;

import java.util.List;

import androidx.lifecycle.LiveData;

public class MovieReviewRepository {
    private MovieDao mMovieDao;
    private LiveData<List<MovieReview>> mAllMovies;

    public MovieReviewRepository(Application application) {
        MovieReviewRoomDatabase db = MovieReviewRoomDatabase.getDataBase(application);
        mMovieDao = db.movieDao();
        mAllMovies = mMovieDao.getAlLMovies();
    }


    public LiveData<List<MovieReview>> getmAllMovies() {
        return mAllMovies;
    }

    public void insert(MovieReview word) {
        new insertAsyncTask(mMovieDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<MovieReview, Void, Void> {

        private MovieDao mAsyncTaskDao;

        insertAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MovieReview... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
