
package wp.proj.movies.common.view;

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
public class EndlessRecyclerOnScrollListener$State$$Parcelable
    implements Parcelable, ParcelWrapper<wp.proj.movies.common.view.EndlessRecyclerOnScrollListener.State>
{

    private wp.proj.movies.common.view.EndlessRecyclerOnScrollListener.State state$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Creator<EndlessRecyclerOnScrollListener$State$$Parcelable>CREATOR = new Creator<EndlessRecyclerOnScrollListener$State$$Parcelable>() {


        @Override
        public EndlessRecyclerOnScrollListener$State$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new EndlessRecyclerOnScrollListener$State$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public EndlessRecyclerOnScrollListener$State$$Parcelable[] newArray(int size) {
            return new EndlessRecyclerOnScrollListener$State$$Parcelable[size] ;
        }

    }
    ;

    public EndlessRecyclerOnScrollListener$State$$Parcelable(wp.proj.movies.common.view.EndlessRecyclerOnScrollListener.State state$$2) {
        state$$0 = state$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(state$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(wp.proj.movies.common.view.EndlessRecyclerOnScrollListener.State state$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(state$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(state$$1));
            parcel$$1 .writeInt(state$$1 .startingPageIndex);
            parcel$$1 .writeInt(state$$1 .previousTotalItemCount);
            parcel$$1 .writeInt(state$$1 .currentPage);
            parcel$$1 .writeInt((state$$1 .loading? 1 : 0));
            parcel$$1 .writeInt(state$$1 .visibleThreshold);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public wp.proj.movies.common.view.EndlessRecyclerOnScrollListener.State getParcel() {
        return state$$0;
    }

    public static wp.proj.movies.common.view.EndlessRecyclerOnScrollListener.State read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            wp.proj.movies.common.view.EndlessRecyclerOnScrollListener.State state$$4;
            int reservation$$0 = identityMap$$1 .reserve();
            state$$4 = new wp.proj.movies.common.view.EndlessRecyclerOnScrollListener.State();
            identityMap$$1 .put(reservation$$0, state$$4);
            state$$4 .startingPageIndex = parcel$$3 .readInt();
            state$$4 .previousTotalItemCount = parcel$$3 .readInt();
            state$$4 .currentPage = parcel$$3 .readInt();
            state$$4 .loading = (parcel$$3 .readInt() == 1);
            state$$4 .visibleThreshold = parcel$$3 .readInt();
            wp.proj.movies.common.view.EndlessRecyclerOnScrollListener.State state$$3 = state$$4;
            identityMap$$1 .put(identity$$1, state$$3);
            return state$$3;
        }
    }

}
