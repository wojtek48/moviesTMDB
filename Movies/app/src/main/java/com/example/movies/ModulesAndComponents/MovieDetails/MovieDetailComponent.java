package com.example.movies.ModulesAndComponents.MovieDetails;

import com.example.movies.MovieDetail.MovieDetailFragment;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = MovieDetailModule.class)
public interface MovieDetailComponent {
    void inject(MovieDetailFragment moviesDetailFragment);
}
