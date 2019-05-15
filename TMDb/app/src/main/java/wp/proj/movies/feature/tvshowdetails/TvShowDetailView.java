package wp.proj.movies.feature.tvshowdetails;

import java.util.List;

import wp.proj.movies.model.TvShow;



interface TvShowDetailView {
    void showProgress();

    void hideProgressWithError(String reason);

    void hideProgress();

    void showSimilarShows(List<TvShow> similarTvShows);

    long getUserSelectedTvShowId();
}
