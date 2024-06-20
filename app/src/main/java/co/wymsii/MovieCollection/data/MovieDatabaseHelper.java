package co.wymsii.MovieCollection.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MovieDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int VERSION = 2;

    public MovieDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static final class MoviesTable {
        private static final String TABLE = "movies";
        private static final String COL_ID = "id";
        private static final String COL_TITLE = "title";
        private static final String COL_DESC = "description";
        private static final String COL_PREMIERED = "date_premiered";
        private static final String COL_ADDED = "date_added";
        private static final String COL_MEDIA_TYPE = "date_purchased";
        private static final String COL_GENRE = "date_purchased";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MoviesTable.TABLE + " (" +
                MoviesTable.COL_ID + " integer primary key autoincrement, " +
                MoviesTable.COL_TITLE + " text, " +
                MoviesTable.COL_DESC + " text, " +
                MoviesTable.COL_GENRE + " text, " +

                MoviesTable.COL_PREMIERED + " int, " +
                MoviesTable.COL_ADDED + " int, " +
                MoviesTable.COL_MEDIA_TYPE + " text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + MoviesTable.TABLE);
        onCreate(db);
    }

    public Long add(Movie movie) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MoviesTable.COL_TITLE, movie.getTitle());
        values.put(MoviesTable.COL_DESC, movie.getDescription());
        values.put(MoviesTable.COL_GENRE, movie.getGenre());

        long premiered = movie.getPremiered().getTime();
        long added = movie.getAdded().getTime();

        values.put(MoviesTable.COL_ADDED, added);
        values.put(MoviesTable.COL_PREMIERED, premiered);

        values.put(MoviesTable.COL_MEDIA_TYPE, movie.getMediaType());

        return db.insert(MoviesTable.TABLE, null, values);
    }

    public void update(Movie movie) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MoviesTable.COL_TITLE, movie.getTitle());
        values.put(MoviesTable.COL_DESC, movie.getDescription());
        values.put(MoviesTable.COL_GENRE, movie.getGenre());

        long premiered = movie.getPremiered().getTime();
        long added = movie.getAdded().getTime();

        values.put(MoviesTable.COL_ADDED, added);
        values.put(MoviesTable.COL_PREMIERED, premiered);

        values.put(MoviesTable.COL_MEDIA_TYPE, movie.getMediaType());
        String whereClause = "id = ? ";

        int rowsUpdated =
                db.update(MoviesTable.TABLE, values, "id = ?", new String[]{Long.toString(movie.getId())});


    }

    public Movie get(int id) {
        return new Movie();
    }

    public List<Movie> getAllMovies() {
        SQLiteDatabase db = getReadableDatabase();

        List<Movie> movieList = new ArrayList<Movie>();


        String sql = "select * from " + MoviesTable.TABLE;
        Cursor cursor = db.rawQuery(sql, new String[] { });
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(0);
                String title = cursor.getString(1);
                String desc = cursor.getString(2);
                Long premiered = cursor.getLong(3);
                Long added = cursor.getLong(4);
                String mediaType = cursor.getString(5);
                String genre = cursor.getString(6);

                Movie movie = new Movie(id, title, desc, premiered, added, mediaType, genre);

                movieList.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return movieList;
    }
}
