package co.wymsii.MovieCollection.data;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.room.ProvidedTypeConverter;
import androidx.room.Room;
import java.util.List;
import javax.inject.Inject;
import dagger.hilt.android.qualifiers.ApplicationContext;


public class MovieRepository {
    private final AppDatabase db;

    @Inject
    public MovieRepository(@ApplicationContext Context ctx) {
        String databaseName = "movies";

        db = Room.databaseBuilder(ctx,
                AppDatabase.class, databaseName)
                .build();


    }

    public void add(Movie movie) {
        db.movieDao().insert(movie);
    }

    public LiveData<List<Movie>> getMovies(){
        return db.movieDao().getAll();
    }

    public LiveData<Movie> getMovie(Long id){
        return db.movieDao().getMovie(id);
    }

    public void save(Movie value) {

        db.movieDao().insert(value);
    }

    public void delete(Movie movie) {
        db.movieDao().delete(movie);
    }

    public void update(Movie movie) {
        db.movieDao().update(movie);
    }
}
