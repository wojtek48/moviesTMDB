// Generated code from Butter Knife. Do not modify!
package wp.proj.movies.feature.tvshowlisting;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TvShowListingFragment$$ViewBinder<T extends wp.proj.movies.feature.tvshowlisting.TvShowListingFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165297, "field 'mSwipeRefreshLayout'");
    target.mSwipeRefreshLayout = finder.castView(view, 2131165297, "field 'mSwipeRefreshLayout'");
    view = finder.findRequiredView(source, 2131165312, "field 'mTvShowRecyclerView'");
    target.mTvShowRecyclerView = finder.castView(view, 2131165312, "field 'mTvShowRecyclerView'");
    view = finder.findRequiredView(source, 2131165262, "field 'mProgressBar'");
    target.mProgressBar = finder.castView(view, 2131165262, "field 'mProgressBar'");
    view = finder.findRequiredView(source, 2131165246, "field 'errorLayout' and method 'onClickError'");
    target.errorLayout = finder.castView(view, 2131165246, "field 'errorLayout'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClickError();
        }
      });
    view = finder.findRequiredView(source, 2131165298, "field 'mSwitcher'");
    target.mSwitcher = finder.castView(view, 2131165298, "field 'mSwitcher'");
  }

  @Override public void unbind(T target) {
    target.mSwipeRefreshLayout = null;
    target.mTvShowRecyclerView = null;
    target.mProgressBar = null;
    target.errorLayout = null;
    target.mSwitcher = null;
  }
}
