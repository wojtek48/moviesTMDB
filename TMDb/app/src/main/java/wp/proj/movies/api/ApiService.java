package wp.proj.movies.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import wp.proj.movies.BuildConfig;
import wp.proj.movies.model.Response;

public interface ApiService {
    String URL = BuildConfig.TMDB_API_URL;

    @GET("tv/popular")
    Observable<Response> getPopularTvShows(@Query("api_key") String apiKey,
                                           @Query("language") String language,
                                           @Query("page") int pageNumber);

    @GET("movie/popular")
    Observable<Response> getPopularMovies(@Query("api_key") String apiKey,
                                           @Query("language") String language,
                                           @Query("page") int pageNumber);

    @GET("search")
    Observable<Response> getSearch(@Query("api_key") String apiKey,
                                   @Query("language") String language,
                                   @Query("query") String query,
                                   @Query("page") int pageNumber);

    @GET("movie/{id}/similar")
    Observable<Response> getSimilarTvShows(@Path("id") String tvShowId,
                                           @Query("api_key") String apiKey,
                                           @Query("language") String language,
                                           @Query("page") int pageNumber);
}
