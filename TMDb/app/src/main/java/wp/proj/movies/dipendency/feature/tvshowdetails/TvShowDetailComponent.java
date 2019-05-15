package wp.proj.movies.dipendency.feature.tvshowdetails;

import javax.inject.Singleton;

import dagger.Subcomponent;
import wp.proj.movies.feature.tvshowdetails.TvShowDetailFragment;


@Singleton
@Subcomponent(modules = TvShowDetailModule.class)
public interface TvShowDetailComponent {
    void inject(TvShowDetailFragment tvShowDetailFragment);
}
