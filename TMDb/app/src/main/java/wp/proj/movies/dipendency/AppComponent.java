package wp.proj.movies.dipendency;

import javax.inject.Singleton;

import dagger.Component;
import wp.proj.movies.common.GlideSetting;
import wp.proj.movies.dipendency.feature.tvshowdetails.TvShowDetailComponent;
import wp.proj.movies.dipendency.feature.tvshowdetails.TvShowDetailModule;
import wp.proj.movies.dipendency.feature.tvshowlisting.TvShowListingComponent;
import wp.proj.movies.dipendency.feature.tvshowlisting.TvShowListingModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(GlideSetting glideSetting);

    TvShowListingComponent plus(TvShowListingModule homeModule);

    TvShowDetailComponent plus(TvShowDetailModule homeModule);
}
