package com.yuloran.curveview.contract;

import com.yuloran.curveview.model.bean.HourTemperature;

import java.util.List;

/**
 * Author & Date: Yuloran, 2017/9/9 22:46
 * Function:
 */

public interface IMainActivityContract {
    interface ICurveView extends BaseView<List<HourTemperature>> {
    }

    interface ICurveDrawPresenter extends BasePresenter<List<HourTemperature>, String> {
    }
}
