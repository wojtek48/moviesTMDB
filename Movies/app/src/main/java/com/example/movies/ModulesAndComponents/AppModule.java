package com.example.movies.ModulesAndComponents;

import android.app.Application;

import com.example.movies.ApiConnection.ApiService;
import com.example.movies.view.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


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
