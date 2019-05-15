// Generated code from Butter Knife. Do not modify!
package wp.proj.movies.feature.tvshowdetails;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TvShowDetailFragment$$ViewBinder<T extends wp.proj.movies.feature.tvshowdetails.TvShowDetailFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165262, "field 'progressBar'");
    target.progressBar = finder.castView(view, 2131165262, "field 'progressBar'");
    view = finder.findRequiredView(source, 2131165321, "field 'tvShowViewPager'");
    target.tvShowViewPager = finder.castView(view, 2131165321, "field 'tvShowViewPager'");
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
  }

  @Override public void unbind(T target) {
    target.progressBar = null;
    target.tvShowViewPager = null;
    target.errorLayout = null;
  }
}
