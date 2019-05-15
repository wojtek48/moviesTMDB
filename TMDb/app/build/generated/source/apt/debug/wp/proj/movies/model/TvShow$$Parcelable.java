
package wp.proj.movies.model;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated("org.parceler.ParcelAnnotationProcessor")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class TvShow$$Parcelable
    implements Parcelable, ParcelWrapper<wp.proj.movies.model.TvShow>
{

    private wp.proj.movies.model.TvShow tvShow$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Creator<TvShow$$Parcelable>CREATOR = new Creator<TvShow$$Parcelable>() {


        @Override
        public TvShow$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new TvShow$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public TvShow$$Parcelable[] newArray(int size) {
            return new TvShow$$Parcelable[size] ;
        }

    }
    ;

    public TvShow$$Parcelable(wp.proj.movies.model.TvShow tvShow$$2) {
        tvShow$$0 = tvShow$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(tvShow$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(wp.proj.movies.model.TvShow tvShow$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(tvShow$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(tvShow$$1));
            parcel$$1 .writeString(tvShow$$1 .firstAirDate);
            parcel$$1 .writeString(tvShow$$1 .overview);
            parcel$$1 .writeDouble(tvShow$$1 .voteAverage);
            parcel$$1 .writeString(tvShow$$1 .releaseDate);
            parcel$$1 .writeString(tvShow$$1 .originalTitle);
            parcel$$1 .writeLong(tvShow$$1 .id);
            parcel$$1 .writeString(tvShow$$1 .backdropPath);
            parcel$$1 .writeString(tvShow$$1 .title);
            parcel$$1 .writeString(tvShow$$1 .posterPath);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public wp.proj.movies.model.TvShow getParcel() {
        return tvShow$$0;
    }

    public static wp.proj.movies.model.TvShow read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            wp.proj.movies.model.TvShow tvShow$$4;
            int reservation$$0 = identityMap$$1 .reserve();
            tvShow$$4 = new wp.proj.movies.model.TvShow();
            identityMap$$1 .put(reservation$$0, tvShow$$4);
            tvShow$$4 .firstAirDate = parcel$$3 .readString();
            tvShow$$4 .overview = parcel$$3 .readString();
            tvShow$$4 .voteAverage = parcel$$3 .readDouble();
            tvShow$$4 .releaseDate = parcel$$3 .readString();
            tvShow$$4 .originalTitle = parcel$$3 .readString();
            tvShow$$4 .id = parcel$$3 .readLong();
            tvShow$$4 .backdropPath = parcel$$3 .readString();
            tvShow$$4 .title = parcel$$3 .readString();
            tvShow$$4 .posterPath = parcel$$3 .readString();
            wp.proj.movies.model.TvShow tvShow$$3 = tvShow$$4;
            identityMap$$1 .put(identity$$1, tvShow$$3);
            return tvShow$$3;
        }
    }

}
