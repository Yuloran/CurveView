package com.yuloran.curveview.contract;

/**
 * Author & Date: Yuloran, 2017/9/10 19:48
 * Function:
 */

public interface BasePresenter<T, E> {
    void start();

    void cancel();

    boolean isStarted();

    void onLoadSuccess(T t);

    void onLoadFailed(E e);
}
