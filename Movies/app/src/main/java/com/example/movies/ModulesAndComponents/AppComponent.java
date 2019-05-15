package com.example.movies.ModulesAndComponents;

import com.example.movies.ModulesAndComponents.MovieDetails.MovieDetailComponent;
import com.example.movies.ModulesAndComponents.MovieDetails.MovieDetailModule;
import com.example.movies.ModulesAndComponents.MovieList.MovieListComponent;
import com.example.movies.ModulesAndComponents.MovieList.MovieListModule;
import com.example.movies.view.GlideSettings;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(GlideSettings glideSetting);

    MovieListComponent plus(MovieListModule homeModule);

    MovieDetailComponent plus(MovieDetailModule homeModule);
}
