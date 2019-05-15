package wp.proj.movies.dipendency.feature.tvshowdetails;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import wp.proj.movies.api.ApiService;
import wp.proj.movies.feature.tvshowdetails.TvShowDetailPresenter;


@Module
public class TvShowDetailModule {
    @Provides
    @Singleton
    public TvShowDetailPresenter provideTvShowDetailPresenter(ApiService apiService) {
        return new TvShowDetailPresenter(apiService);
    }
}
