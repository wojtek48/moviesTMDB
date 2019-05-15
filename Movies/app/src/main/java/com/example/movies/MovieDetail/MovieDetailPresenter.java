package com.example.movies.MovieDetail;

import com.example.movies.ApiConnection.ApiService;
import com.example.movies.ApiConnection.Callback;
import com.example.movies.Base.BasePresenter;
import com.example.movies.model.Response;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
 * Created by Tushar on 6/14/17.
 */

public class MovieDetailPresenter extends BasePresenter<MovieDetailView> {

    private ApiService apiService;

    private int pageNumber = 1;

    private Subscriber subscriber;

    public MovieDetailPresenter(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void bindView(MovieDetailView view) {
        super.bindView(view);
        if (!wasViewCreated) {
            if (view != null) {
                view.movieProgress();
                loadSimilarMovies();
            }
        }
    }

    public void loadSimilarMoviesWhenError() {
        view.movieProgress();
        loadSimilarMovies();
    }

    private void loadSimilarMovies() {
        apiService.getSimilarMovies(
                String.valueOf(view.getUserSelectedMovieId()),
                "eae426df77219f6528405b00e8508d02",
                "en-US",
                pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getSubscriber());
    }

    private Observer<Response> getSubscriber() {
        subscriber = new Callback<Response>() {
            @Override
            public void onSuccess(Response model) {
                if (view != null) {
                    view.hideProgress();
                    view.movieSimilarMovies(model.movies);
                }
            }

            @Override
            public void onFailure(String message) {
                if (view != null) {
                    view.hideProgressWithError(message);
                }
            }

            @Override
            public void onFinish() {

            }
        };
        return subscriber;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }
}
