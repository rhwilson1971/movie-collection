package co.wymsii.MovieCollection.ui.details;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.function.ObjIntConsumer;

import co.wymsii.MovieCollection.R;
import co.wymsii.MovieCollection.databinding.FragmentMovieDetailsBinding;

public class MovieDetailsFragment extends Fragment {

    private MovieDetailsViewModel mViewModel;

    FragmentMovieDetailsBinding binding;

    public static MovieDetailsFragment newInstance() {
        return new MovieDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText title = binding.editTextTitle;
        final EditText description = binding.editTextDescription;

        mViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        mViewModel.getMovieTitle().observe(getViewLifecycleOwner(), title::setText);
        mViewModel.getMovieDescription().observe(getViewLifecycleOwner(), description::setText);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}