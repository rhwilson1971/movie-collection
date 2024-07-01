package co.wymsii.MovieCollection.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.tabs.TabLayout;

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
    private HomeViewModel homeViewModel;

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

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
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

        binding.buttonFindMovies.setOnClickListener(v ->  filterMovies() );
        binding.editSearchTerm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.buttonFindMovies.setEnabled(!s.toString().isEmpty());
            }
        });

        homeViewModel.getSearchTerm().observe(getViewLifecycleOwner(), binding.editSearchTerm::setText);
        int index = binding.tabLayoutCategories.getTabCount();
        binding.tabLayoutCategories.addOnTabSelectedListener(tabSelectedListener);
    }

    TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            homeViewModel.getSearchFilter().postValue(tab.getPosition());

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    };

    private void filterMovies() {
    }

}