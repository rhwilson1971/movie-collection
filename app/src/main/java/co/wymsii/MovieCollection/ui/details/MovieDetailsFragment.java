package co.wymsii.MovieCollection.ui.details;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import co.wymsii.MovieCollection.R;
import co.wymsii.MovieCollection.databinding.FragmentMovieDetailsBinding;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
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
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText title = binding.editTextTitle;
        final EditText description = binding.editTextDescription;

        mViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        mViewModel.getMovieTitle().observe(getViewLifecycleOwner(), title::setText);
        mViewModel.getMovieDescription().observe(getViewLifecycleOwner(), description::setText);
    }

    private void save() {
        String title = binding.editTextTitle.getText().toString();
        String description = binding.editTextDescription.getText().toString();
        mViewModel.save();
    }

    private void setMovieGenre() {

        String [] items = getResources().getStringArray(R.array.movie_genres);

        int selectedItem = 0;
        new AlertDialog.Builder(getActivity())
                .setTitle("Select Genre")
                .setSingleChoiceItems(items, selectedItem, (dialog, which) -> {
                    String item = items[which];
                    mViewModel.getMovieGenre().postValue(item);
                })
                .setPositiveButton("OK", (dialog, which) -> {
                    mViewModel.save();
                }).setNegativeButton("Cancel", null);
    }
}