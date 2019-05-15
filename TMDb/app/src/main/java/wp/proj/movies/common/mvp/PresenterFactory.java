package wp.proj.movies.common.mvp;

import android.support.annotation.NonNull;

public interface PresenterFactory<T extends Presenter> {
    @NonNull
    T createPresenter();
}
