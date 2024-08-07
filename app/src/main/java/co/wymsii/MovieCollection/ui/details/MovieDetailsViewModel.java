package co.wymsii.MovieCollection.ui.details;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.Date;


import javax.inject.Inject;

import co.wymsii.MovieCollection.data.Movie;
import co.wymsii.MovieCollection.data.MovieRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MovieDetailsViewModel extends ViewModel {

    MutableLiveData<String> movieTitle = new MutableLiveData<>();
    MutableLiveData<String> movieDescription = new MutableLiveData<>();
    MutableLiveData<Movie> movie = new MutableLiveData<>();
    MutableLiveData<String> movieGenre = new MutableLiveData<>();
    MutableLiveData<String> mediaType = new MutableLiveData<>();
    MutableLiveData<String> showFormat = new MutableLiveData<>();

    public final MovieRepository moviesRepository;
    public final SavedStateHandle savedStateHandle;

    @Inject
    MovieDetailsViewModel(
            MovieRepository moviesRepository,
            SavedStateHandle savedStateHandle
            ) {

        this.moviesRepository = moviesRepository;
        this.savedStateHandle = savedStateHandle;

        long movieId = savedStateHandle.get("movieId");

        if(movieId != 0L) {

            moviesRepository.getMovie(movieId).observeForever(m -> {
                        if(m != null){

                            movie.postValue(m);
                        }
                    });


            movie.observeForever(m -> {

                if(m != null)
                    movieTitle.setValue(m.getTitle());
                    movieDescription.setValue(m.getDescription());
                    movieGenre.setValue(m.getGenre());
                    mediaType.setValue(m.getMediaType());
                    showFormat.setValue(m.getShowFormat());
                    Log.d("TAG", "update: " + m.getTitle());
                }
            );
//            movie.observeForever(m -> {
//                movieTitle.setValue(m.getTitle());
//                movieDescription.setValue(m.getDescription());
//                movieGenre.setValue(m.getGenre());
//                mediaType.setValue(m.getMediaType());
//                showFormat.setValue(m.getShowFormat());
//            });
        }
        else {
            movieTitle.setValue("");
            movieDescription.setValue("");
            movieGenre.setValue("");
            mediaType.setValue("");
            showFormat.setValue("");
        }
    }

    public void save() {
        if (movie.getValue() == null) {
            movie.setValue(new Movie());
        }
        movie.getValue().setTitle(movieTitle.getValue());
        movie.getValue().setDescription(movieDescription.getValue());
        movie.getValue().setGenre(movieGenre.getValue());
        movie.getValue().setMediaType(mediaType.getValue());
        movie.getValue().setAdded(new Date());
    }

    public void save(String title, String description) {
        if (movie.getValue() == null) {
            movie.setValue(new Movie());
        }
        movie.getValue().setTitle(title);
        movie.getValue().setDescription(description);
        movie.getValue().setGenre(movieGenre.getValue());
        movie.getValue().setMediaType(mediaType.getValue());
        movie.getValue().setShowFormat(showFormat.getValue());
        if(movie.getValue().getId() == 0)
            movie.getValue().setAdded(new Date());
    }

    public void updateRepo(){
        if(movie.getValue().getId() == 0)
            moviesRepository.save(movie.getValue());
        else
            moviesRepository.update(movie.getValue());
    }

    public MutableLiveData<String> getMovieTitle() {
        return movieTitle;
    }
    public MutableLiveData<String> getMovieDescription() {
        return movieDescription;
    }
    public MutableLiveData<String> getMovieGenre() {
        return movieGenre;
    }
    public MutableLiveData<String> getMediaType() {
        return mediaType;
    }
    public MutableLiveData<String> getShowFormat() {
        return showFormat;
    }
}