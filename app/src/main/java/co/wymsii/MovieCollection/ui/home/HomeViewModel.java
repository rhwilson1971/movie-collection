package co.wymsii.MovieCollection.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.List;
import javax.inject.Inject;

import co.wymsii.MovieCollection.data.Movie;
import co.wymsii.MovieCollection.data.MovieRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {


    private final MovieRepository repo;
    private final SavedStateHandle savedStateHandle;

    @Inject
    public HomeViewModel(MovieRepository repo, SavedStateHandle savedStateHandle) {
        this.repo = repo;
        this.savedStateHandle = savedStateHandle;
    }


    public LiveData<List<Movie>> getMovies() {
        return
                repo.getMovies();
    }
}