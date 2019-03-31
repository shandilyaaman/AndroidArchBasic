package com.example.androidarchbasicsample;

import android.content.Context;
import android.os.AsyncTask;

import com.example.androidarchbasicsample.dao.MovieDao;
import com.example.androidarchbasicsample.entity.MovieReview;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {MovieReview.class}, version = 1, exportSchema = false)
public abstract class MovieReviewRoomDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static MovieReviewRoomDatabase INSTANCE;

    public static MovieReviewRoomDatabase getDataBase(Context context) {
        if (INSTANCE == null) {
            synchronized (MovieReviewRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MovieReviewRoomDatabase.class, "movie_database")
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MovieDao mDao;
        String[] name = {"movie1", "movie2", "movie3"};
        String[] reviews = {"review1", "review2", "review3"};

        PopulateDbAsync(MovieReviewRoomDatabase db) {
            mDao = db.movieDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            for (int i = 0; i <= name.length - 1; i++) {
                MovieReview movieReview = new MovieReview(name[i], reviews[i]);
                mDao.insert(movieReview);
            }
            return null;
        }
    }

}
