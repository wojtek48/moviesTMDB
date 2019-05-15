// Generated code from Dart. Do not modify!
package wp.proj.movies.feature.tvshowdetails;

import com.f2prateek.dart.Dart;
import java.lang.Object;

public class TvShowDetailFragment$$ExtraInjector {
  public static void inject(Dart.Finder finder, TvShowDetailFragment target, Object source) {
    Object object;
    object = finder.getExtra(source, "selected_tv_show");
    if (object == null) {
      throw new IllegalStateException("Required extra with key 'selected_tv_show' for field 'selectedTvShow' was not found. If this extra is optional add '@Nullable' annotation.");
    }
    target.selectedTvShow = org.parceler.Parcels.unwrap((android.os.Parcelable) object);
  }
}
