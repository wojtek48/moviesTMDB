package com.example.movies.MovieList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.movies.Base.PresenterActivity;
import com.example.movies.MovieDetail.MovieDetailFragment;
import com.example.movies.R;
import com.example.movies.model.Movie;
import com.f2prateek.dart.InjectExtra;

import org.parceler.Parcels;



public class MovieDetailActivity extends PresenterActivity {

    @InjectExtra("selectedMovie")
    Movie movie;

    public static void open(Activity activity, Movie Movie) {
        Intent startIntent = new Intent(activity, MovieDetailActivity.class);
        startIntent.putExtra("selectedMovie", Parcels.wrap(Movie));
        activity.startActivity(startIntent);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_tv_show_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.root, MovieDetailFragment.newInstance(movie), MovieDetailFragment.TAG)
                    .commit();
        }
    }
}
