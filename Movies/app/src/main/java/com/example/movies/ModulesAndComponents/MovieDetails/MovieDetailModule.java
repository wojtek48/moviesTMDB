package com.example.movies.ModulesAndComponents.MovieDetails;

import com.example.movies.ApiConnection.ApiService;
import com.example.movies.MovieDetail.MovieDetailPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailModule {
    @Provides
    @Singleton
    public MovieDetailPresenter provideMovieDetailPresenter(ApiService apiService) {
        return new MovieDetailPresenter(apiService);
    }
}