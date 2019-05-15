package com.example.movies.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

public class DaddySwipeRefreshLayout extends SwipeRefreshLayout {

    private ScrollUpApplicableListener mScrollUpApplicableListener;

    public DaddySwipeRefreshLayout(Context context) {
        super(context);
    }

    public DaddySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canChildScrollUp() {
        if (mScrollUpApplicableListener != null) {
            return mScrollUpApplicableListener.isScrollUpApplicable();
        }
        return super.canChildScrollUp();
    }

    public void setCanChildScrollUpCallback(ScrollUpApplicableListener scrollUpApplicableListener) {
        mScrollUpApplicableListener = scrollUpApplicableListener;
    }

    public interface ScrollUpApplicableListener {
        boolean isScrollUpApplicable();
    }
}
