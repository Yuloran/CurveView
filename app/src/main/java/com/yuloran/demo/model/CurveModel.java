package com.yuloran.demo.model;

import com.yuloran.demo.base.IBaseModel;
import com.yuloran.demo.model.bean.HourWeather;
import com.yuloran.demo.ui.curveview.CurveViewPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author & Date: Yuloran, 2017/9/10 19:55
 * Function:
 */

public class CurveModel implements IBaseModel {
    private CurveViewPresenter mPresenter;

    private Random mRandom;

    public CurveModel(CurveViewPresenter presenter) {
        this.mPresenter = presenter;
        mRandom = new Random();
    }

    @Override
    public void start() {
        List<HourWeather> hourWeathers = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            HourWeather p = new HourWeather();
            p.setTemperature(mRandom.nextInt(200) + 100);
            p.setHour(i);
            hourWeathers.add(p);
        }
        mPresenter.onLoadSuccess(hourWeathers);
    }

    @Override
    public void cancel() {

    }
}
