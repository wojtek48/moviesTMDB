package com.example.movies.MovieDetail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.example.movies.R;
import com.example.movies.model.Movie;

import java.util.List;

public class ScaleResponsiblePagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    public final static float BIG_SLIDE = 1f;
    public final static float SMALL_SLIDE = 0.90f;
    public final static float DIFF_SLIDE = BIG_SLIDE - SMALL_SLIDE;
    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    private List<Movie> movies;

    private float scale;

    public ScaleResponsiblePagerAdapter(Context context, List<Movie> movies, FragmentManager fm) {
        super(fm);
        this.movies = movies;
    }

    @Override
    public Fragment getItem(int position) {
        scale = SMALL_SLIDE;
        return SingleMovieDetailFragment.newInstance(position, movies.get(position), scale);
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        Timber.d("position: %4f", positionOffset);
//        Timber.d("position no: ", position);

        try {
            if (positionOffset >= 0f && positionOffset <= 1f) {
                ScaleResponsibleSlideLayout cur = (ScaleResponsibleSlideLayout) getRegisteredFragment(position).getView().findViewById(R.id.root_container);
                ScaleResponsibleSlideLayout next = (ScaleResponsibleSlideLayout) getRegisteredFragment(position + 1).getView().findViewById(R.id.root_container);
                cur.setScale(BIG_SLIDE - DIFF_SLIDE * positionOffset);
                next.setScale(SMALL_SLIDE + DIFF_SLIDE * positionOffset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}