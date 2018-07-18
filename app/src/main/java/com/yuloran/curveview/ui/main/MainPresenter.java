package com.yuloran.curveview.ui.main;

import com.orhanobut.logger.Logger;
import com.yuloran.curveview.model.CurveModel;
import com.yuloran.curveview.model.bean.HourWeather;

import java.util.List;

/**
 * Author & Date: Yuloran, 2017/9/9 22:53
 * Function: 曲线绘制实现类
 */

public class MainPresenter implements IMainContract.IPresenter {

    private IMainContract.IView mView;

    private CurveModel mModel;

    private boolean mIsStarted;

    MainPresenter(IMainContract.IView view) {
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
    public void onLoadSuccess(List<HourWeather> hourWeathers) {
        mView.show(hourWeathers);
    }

    @Override
    public void onLoadFailed(String s) {
        Logger.d(s);
    }

}
