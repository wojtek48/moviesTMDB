package wp.proj.movies.common.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> objects;

    protected boolean notifyOnChange = true;

    protected final Object lock = new Object();

    public BaseRecyclerViewAdapter(List<T> objects) {
        this.objects = objects;
    }

    @Override
    public int getItemCount() {
        return objects != null ? objects.size() : 0;
    }

    public T getItem(int position) {
        if (objects == null) {
            return null;
        }

        return objects.get(position);
    }

    public List<T> getAllItems() {
        return objects;
    }

    public void add(T object) {
        synchronized (lock) {
            if (objects == null) {
                objects = new ArrayList<>();
            }

            objects.add(object);
        }

        if (notifyOnChange) {
            notifyItemInserted(objects.size() - 1);
        }
    }

    public void addAll(Collection<? extends T> collection) {
        if (collection != null) {
            synchronized (lock) {
                if (objects == null) {
                    objects = new ArrayList<>();
                }

                objects.addAll(collection);
            }

            if (notifyOnChange) {
                if (objects.size() - collection.size() != 0) {
                    notifyItemRangeChanged(objects.size() - collection.size(), collection.size());
                } else {
                    notifyDataSetChanged();
                }
            }
        }
    }


    public void remove(T object) {
        int removeIndex;

        synchronized (lock) {
            if (objects == null) {
                return;
            }

            removeIndex = objects.indexOf(object);

            if (removeIndex != -1) {
                objects.remove(removeIndex);
            }
        }

        if (notifyOnChange && removeIndex != -1) {
            notifyDataSetChanged();
        }
    }

}

