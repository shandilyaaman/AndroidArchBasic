package com.example.androidarchbasicsample.dao;

import com.example.androidarchbasicsample.entity.MovieReview;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MovieReview movieReview);

    @Query("delete from movie_review")
    void deleteAll();

    @Query("SELECT * from movie_review order by name ASC")
    LiveData<List<MovieReview>> getAlLMovies();
}
