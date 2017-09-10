package com.yuloran.curveview.model;

import com.yuloran.curveview.contract.BaseModel;
import com.yuloran.curveview.contract.presenter.curve.CurveDrawPresenter;
import com.yuloran.curveview.model.bean.HourTemperature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author & Date: Yuloran, 2017/9/10 19:55
 * Function:
 */

public class CurveModel implements BaseModel {
    private CurveDrawPresenter mPresenter;

    private Random mRandom;

    public CurveModel(CurveDrawPresenter presenter) {
        this.mPresenter = presenter;
        mRandom = new Random();
    }

    @Override
    public void start() {
        List<HourTemperature> hourTemperatures = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            HourTemperature p = new HourTemperature();
            p.setTemperature(mRandom.nextInt(200) + 100);
            p.setHour(i);
            hourTemperatures.add(p);
        }
        mPresenter.onLoadSuccess(hourTemperatures);
    }

    @Override
    public void cancel() {

    }
}
