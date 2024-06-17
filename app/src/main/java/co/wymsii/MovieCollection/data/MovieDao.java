package co.wymsii.MovieCollection.data;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface MovieDao {

    @Query("SELECT * from Movie")
    List<Movie> getAll();

    @Insert
    void insert(Movie movie);

    @Delete
    void delete(Movie movie);
}
