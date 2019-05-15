package wp.proj.movies.feature.tvshowlisting;

import android.view.View;

import wp.proj.movies.model.TvShow;


public interface OnTvShowClickListener {
    void onTvShowSelected(View selectedView, TvShow tvShow, int position);
}
