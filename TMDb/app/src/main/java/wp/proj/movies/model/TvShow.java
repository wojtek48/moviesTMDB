package wp.proj.movies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class TvShow {

    @Expose
    long id;

    @Expose
    @SerializedName("name")
    String title;

    @Expose
    String overview;

    @Expose
    @SerializedName("poster_path")
    String posterPath;

    @Expose
    @SerializedName("backdrop_path")
    String backdropPath;

    @Expose
    @SerializedName("vote_average")
    double voteAverage;

    @Expose
    @SerializedName("first_air_date")
    String firstAirDate;

    @Expose
    @SerializedName("release_date")
    String releaseDate;

    @Expose
    @SerializedName("original_title")
    String originalTitle;

    public TvShow() {
    }

    public long getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getTitle() {
        if (title == null)
            return originalTitle;
        return title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getFirstAirDate() {
        if(firstAirDate == null)
            return releaseDate;
        return firstAirDate;
    }
}
