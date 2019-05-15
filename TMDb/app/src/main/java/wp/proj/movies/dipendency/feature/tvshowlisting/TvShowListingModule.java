package wp.proj.movies.dipendency.feature.tvshowlisting;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import wp.proj.movies.api.ApiService;
import wp.proj.movies.feature.tvshowlisting.TvShowListingPresenter;


@Module
public class TvShowListingModule {
    @Provides
    @Singleton
    public TvShowListingPresenter provideTvShowListingPresenter(ApiService apiService) {
        return new TvShowListingPresenter(apiService);
    }
}
