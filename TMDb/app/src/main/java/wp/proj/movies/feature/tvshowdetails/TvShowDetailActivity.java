package wp.proj.movies.feature.tvshowdetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.f2prateek.dart.InjectExtra;

import org.parceler.Parcels;

import wp.proj.movies.R;
import wp.proj.movies.common.mvp.PresenterActivity;
import wp.proj.movies.model.TvShow;


public class TvShowDetailActivity extends PresenterActivity {

    @InjectExtra("selectedTvShow")
    TvShow tvShow;

    public static void open(Activity activity, TvShow tvShow) {
        Intent startIntent = new Intent(activity, TvShowDetailActivity.class);
        startIntent.putExtra("selectedTvShow", Parcels.wrap(tvShow));
        activity.startActivity(startIntent);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_tv_show_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.root, TvShowDetailFragment.newInstance(tvShow), TvShowDetailFragment.TAG)
                    .commit();
        }
    }
}
