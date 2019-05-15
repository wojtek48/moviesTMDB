package com.example.movies.ApiConnection;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public abstract class Callback<M> extends Subscriber<M> {

    private static final String TAG = Callback.class.getName();

    public abstract void onSuccess(M model);

    public abstract void onFailure(String message);

    public abstract void onFinish();

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            String message = httpException.getMessage();
            onFailure(message);
        } else {
            onFailure(e.getMessage());
        }
        onFinish();
    }

    @Override
    public void onNext(M model) {
        onSuccess(model);
    }

    @Override
    public void onCompleted() {
        onFinish();
    }
}