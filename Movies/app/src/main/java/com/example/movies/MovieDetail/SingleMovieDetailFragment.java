package com.example.movies.MovieDetail;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movies.Base.BaseFragment;
import com.example.movies.R;
import com.example.movies.model.Movie;
import com.f2prateek.dart.InjectExtra;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;

public class SingleMovieDetailFragment extends BaseFragment {

    private static final String STATE_MOVIE = "movie";
    private static final String POSITION = "position";
    private static final String SCALE = "scale";

    private int screenWidth;
    private int screenHeight;
    private int position;
    private float scale;

    @InjectExtra(STATE_MOVIE)
    Movie movie;

    @BindView(R.id.root_container)
    ScaleResponsibleSlideLayout rootContainer;

    @BindView(R.id.layout_content)
    RelativeLayout contentLayout;

    @BindView(R.id.img_tv_movie_cover)
    ImageView mCoverImage;

    @BindView(R.id.layout_detail_item_highlight)
    LinearLayout detailItemHighLightLayout;

    @BindView(R.id.tv_movie_detail_title)
    TextView MovieDetailTitle;

    @BindView(R.id.tv_movie_detail_rating)
    TextView MovieDetailRating;

    @BindView(R.id.tv_movie_detail_air_time)
    TextView MovieDetailAirTime;

    @Nullable
    @BindView(R.id.tv_movie_detail_overview)
    TextView MovieDetailOverview;

    public static Fragment newInstance(int pos, Movie Movie, float scale) {
        Bundle arguments = new Bundle();
        arguments.putInt(POSITION, pos);
        arguments.putFloat(SCALE, scale);
        arguments.putParcelable(STATE_MOVIE, Parcels.wrap(Movie));
        SingleMovieDetailFragment fragment = new SingleMovieDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.item_tv_show_details;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (movie != null) {
            outState.putParcelable(STATE_MOVIE, Parcels.wrap(movie));
            outState.putInt(POSITION, position);
            outState.putFloat(SCALE, scale);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        position = this.getArguments().getInt(POSITION);
        scale = this.getArguments().getFloat(SCALE);

        if (savedInstanceState != null) {
            movie = Parcels.unwrap(savedInstanceState.getParcelable(STATE_MOVIE));
//            Timber.tag("on retain").d("single tv movie position %d", position);
        }

        setContent();
    }

    @SuppressLint("SetTextI18n")
    private void setContent() {
//        Timber.tag("on single tv movie").d("single tv movie position %d", position);

        int marginWidth = ((Resources.getSystem().getDisplayMetrics().widthPixels / 10) * 2);
        int marginHeight = ((Resources.getSystem().getDisplayMetrics().heightPixels / 8) * 2);

        contentLayout.getLayoutParams().width = screenWidth - marginWidth;
        contentLayout.getLayoutParams().height = screenHeight - marginHeight;
        contentLayout.requestLayout();

        Glide.with(getActivity()).load(movie.getBackdropPath())
                .placeholder(R.color.tv_movie_poster_placeholder)
                .centerCrop()
                .crossFade()
                .listener(GlidePalette.with(movie.getBackdropPath())
                        .use(GlidePalette.Profile.MUTED_DARK)
                        .intoBackground(detailItemHighLightLayout)
                        .intoTextColor(MovieDetailTitle, BitmapPalette.Swatch.BODY_TEXT_COLOR)
                        .intoTextColor(MovieDetailAirTime, BitmapPalette.Swatch.TITLE_TEXT_COLOR))
                .into(mCoverImage);

        MovieDetailTitle.setText(movie.getTitle());
        MovieDetailRating.setText(getString(R.string.tv_rating, movie.getVoteAverage()));
        String date = movie.getFirstAirDate();
        if (TextUtils.isEmpty(date)) {
            date = "";
        }
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date));
            date =  calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + calendar.get(Calendar.YEAR);
        } catch (ParseException e) {
            date =  "";
        }
        MovieDetailAirTime.setText(getString(R.string.dot) + " " + date);

        if (MovieDetailOverview != null) {
            MovieDetailOverview.setText(movie.getOverview());
        }

        rootContainer.setScale(scale);
    }
}
