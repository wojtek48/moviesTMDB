package com.example.movies.ModulesAndComponents.MovieList;

import com.example.movies.MovieList.MovieListFragment;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = MovieListModule.class)
public interface MovieListComponent {
    void inject(MovieListFragment fragment);
}
