package co.wymsii.MovieCollection.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.wymsii.MovieCollection.R;
import co.wymsii.MovieCollection.data.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    public MoviesAdapter(List<Movie> movies, OnMovieItemClickListener listener) {
        this.movies = movies;
        this.mListener = listener;
    }

    List<Movie> movies;
    private final OnMovieItemClickListener mListener;

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_layout_template, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.getMovieTitle().setText(movie.getTitle());
        holder.itemView.setOnClickListener(v -> mListener.onItemClick(movie));
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final TextView movieTitle;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_title);
        }

        public TextView getMovieTitle() {
            return movieTitle;
        }
    }

    public interface OnMovieItemClickListener {
        void onItemClick(Movie item);
    }
}
