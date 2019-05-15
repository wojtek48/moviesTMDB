package com.example.movies.MovieDetail;

import com.example.movies.model.Movie;

import java.util.List;


interface MovieDetailView {
    void movieProgress();

    void hideProgressWithError(String reason);

    void hideProgress();

    void movieSimilarMovies(List<Movie> similarMovies);

    long getUserSelectedMovieId();
}
