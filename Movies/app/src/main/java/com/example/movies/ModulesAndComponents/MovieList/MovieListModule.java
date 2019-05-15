package com.example.movies.ModulesAndComponents.MovieList;

import com.example.movies.ApiConnection.ApiService;
import com.example.movies.MovieList.MovieListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieListModule {
    @Provides
    @Singleton
    public MovieListPresenter provideMovieListingPresenter(ApiService apiService) {
        return new MovieListPresenter(apiService);
    }
}
