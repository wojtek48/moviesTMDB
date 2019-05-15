package wp.proj.movies.feature.tvshowlisting;

import android.os.Bundle;

import wp.proj.movies.R;
import wp.proj.movies.common.mvp.PresenterActivity;

public class TvShowListingActivity extends PresenterActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_tv_show_listing;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.root, TvShowListingFragment.newInstance(), TvShowListingFragment.TAG)
                    .commit();
        }
    }
//    @Override
    public void recreate() {
                    getSupportFragmentManager().beginTransaction()
                    .replace(R.id.root, TvShowListingFragment.newInstance(), TvShowListingFragment.TAG)
                     .commit();
    }
}
