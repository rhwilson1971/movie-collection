package co.wymsii.MovieCollection.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import co.wymsii.MovieCollection.data.Movie;
import co.wymsii.MovieCollection.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MoviesAdapter moviesAdapter;
    private List<Movie> movies = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        moviesAdapter = new MoviesAdapter(movies, (movie) -> {
            Log.d("debug", movie.toString());

        });
        binding.movieListView.setAdapter(moviesAdapter);

        homeViewModel.getMovies().observe(getViewLifecycleOwner(), movies -> {
            this.movies.clear();
            this.movies.addAll(movies);
            moviesAdapter.notifyDataSetChanged();
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}