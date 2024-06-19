package co.wymsii.MovieCollection.ui.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import co.wymsii.MovieCollection.data.MovieRepository;

public class MovieDetailsViewModel extends ViewModel {

    MutableLiveData<String> movieTitle = new MutableLiveData<>();
    MutableLiveData<String> movieDescription = new MutableLiveData<>();

    public MovieRepository moviesRepository;




    public MutableLiveData<String> getMovieTitle() {
        return movieTitle;
    }

    public MutableLiveData<String> getMovieDescription() {
        return movieDescription;
    }
}