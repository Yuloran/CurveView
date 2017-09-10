package com.yuloran.curveview.contract.presenter.curve;

import com.orhanobut.logger.Logger;
import com.yuloran.curveview.contract.IMainActivityContract;
import com.yuloran.curveview.model.CurveModel;
import com.yuloran.curveview.model.bean.HourTemperature;

import java.util.List;

/**
 * Author & Date: Yuloran, 2017/9/9 22:53
 * Function: 曲线绘制实现类
 */

public class CurveDrawPresenter implements IMainActivityContract.ICurveDrawPresenter {

    private IMainActivityContract.ICurveView mView;

    private CurveModel mModel;

    private boolean mIsStarted;

    public CurveDrawPresenter(IMainActivityContract.ICurveView view) {
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
    public void onLoadSuccess(List<HourTemperature> hourTemperatures) {
        mView.show(hourTemperatures);
    }

    @Override
    public void onLoadFailed(String s) {
        Logger.d(s);
    }

}
