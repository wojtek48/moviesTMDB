package com.example.movies.MovieList;

import com.example.movies.model.Movie;

import java.util.List;


public interface LoadMovieListener {
    void onMovieLoadingStart();

    void onMovieLoadingSuccess(List<Movie> Movies);

    void onMovieLoadingFailure(String reason);
}