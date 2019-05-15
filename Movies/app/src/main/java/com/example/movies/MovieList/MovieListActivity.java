package com.example.movies.MovieList;

import android.os.Bundle;

import com.example.movies.Base.PresenterActivity;
import com.example.movies.R;

public class MovieListActivity extends PresenterActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_tv_movie_listing;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.root, MovieListFragment.newInstance(), MovieListFragment.TAG)
                    .commit();
        }
    }
}
