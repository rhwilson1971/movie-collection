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

    public MovieRepository moviesRepository;
    public SavedStateHandle savedStateHandle;

    @Inject
    MovieDetailsViewModel(
            SavedStateHandle savedStateHandle,
            MovieRepository moviesRepository) {

        this.moviesRepository = moviesRepository;
        this.savedStateHandle = savedStateHandle;

        String movieId = savedStateHandle.get("movieId");
        Long id = Long.parseLong(movieId);
        LiveData<Movie> movie = moviesRepository.getMovie(id);

        movie.observeForever(m -> {
            movieTitle.setValue(m.getTitle());
            movieDescription.setValue(m.getDescription());
        });
    }

    public MutableLiveData<String> getMovieTitle() {
        return movieTitle;
    }
    public MutableLiveData<String> getMovieDescription() {
        return movieDescription;
    }
}