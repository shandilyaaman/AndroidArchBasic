package com.example.androidarchbasicsample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidarchbasicsample.viewmodel.NewMovieActivityViewModel;
import com.example.androidarchbasicsample.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class NewMovieActivity extends AppCompatActivity {
    public static final String NAME ="name";
    public static final String REVIEW ="review";
    private EditText mEditMovieView, mEditReviewView;
    private NewMovieActivityViewModel activityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_movie);
        activityViewModel = ViewModelProviders.of(this).get(NewMovieActivityViewModel.class);
        mEditMovieView = findViewById(R.id.edit_name);
        mEditReviewView = findViewById(R.id.edit_review);
        final Button button = findViewById(R.id.button_save);

        mEditMovieView.setText(activityViewModel.getMovie().getMovieName());
        mEditReviewView.setText(activityViewModel.getMovie().getReview());

        mEditMovieView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                activityViewModel.setMovieName(s.toString());
            }
        });

        mEditMovieView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                activityViewModel.setMovieReview(s.toString());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditMovieView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String movieName = mEditMovieView.getText().toString();
                    String movieReview = mEditReviewView.getText().toString();
                    replyIntent.putExtra(NAME, movieName);
                    replyIntent.putExtra(REVIEW, movieReview);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
