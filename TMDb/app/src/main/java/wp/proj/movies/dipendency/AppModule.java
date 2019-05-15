package wp.proj.movies.dipendency;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import wp.proj.movies.api.ApiService;
import wp.proj.movies.common.App;


@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApp() {
        return (App) application;
    }

    @Provides
    @Singleton
    ApiService provideApiService(App app) {
        return app.getApiService();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(App app) {
        return app.provideOkHttpClient();
    }
}
