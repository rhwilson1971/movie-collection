package co.wymsii.MovieCollection.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import co.wymsii.MovieCollection.R;
import co.wymsii.MovieCollection.data.Movie;
import co.wymsii.MovieCollection.databinding.FragmentHomeBinding;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private  FragmentHomeBinding binding;
    private MoviesAdapter moviesAdapter;
    private final List<Movie> movies = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        moviesAdapter = new MoviesAdapter((movie) -> {

            Bundle args = new Bundle();
            args.putLong("movieId", movie.getId());

            Navigation.findNavController(view).navigate(R.id.action_nav_home_to_movieDetailsFragment, args);
        });


        homeViewModel.getMovies().observe(getViewLifecycleOwner(), allMovies -> {
            moviesAdapter.updateList(allMovies);
            moviesAdapter.notifyDataSetChanged();
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        binding.movieListView.setLayoutManager(linearLayoutManager);
        binding.movieListView.setAdapter(moviesAdapter);

        binding.fab.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_nav_home_to_movieDetailsFragment));
    }
}