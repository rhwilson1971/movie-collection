package co.wymsii.MovieCollection.ui.details;

import static androidx.navigation.NavGraphBuilderKt.navigation;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

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
        mViewModel.getMovieGenre().observe(getViewLifecycleOwner(), binding.movieGenre::setText);
        mViewModel.getMediaType().observe(getViewLifecycleOwner(), binding.movieMedia::setText);

        binding.imageGenre.setOnClickListener(v -> setMovieGenre());
        binding.imageMedia.setOnClickListener(v -> setMediaType());

        binding.fab.setOnClickListener(v -> {

            mViewModel.getMovieTitle().postValue(title.getText().toString());
            mViewModel.getMovieDescription().postValue(description.getText().toString());

            mViewModel.save(title.getText().toString(),
                    description.getText().toString());
            // save to repo
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executorService.execute(() -> {

                mViewModel.updateRepo();

                // finished task
                handler.post(() ->
                {
                    Snackbar.make(v, "Movie Saved", Snackbar.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigateUp();
                });
            });
        });


        binding.editTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.fab.setEnabled(!s.toString().isEmpty());
                Log.d("TAG", "afterTextChanged: " + s);
            }
        });
        binding.editTextDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("TAG", "afterTextChanged: " + s.toString());
            }
        });
    }

    private void setMovieGenre() {

        String [] items = getResources().getStringArray(R.array.movie_genres);
        final String[] selectedItem = new String[1];
        int defaultItem = 0;
        new AlertDialog.Builder(getActivity())
                .setTitle("Select Genre")
                .setSingleChoiceItems(items, defaultItem, (dialog, which) -> {
                    selectedItem[0] =items[which];
                })
                .setPositiveButton("OK", (dialog, which) -> {
                    mViewModel.getMovieGenre().postValue(selectedItem[0]);
                }).setNegativeButton("Cancel", null)
                .show();
    }

    private void setMediaType() {

        String [] items = getResources().getStringArray(R.array.media_types);
        final String[] selectedItem = new String[1];
        int defaultItem = 0;
        new AlertDialog.Builder(getActivity())
                .setTitle("Select Media")
                .setSingleChoiceItems(items, defaultItem, (dialog, which) -> {
                    selectedItem[0] =items[which];
                })
                .setPositiveButton("OK", (dialog, which) -> {
                    mViewModel.getMediaType().postValue(selectedItem[0]);
                }).setNegativeButton("Cancel", null)
                .show();
    }
}