package com.example.movies.MovieList;

import com.example.movies.model.Movie;

import java.util.List;

public interface RefreshMovieListener {
    void onRefreshStart();

    void onRefreshSuccess(List<Movie> Movies);

    void onRefreshFailure(String reason);
}