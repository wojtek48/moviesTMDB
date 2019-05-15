// Generated code from Butter Knife. Do not modify!
package wp.proj.movies.feature.tvshowlisting;

import android.content.res.Resources;
import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TvShowRecyclerViewAdapter$ItemTvShowViewHolder$$ViewBinder<T extends wp.proj.movies.feature.tvshowlisting.TvShowRecyclerViewAdapter.ItemTvShowViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165317, "field 'mContentContainer'");
    target.mContentContainer = view;
    view = finder.findRequiredView(source, 2131165319, "field 'mImageView'");
    target.mImageView = finder.castView(view, 2131165319, "field 'mImageView'");
    view = finder.findRequiredView(source, 2131165318, "field 'mFooterView'");
    target.mFooterView = view;
    view = finder.findRequiredView(source, 2131165320, "field 'mTitleView'");
    target.mTitleView = finder.castView(view, 2131165320, "field 'mTitleView'");
    view = finder.findRequiredView(source, 2131165311, "field 'mRatingView'");
    target.mRatingView = finder.castView(view, 2131165311, "field 'mRatingView'");
    Resources res = finder.getContext(source).getResources();
    target.mColorBackground = res.getColor(2130968620);
    target.mColorTitle = res.getColor(2130968675);
    target.mColorRating = res.getColor(2130968659);
  }

  @Override public void unbind(T target) {
    target.mContentContainer = null;
    target.mImageView = null;
    target.mFooterView = null;
    target.mTitleView = null;
    target.mRatingView = null;
  }
}
