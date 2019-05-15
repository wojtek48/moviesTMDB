package com.example.movies.MovieList;

import android.content.Context;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movies.Base.BaseRecyclerViewAdapter;
import com.example.movies.R;
import com.example.movies.model.Movie;
import com.github.florent37.glidepalette.GlidePalette;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;



public class MovieRecyclerViewAdapter extends BaseRecyclerViewAdapter<Movie, RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_PROGRESS = 1;

    private Context mContext;
    private OnMovieClickListener mOnMovieClickListener;

    public MovieRecyclerViewAdapter(Context context, List<Movie> Movies, OnMovieClickListener listener) {
        super(Movies);

        this.mContext = context;
        this.mOnMovieClickListener = listener;
        setHasStableIds(true);
    }

    @Override
    public int getItemViewType(int position) {
        return objects.get(position).equals(ProgressMovie.INSTANCE) ? VIEW_TYPE_PROGRESS : VIEW_TYPE_ITEM;
    }

    @Override
    public long getItemId(int position) {
        return objects.get(position).equals(ProgressMovie.INSTANCE) ? -1 : objects.get(position).getId();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder;
        if (viewType == VIEW_TYPE_ITEM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_movie, parent, false);
            viewHolder = new ItemMovieViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more, parent, false);
            viewHolder = new ProgressViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemMovieViewHolder) {
            ((ItemMovieViewHolder) holder).bind(position);
        }
    }

    public class ItemMovieViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        @BindView(R.id.tv_movie_item_container)
        View mContentContainer;

        @BindView(R.id.tv_movie_item_image)
        ImageView mImageView;

        @BindView(R.id.tv_movie_item_footer)
        View mFooterView;

        @BindView(R.id.tv_movie_item_title)
        TextView mTitleView;

        @BindView(R.id.tv_Movie_item_rating)
        TextView mRatingView;

        @BindColor(R.color.colorPrimary)
        int mColorBackground;

        @BindColor(R.color.title_text_color)
        int mColorTitle;

        @BindColor(R.color.rating_text_color)
        int mColorRating;

        Movie mMovie;

        long mMovieId;

        public ItemMovieViewHolder(View view) {
            super(view);
            this.view = view;

            ButterKnife.bind(this, view);
        }

        public void bind(final int position) {
            mMovie = objects.get(position);
            mTitleView.setText(mMovie.getTitle());
            mRatingView.setText(mContext.getString(R.string.tv_rating, mMovie.getVoteAverage()));

            if (mMovieId != mMovie.getId()) {
                resetColors();
                mMovieId = mMovie.getId();
            }

            Glide.with(mContext)
                    .load(mMovie.getPosterPath())
                    .crossFade()
                    .placeholder(R.color.tv_movie_poster_placeholder)
                    .listener(GlidePalette.with(mMovie.getPosterPath())
                            .intoCallBack(palette -> applyColors(palette.getVibrantSwatch())))
                    .into(mImageView);

            mContentContainer.setOnClickListener(v -> {
                if (mOnMovieClickListener != null) {
                    mOnMovieClickListener.onMovieSelected(mContentContainer,
                            mMovie, position);
                }
            });
        }

        private void resetColors() {
            mFooterView.setBackgroundColor(mColorBackground);
            mTitleView.setTextColor(mColorTitle);
            mRatingView.setTextColor(mColorRating);
        }

        private void applyColors(Palette.Swatch swatch) {
            if (swatch != null) {
                mFooterView.setBackgroundColor(swatch.getRgb());
                mTitleView.setTextColor(swatch.getBodyTextColor());
                mRatingView.setTextColor(swatch.getTitleTextColor());
            }
        }
    }

    private class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressViewHolder(View view) {
            super(view);
        }
    }
}
