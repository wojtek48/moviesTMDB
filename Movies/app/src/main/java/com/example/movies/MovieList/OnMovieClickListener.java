package com.example.movies.MovieList;

import android.view.View;

import com.example.movies.model.Movie;

/**
 * Created by Tushar on 6/13/17.
 */

public interface OnMovieClickListener {
    void onMovieSelected(View selectedView, Movie Movie, int position);
}
