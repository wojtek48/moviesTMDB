package wp.proj.movies.feature.tvshowlisting;

import java.util.List;

import wp.proj.movies.model.TvShow;

public interface RefreshTvShowListener {
    void onRefreshStart();

    void onRefreshSuccess(List<TvShow> tvShows);

    void onRefreshFailure(String reason);
}