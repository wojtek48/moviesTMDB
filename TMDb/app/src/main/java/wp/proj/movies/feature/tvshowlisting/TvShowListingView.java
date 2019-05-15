package wp.proj.movies.feature.tvshowlisting;

import java.util.List;

import wp.proj.movies.model.TvShow;


public interface TvShowListingView {
    void showProgress();

    void hideProgress();

    void showTvShowsLoadingProgress();

    void hideTvShowsLoadingProgress();

    void showTvShows(List<TvShow> tvShows);

    void hideErrors();

    void showTvShowLoadingError(String reason);

    void showTvShowLoadingError();

    boolean isRefreshing();

    void hideRefreshing();
}
