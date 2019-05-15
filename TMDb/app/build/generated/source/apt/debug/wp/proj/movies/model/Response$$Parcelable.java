
package wp.proj.movies.model;

import java.util.ArrayList;
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
public class Response$$Parcelable
    implements Parcelable, ParcelWrapper<wp.proj.movies.model.Response>
{

    private wp.proj.movies.model.Response response$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Creator<Response$$Parcelable>CREATOR = new Creator<Response$$Parcelable>() {


        @Override
        public Response$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new Response$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public Response$$Parcelable[] newArray(int size) {
            return new Response$$Parcelable[size] ;
        }

    }
    ;

    public Response$$Parcelable(wp.proj.movies.model.Response response$$2) {
        response$$0 = response$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(response$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(wp.proj.movies.model.Response response$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(response$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(response$$1));
            parcel$$1 .writeInt(response$$1 .totalShows);
            if (response$$1 .tvShows == null) {
                parcel$$1 .writeInt(-1);
            } else {
                parcel$$1 .writeInt(response$$1 .tvShows.size());
                for (wp.proj.movies.model.TvShow tvShow$$0 : response$$1 .tvShows) {
                    wp.proj.movies.model.TvShow$$Parcelable.write(tvShow$$0, parcel$$1, flags$$0, identityMap$$0);
                }
            }
            parcel$$1 .writeInt(response$$1 .totalPages);
            parcel$$1 .writeInt(response$$1 .page);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public wp.proj.movies.model.Response getParcel() {
        return response$$0;
    }

    public static wp.proj.movies.model.Response read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            wp.proj.movies.model.Response response$$4;
            int reservation$$0 = identityMap$$1 .reserve();
            response$$4 = new wp.proj.movies.model.Response();
            identityMap$$1 .put(reservation$$0, response$$4);
            response$$4 .totalShows = parcel$$3 .readInt();
            int int$$0 = parcel$$3 .readInt();
            ArrayList<wp.proj.movies.model.TvShow> list$$0;
            if (int$$0 < 0) {
                list$$0 = null;
            } else {
                list$$0 = new ArrayList<wp.proj.movies.model.TvShow>(int$$0);
                for (int int$$1 = 0; (int$$1 <int$$0); int$$1 ++) {
                    wp.proj.movies.model.TvShow tvShow$$1 = wp.proj.movies.model.TvShow$$Parcelable.read(parcel$$3, identityMap$$1);
                    list$$0 .add(tvShow$$1);
                }
            }
            response$$4 .tvShows = list$$0;
            response$$4 .totalPages = parcel$$3 .readInt();
            response$$4 .page = parcel$$3 .readInt();
            wp.proj.movies.model.Response response$$3 = response$$4;
            identityMap$$1 .put(identity$$1, response$$3);
            return response$$3;
        }
    }

}
