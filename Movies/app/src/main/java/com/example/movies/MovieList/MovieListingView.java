package com.example.movies.MovieList;

import com.example.movies.model.Movie;

import java.util.List;

public interface MovieListingView {
    void movieProgress();

    void hideProgress();

    void movieMoviesLoadingProgress();

    void hideMoviesLoadingProgress();

    void movieMovies(List<Movie> Movies);

    void hideErrors();

    void movieMovieLoadingError(String reason);

    void movieMovieLoadingError();

    boolean isRefreshing();

    void hideRefreshing();
}
