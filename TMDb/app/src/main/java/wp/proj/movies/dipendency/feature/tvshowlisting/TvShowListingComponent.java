package wp.proj.movies.dipendency.feature.tvshowlisting;

import javax.inject.Singleton;

import dagger.Subcomponent;
import wp.proj.movies.feature.tvshowlisting.TvShowListingFragment;


@Singleton
@Subcomponent(modules = TvShowListingModule.class)
public interface TvShowListingComponent {
    void inject(TvShowListingFragment fragment);
}
