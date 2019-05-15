// Generated code from Dart. Do not modify!
package wp.proj.movies.feature.tvshowdetails;

import com.f2prateek.dart.Dart;
import java.lang.Object;

public class TvShowDetailActivity$$ExtraInjector {
  public static void inject(Dart.Finder finder, TvShowDetailActivity target, Object source) {
    Object object;
    object = finder.getExtra(source, "selectedTvShow");
    if (object == null) {
      throw new IllegalStateException("Required extra with key 'selectedTvShow' for field 'tvShow' was not found. If this extra is optional add '@Nullable' annotation.");
    }
    target.tvShow = org.parceler.Parcels.unwrap((android.os.Parcelable) object);
  }
}
