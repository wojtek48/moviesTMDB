// Generated code from Butter Knife. Do not modify!
package wp.proj.movies.feature.tvshowdetails;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SingleTvShowDetailFragment$$ViewBinder<T extends wp.proj.movies.feature.tvshowdetails.SingleTvShowDetailFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165269, "field 'rootContainer'");
    target.rootContainer = finder.castView(view, 2131165269, "field 'rootContainer'");
    view = finder.findRequiredView(source, 2131165244, "field 'contentLayout'");
    target.contentLayout = finder.castView(view, 2131165244, "field 'contentLayout'");
    view = finder.findRequiredView(source, 2131165240, "field 'mCoverImage'");
    target.mCoverImage = finder.castView(view, 2131165240, "field 'mCoverImage'");
    view = finder.findRequiredView(source, 2131165245, "field 'detailItemHighLightLayout'");
    target.detailItemHighLightLayout = finder.castView(view, 2131165245, "field 'detailItemHighLightLayout'");
    view = finder.findRequiredView(source, 2131165316, "field 'tvShowDetailTitle'");
    target.tvShowDetailTitle = finder.castView(view, 2131165316, "field 'tvShowDetailTitle'");
    view = finder.findRequiredView(source, 2131165315, "field 'tvShowDetailRating'");
    target.tvShowDetailRating = finder.castView(view, 2131165315, "field 'tvShowDetailRating'");
    view = finder.findRequiredView(source, 2131165313, "field 'tvShowDetailAirTime'");
    target.tvShowDetailAirTime = finder.castView(view, 2131165313, "field 'tvShowDetailAirTime'");
    view = finder.findOptionalView(source, 2131165314, null);
    target.tvShowDetailOverview = finder.castView(view, 2131165314, "field 'tvShowDetailOverview'");
  }

  @Override public void unbind(T target) {
    target.rootContainer = null;
    target.contentLayout = null;
    target.mCoverImage = null;
    target.detailItemHighLightLayout = null;
    target.tvShowDetailTitle = null;
    target.tvShowDetailRating = null;
    target.tvShowDetailAirTime = null;
    target.tvShowDetailOverview = null;
  }
}
