package com.example.androidarchbasicsample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidarchbasicsample.R;
import com.example.androidarchbasicsample.entity.MovieReview;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieReviewListAdapter extends RecyclerView.Adapter<MovieReviewListAdapter.MovieReviewViewHolder> {
    private final LayoutInflater mInflater;
    private List<MovieReview> movieReviewList; // Cached copy of words

    public MovieReviewListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MovieReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MovieReviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewViewHolder holder, int position) {
        if (movieReviewList != null) {
            MovieReview current = movieReviewList.get(position);
            holder.tvMovieName.setText(current.getMovieName());
            holder.tvMovieReview.setText(current.getReview());
        } else {
            // Covers the case of data not being ready yet.
            holder.tvMovieName.setText("NA");
        }
    }

    public void setWords(List<MovieReview> words) {
        movieReviewList = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (movieReviewList != null)
            return movieReviewList.size();
        else return 0;
    }

    class MovieReviewViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMovieName, tvMovieReview;

        private MovieReviewViewHolder(View itemView) {
            super(itemView);
            tvMovieName = itemView.findViewById(R.id.textView);
            tvMovieReview = itemView.findViewById(R.id.textView2);
        }
    }
}
