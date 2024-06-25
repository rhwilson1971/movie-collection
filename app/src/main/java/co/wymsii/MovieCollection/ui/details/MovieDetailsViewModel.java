package co.wymsii.MovieCollection.ui.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.wymsii.MovieCollection.data.Movie;
import co.wymsii.MovieCollection.data.MovieRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MovieDetailsViewModel extends ViewModel {

    MutableLiveData<String> movieTitle = new MutableLiveData<>();
    MutableLiveData<String> movieDescription = new MutableLiveData<>();

    public final MovieRepository moviesRepository;
    public final SavedStateHandle savedStateHandle;

    @Inject
    MovieDetailsViewModel(
            MovieRepository moviesRepository,
            SavedStateHandle savedStateHandle
            ) {

        this.moviesRepository = moviesRepository;
        this.savedStateHandle = savedStateHandle;

        String movieId = savedStateHandle.get("movieId");
        long id = Long.parseLong(movieId);

        if(id != 0L) {
            LiveData<Movie> movie = moviesRepository.getMovie(id);

            movie.observeForever(m -> {
                movieTitle.setValue(m.getTitle());
                movieDescription.setValue(m.getDescription());
            });
        }
        else {
            movieTitle.setValue("");
            movieDescription.setValue("");
        }

    }

    public MutableLiveData<String> getMovieTitle() {
        return movieTitle;
    }
    public MutableLiveData<String> getMovieDescription() {
        return movieDescription;
    }
}