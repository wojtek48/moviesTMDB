package wp.proj.movies.feature.tvshowlisting;

import java.util.List;

import wp.proj.movies.model.TvShow;

public interface LoadTvShowListener {
    void onTvShowLoadingStart();

    void onTvShowLoadingSuccess(List<TvShow> tvShows);

    void onTvShowLoadingFailure(String reason);
}