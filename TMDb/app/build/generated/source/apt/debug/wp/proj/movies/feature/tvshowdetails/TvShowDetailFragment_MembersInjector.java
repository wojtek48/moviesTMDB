// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package wp.proj.movies.feature.tvshowdetails;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class TvShowDetailFragment_MembersInjector
    implements MembersInjector<TvShowDetailFragment> {
  private final Provider<TvShowDetailPresenter> presenterProvider;

  public TvShowDetailFragment_MembersInjector(Provider<TvShowDetailPresenter> presenterProvider) {
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  public static MembersInjector<TvShowDetailFragment> create(
      Provider<TvShowDetailPresenter> presenterProvider) {
    return new TvShowDetailFragment_MembersInjector(presenterProvider);
  }

  @Override
  public void injectMembers(TvShowDetailFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.presenter = presenterProvider.get();
  }

  public static void injectPresenter(
      TvShowDetailFragment instance, Provider<TvShowDetailPresenter> presenterProvider) {
    instance.presenter = presenterProvider.get();
  }
}