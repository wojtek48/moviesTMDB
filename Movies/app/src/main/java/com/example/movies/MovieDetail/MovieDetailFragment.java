package com.example.movies.MovieDetail;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.movies.Base.BasePresenterFragment;
import com.example.movies.ModulesAndComponents.MovieDetails.MovieDetailModule;
import com.example.movies.R;
import com.example.movies.model.Movie;
import com.f2prateek.dart.InjectExtra;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class MovieDetailFragment extends BasePresenterFragment<MovieDetailView, MovieDetailPresenter> implements MovieDetailView {
    public static final String TAG = MovieDetailFragment.class.getName();

    private static final String STATE_SELECTED_TV_SHOW = "selected_tv_movie";
    private static final String STATE_LOADING = "state_loading";
    private static final String STATE_ERROR = "state_error";
    private static final String STATE_TV_SHOWS = "state_movies";
    private static final String STATE_SELECTED_POSITION = "state_selected_position";

    @InjectExtra(STATE_SELECTED_TV_SHOW)
    Movie selectedMovie;

    @Inject
    MovieDetailPresenter presenter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.tv_movie_view_pager)
    ViewPager MovieViewPager;

    @BindView(R.id.layout_error)
    LinearLayout errorLayout;

    private List<Movie> Movies = new ArrayList<>();

    private ScaleResponsiblePagerAdapter scaleResponsiblePagerAdapter;

    private int mSelectedPosition = -1;

    private boolean isLoading;

    private boolean isError;

    public static MovieDetailFragment newInstance(Movie movie) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(STATE_SELECTED_TV_SHOW, Parcels.wrap(movie));

        MovieDetailFragment MovieDetailFragment = new MovieDetailFragment();
        MovieDetailFragment.setArguments(arguments);
        return MovieDetailFragment;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_tv_movie_details;
    }

    @NonNull
    @Override
    public MovieDetailPresenter onCreatePresenter() {
        app.getAppComponent()
                .plus(new MovieDetailModule())
                .inject(this);
        return presenter;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_LOADING, isLoading);
        outState.putParcelable(STATE_TV_SHOWS, Parcels.wrap(Movies));
        outState.putBoolean(STATE_ERROR, isError);
        outState.putInt(STATE_SELECTED_POSITION, MovieViewPager.getCurrentItem());
//        Timber.tag(TAG).d("tv movie size %d", Movies.size());
//        Timber.tag(TAG).d("current position %d", MovieViewPager.getCurrentItem());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            if (!savedInstanceState.getBoolean(STATE_LOADING)
                    && !savedInstanceState.getBoolean(STATE_ERROR)) {
                Movies = Parcels.unwrap(savedInstanceState.getParcelable(STATE_TV_SHOWS));
                mSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION, -1);
//                Timber.tag(TAG).d("tv movie size %d, current position %d", Movies.size(), MovieViewPager.getCurrentItem());
                hideProgress();
            } else if (savedInstanceState.getBoolean(STATE_LOADING)) {
                movieProgress();
            } else {
                hideProgressWithError("");
            }
        }

        initMovieViewPager();
    }

    private void initMovieViewPager() {
        scaleResponsiblePagerAdapter = new ScaleResponsiblePagerAdapter(getActivity(),
                Movies, getActivity().getSupportFragmentManager());
        int pageMargin = ((Resources.getSystem().getDisplayMetrics().widthPixels / 10) * 2);
        MovieViewPager.setPageMargin(-pageMargin);
        MovieViewPager.setAdapter(scaleResponsiblePagerAdapter);
        MovieViewPager.addOnPageChangeListener(scaleResponsiblePagerAdapter);
        if (mSelectedPosition != -1) {
            MovieViewPager.setCurrentItem(mSelectedPosition, true);
            if (MovieViewPager.getVisibility() == View.GONE) {
                MovieViewPager.setVisibility(View.VISIBLE);
            }
//            Timber.tag(TAG).d("current position %d", MovieViewPager.getCurrentItem());
        }
    }

    @Override
    public void movieProgress() {
        isLoading = true;
        getActivity().runOnUiThread(() -> {
            MovieViewPager.setVisibility(View.GONE);
            errorLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void hideProgressWithError(String reason) {
//        Timber.tag(TAG).d("hide progress with error");
        isError = true;
        isLoading = false;
        getActivity().runOnUiThread(() -> {
            errorLayout.setVisibility(View.VISIBLE);
            MovieViewPager.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        });
    }

    @Override
    public void hideProgress() {
        isError = false;
        isLoading = false;
        getActivity().runOnUiThread(() -> {
            MovieViewPager.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            errorLayout.setVisibility(View.GONE);
        });
    }

    @Override
    public void movieSimilarMovies(List<Movie> similarMovies) {
        Movies.add(selectedMovie);
        Movies.addAll(similarMovies);
        scaleResponsiblePagerAdapter.notifyDataSetChanged();
    }

    @Override
    public long getUserSelectedMovieId() {
        return selectedMovie.getId();
    }

    @OnClick(R.id.layout_error)
    void onClickError() {
        getPresenter().loadSimilarMoviesWhenError();
    }
}
