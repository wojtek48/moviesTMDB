package com.example.movies.MovieList;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.movies.Base.BasePresenterFragment;
import com.example.movies.ModulesAndComponents.MovieList.MovieListModule;
import com.example.movies.R;
import com.example.movies.model.Movie;
import com.example.movies.view.DaddySwipeRefreshLayout;
import com.example.movies.view.EndlessRecyclerOnScrollListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;



public class MovieListFragment extends BasePresenterFragment<MovieListingView, MovieListPresenter>
        implements MovieListingView,
        SwipeRefreshLayout.OnRefreshListener,
        OnMovieClickListener {

    public static final String TAG = MovieListFragment.class.getName();

    private static final String STATE_ERROR = "state_error";
    private static final String STATE_LOADING = "state_loading";
    private static final String STATE_TV_SHOWS = "state_movies";
    private static final String STATE_SELECTED_POSITION = "state_selected_position";

    @BindView(R.id.swipe_refresh_layout)
    DaddySwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.tv_list)
    RecyclerView mMovieRecyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.layout_error)
    LinearLayout errorLayout;

    private GridLayoutManager mGridLayoutManager;

    private MovieRecyclerViewAdapter mMovieRecyclerViewAdapter;

    private EndlessRecyclerOnScrollListener mEndlessRecyclerOnScrollListener;

    private EndlessRecyclerOnScrollListener.State state = new EndlessRecyclerOnScrollListener.State();

    List<Movie> Movies = new ArrayList<>();

    int mSelectedPosition = -1;

    private boolean isError;

    private boolean isLoading;

    @Inject
    MovieListPresenter presenter;

    public static Fragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_tv_movie_listing;
    }

    @NonNull
    @Override
    public MovieListPresenter onCreatePresenter() {
        app.getAppComponent()
                .plus(new MovieListModule())
                .inject(this);
        return presenter;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mMovieRecyclerViewAdapter != null) {
            outState.putBoolean(STATE_LOADING, isLoading);
            outState.putBoolean(STATE_ERROR, isError);
            outState.putParcelable(STATE_TV_SHOWS, Parcels.wrap(mMovieRecyclerViewAdapter.getAllItems()));
            outState.putInt(STATE_SELECTED_POSITION, mSelectedPosition);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            if (!savedInstanceState.getBoolean(STATE_LOADING)
                    && !savedInstanceState.getBoolean(STATE_ERROR)) {
                Movies = Parcels.unwrap(savedInstanceState.getParcelable(STATE_TV_SHOWS));
                mSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION, -1);
            } else if (savedInstanceState.getBoolean(STATE_LOADING)) {
                movieProgress();
            } else {
                movieMovieLoadingError();
            }
        }

        initSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setCanChildScrollUpCallback(new DaddySwipeRefreshLayout.ScrollUpApplicableListener() {
            @Override
            public boolean isScrollUpApplicable() {
                return mMovieRecyclerView != null && ViewCompat.canScrollVertically(mMovieRecyclerView, -1);
            }
        });
    }

    private void initRecyclerView() {
        mMovieRecyclerViewAdapter = new MovieRecyclerViewAdapter(getActivity(), Movies, this);

        mMovieRecyclerView.setAdapter(mMovieRecyclerViewAdapter);
        mGridLayoutManager = new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.tv_movie_columns));
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mMovieRecyclerViewAdapter.getItemViewType(position);

                switch (itemViewType) {
                    case MovieRecyclerViewAdapter.VIEW_TYPE_ITEM:
                        return 1;
                    case MovieRecyclerViewAdapter.VIEW_TYPE_PROGRESS:
                        return getResources().getInteger(R.integer.tv_movie_columns);
                    default:
                        return -1;
                }
            }
        });
        mMovieRecyclerView.setLayoutManager(mGridLayoutManager);

        mEndlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(mMovieRecyclerView) {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                getPresenter().loadMore();
                return true;
            }

            @Override
            public void enableRefresh() {
                if (!mSwipeRefreshLayout.isEnabled()) {
                    mSwipeRefreshLayout.setEnabled(true);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void disableRefresh() {
                if (mSwipeRefreshLayout.isEnabled()) {
                    mSwipeRefreshLayout.setEnabled(false);
                }
            }

            @Override
            public void onScroll() {
                // skip
            }
        };

        mEndlessRecyclerOnScrollListener.setState(state);
        mMovieRecyclerView.addOnScrollListener(mEndlessRecyclerOnScrollListener);
        if (mSelectedPosition != -1) {
            mMovieRecyclerView.scrollToPosition(mSelectedPosition);
        }
    }

    @Override
    public void onRefresh() {
        getPresenter().reload();
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onMovieSelected(View selectedView, Movie Movie, int position) {
        mSelectedPosition = position;
        MovieDetailActivity.open(getActivity(), Movie);
    }

    @Override
    public void movieProgress() {
        isLoading = true;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.VISIBLE);
                errorLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void hideProgress() {
        isLoading = false;
        getActivity().runOnUiThread(() -> {
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
            errorLayout.setVisibility(View.GONE);
        });
    }

    @Override
    public void movieMoviesLoadingProgress() {
        mMovieRecyclerView.post(() -> mMovieRecyclerViewAdapter.add(ProgressMovie.INSTANCE));
    }

    @Override
    public void hideMoviesLoadingProgress() {
        mMovieRecyclerView.post(() -> mMovieRecyclerViewAdapter.remove(ProgressMovie.INSTANCE));
    }

    @Override
    public void movieMovies(List<Movie> Movies) {
        isError = false;
        mMovieRecyclerView.post(() -> mMovieRecyclerViewAdapter.addAll(Movies));
    }

    @Override
    public void hideErrors() {
        errorLayout.setVisibility(View.GONE);
    }

    @Override
    public void movieMovieLoadingError(String reason) {
        // skip
    }

    @Override
    public void movieMovieLoadingError() {
        isError = true;
        getActivity().runOnUiThread(() -> errorLayout.setVisibility(View.VISIBLE));
    }

    @Override
    public boolean isRefreshing() {
        return mSwipeRefreshLayout.isRefreshing();
    }

    @Override
    public void hideRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @OnClick(R.id.layout_error)
    void onClickError() {
        getPresenter().reloadWhenError();
    }
}
