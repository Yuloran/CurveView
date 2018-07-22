package com.yuloran.demo.base;

import android.support.annotation.NonNull;

/**
 * Author & Date: Yuloran, 2017/9/10 19:48
 * Function:
 */

public interface IBasePresenter<T, E> {
    void start();

    void cancel();

    boolean isStarted();

    void onLoadSuccess(@NonNull T t);

    void onLoadFailed(E e);
}
