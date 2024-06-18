package co.wymsii.MovieCollection.ui.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.List;

import javax.inject.Inject;

import co.wymsii.MovieCollection.data.AppDatabase;
import co.wymsii.MovieCollection.data.Movie;
import co.wymsii.MovieCollection.data.MovieRepository;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    // @Inject
    public MovieRepository repo;

    public HomeViewModel(Context context) {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

        repo = new MovieRepository(context);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Movie>> getMovies() {
        return repo.getMovies();
    }
}