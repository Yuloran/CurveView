package com.yuloran.demo.ui.curveview;

import com.yuloran.demo.base.IBasePresenter;
import com.yuloran.demo.base.IBaseView;
import com.yuloran.demo.model.bean.HourWeather;

import java.util.List;

/**
 * Author & Date: Yuloran, 2017/9/9 22:46
 * Function:
 */

public interface ICurveViewContract {
    interface IView extends IBaseView<List<HourWeather>> {
    }

    interface IPresenter extends IBasePresenter<List<HourWeather>, String> {
    }
}
