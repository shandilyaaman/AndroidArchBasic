package com.example.androidarchbasicsample.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_review")
public class MovieReview {
    public MovieReview(@NonNull String movieName, @NonNull String movieReview) {
        this.movieName = movieName;
        this.review = movieReview;
    }

    public MovieReview() {
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String movieName;

    @ColumnInfo(name = "review")
    private String review;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @NonNull
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(@NonNull String movieName) {
        this.movieName = movieName;
    }
}
