package wp.proj.movies.feature.tvshowlisting;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import wp.proj.movies.BuildConfig;
import wp.proj.movies.api.ApiService;
import wp.proj.movies.api.NetworkCallback;
import wp.proj.movies.common.App;
import wp.proj.movies.common.mvp.BasePresenter;
import wp.proj.movies.model.Response;


public class TvShowListingPresenter extends BasePresenter<TvShowListingView> {

    private ApiService apiService;

    private int pageNumber = 1;

    private Subscriber subscriber;


    public TvShowListingPresenter(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void bindView(TvShowListingView view) {
        super.bindView(view);

        if (!wasViewCreated) {
            if (view != null) {
                view.showProgress();
                loadTvShows();
            }
        }
    }

    private void loadTvShows() {
        boolean isMovie = App.preferences.getBoolean( "isChecked", false );
        if(!isMovie)
        apiService.getPopularMovies(BuildConfig.TMDB_API_KEY, "en-US", pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createSubscriber());
        else
            apiService.getPopularTvShows(BuildConfig.TMDB_API_KEY, "en-US", pageNumber)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(createSubscriber());
    }

    private Observer<Response> createSubscriber() {
        subscriber = new NetworkCallback<Response>() {
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
                        view.hideTvShowsLoadingProgress();
                    }
                    view.showTvShows(model.tvShows);
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
                            view.showTvShowLoadingError();
                        }
                    } else {
                        view.hideTvShowsLoadingProgress();
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
        loadTvShows();
        view.showTvShowsLoadingProgress();
    }

    public void reload(boolean isMovie) {
        pageNumber = 1;
        loadTvShows();
    }

    public void reloadWhenError() {
        pageNumber = 1;
        view.showProgress();
        loadTvShows();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }
}
