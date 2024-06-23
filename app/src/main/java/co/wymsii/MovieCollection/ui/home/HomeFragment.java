package co.wymsii.MovieCollection.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.wymsii.MovieCollection.data.Movie;
import co.wymsii.MovieCollection.databinding.FragmentHomeBinding;
import dagger.hilt.EntryPoint;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private  FragmentHomeBinding binding;
    private MoviesAdapter moviesAdapter;
    private List<Movie> movies = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //try {
            homeViewModel =
                    new ViewModelProvider(this).get(HomeViewModel.class);

            moviesAdapter = new MoviesAdapter(movies, (movie) -> {
                Log.d("debug", movie.toString());

            });
            binding.movieListView.setAdapter(moviesAdapter);

            homeViewModel.getMovies().observe(getViewLifecycleOwner(), movies -> {
                this.movies.clear();
                this.movies.addAll(movies);
                moviesAdapter.notifyDataSetChanged();
            });

//        }
//        catch(Exception ex){
//            Log.d("debug", ex.toString());
//        }
    }
}