package com.example.movies.MovieList;

import com.example.movies.ApiConnection.ApiService;
import com.example.movies.ApiConnection.Callback;
import com.example.movies.Base.BasePresenter;
import com.example.movies.model.Response;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Tushar on 6/13/17.
 */

public class MovieListPresenter extends BasePresenter<MovieListingView> {

    private ApiService apiService;

    private int pageNumber = 1;

    private Subscriber subscriber;

    public MovieListPresenter(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void bindView(MovieListingView view) {
        super.bindView(view);

        if (!wasViewCreated) {
            if (view != null) {
                view.movieProgress();
                loadMovies();
            }
        }
    }

    private void loadMovies() {
        apiService.getPopularMovies("eae426df77219f6528405b00e8508d02", "en-US", pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createSubscriber());
    }

    private Observer<Response> createSubscriber() {
        subscriber = new Callback<Response>() {
            @Override
            public void onSuccess(Response model) {
                if (view != null) {
                    if (pageNumber == 1) {
                        view.hideErrors();
                        if (view.isRefreshing()) {
                            view.hideRefreshing();
                        } else {
                            view.hideProgress();
                        }
                    } else {
                        view.hideMoviesLoadingProgress();
                    }
                    view.movieMovies(model.movies);
                }
            }

            @Override
            public void onFailure(String message) {
                if (view != null) {
                    if (pageNumber == 1) {
                        if (view.isRefreshing()) {
                            view.hideRefreshing();
                        } else {
                            view.hideProgress();
                            view.movieMovieLoadingError();
                        }
                    } else {
                        view.hideMoviesLoadingProgress();
                    }
                }
            }

            @Override
            public void onFinish() {

            }
        };

        return subscriber;
    }

    public void loadMore() {
        pageNumber++;
        loadMovies();
        view.movieMoviesLoadingProgress();
    }

    public void reload() {
        pageNumber = 1;
        loadMovies();
    }

    public void reloadWhenError() {
        pageNumber = 1;
        view.movieProgress();
        loadMovies();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }
}
