package com.yuloran.demo.base;

import android.support.annotation.NonNull;

/**
 * Author & Date: Yuloran, 2017/9/10 19:47
 * Function:
 */

public interface IBaseView<T> {
    void show(@NonNull T t);
}
