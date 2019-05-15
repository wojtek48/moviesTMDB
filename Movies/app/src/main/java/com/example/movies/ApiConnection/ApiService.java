package com.example.movies.ApiConnection;

import com.example.movies.model.Response;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiService {
    String URL = "https://api.themoviedb.org/3/movie/";

    @GET("popular")
    Observable<Response> getPopularMovies(@Query("api_key") String apiKey,
                                          @Query("language") String language,
                                          @Query("page") int pageNumber);

    @GET("{id}/similar")
    Observable<Response> getSimilarMovies(@Path("id") String movieId,
                                           @Query("api_key") String apiKey,
                                           @Query("language") String language,
                                           @Query("page") int pageNumber);
}

