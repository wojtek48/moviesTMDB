package com.example.movies.Base;

public interface PresenterCache {
    long generateId();

    <T extends Presenter> T getPresenter(long index);

    void setPresenter(long index, Presenter presenter);
}
