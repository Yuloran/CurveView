package com.yuloran.demo.ui.curveview;

import android.support.annotation.NonNull;

import com.yuloran.demo.model.CurveModel;
import com.yuloran.demo.model.bean.HourWeather;
import com.yuloran.demo.util.Logger;

import java.util.List;

/**
 * Author & Date: Yuloran, 2017/9/9 22:53
 * Function: 曲线绘制实现类
 */

public class CurveViewPresenter implements ICurveViewContract.IPresenter {
    private static final String TAG = CurveViewPresenter.class.getSimpleName();

    private ICurveViewContract.IView mView;

    private CurveModel mModel;

    private boolean mIsStarted;

    CurveViewPresenter(ICurveViewContract.IView view) {
        this.mView = view;
        mModel = new CurveModel(this);
    }

    @Override
    public void start() {
        mModel.start();
        mIsStarted = true;
    }

    @Override
    public void cancel() {
        mModel.cancel();
    }

    @Override
    public boolean isStarted() {
        return mIsStarted;
    }

    @Override
    public void onLoadSuccess(@NonNull List<HourWeather> hourWeathers) {
        mView.show(hourWeathers);
    }

    @Override
    public void onLoadFailed(String s) {
        Logger.d(TAG, s);
    }

}
