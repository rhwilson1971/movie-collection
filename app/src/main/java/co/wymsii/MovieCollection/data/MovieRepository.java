package co.wymsii.MovieCollection.data;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class MovieRepository {

    private final AppDatabase db;

    public MovieRepository(Context ctx) {

        String databaseName = "movies";

        db = Room.databaseBuilder(ctx,
                AppDatabase.class, databaseName).build();
    }

    public void add(Movie movie) {
        db.movieDao().insert(movie);
    }

    public LiveData<List<Movie>> getMovies(){
        return db.movieDao().getAll();
    }
}
