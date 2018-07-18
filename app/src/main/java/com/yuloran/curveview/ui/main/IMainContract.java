package com.yuloran.curveview.ui.main;

import com.yuloran.curveview.base.IBasePresenter;
import com.yuloran.curveview.base.IBaseView;
import com.yuloran.curveview.model.bean.HourWeather;

import java.util.List;

/**
 * Author & Date: Yuloran, 2017/9/9 22:46
 * Function:
 */

public interface IMainContract {
    interface IView extends IBaseView<List<HourWeather>> {
    }

    interface IPresenter extends IBasePresenter<List<HourWeather>, String> {
    }
}
