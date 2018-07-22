package com.yuloran.demo.ui.curveview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.SeekBar;

import com.yuloran.demo.R;
import com.yuloran.demo.model.bean.HourWeather;
import com.yuloran.demo.widget.curve.CurveView;

import java.util.List;

public class CurveViewActivity extends Activity implements ICurveViewContract.IView {

    private CurveView mCurveView;
    private SeekBar mSeekBar;

    private ICurveViewContract.IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curve_view);

        initViews();

        mPresenter = new CurveViewPresenter(this);
    }

    private void initViews() {
        mCurveView = (CurveView) findViewById(R.id.curve_view);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = mSeekBar.getProgress();
                float ratio = (float) progress / 100;
                mCurveView.changeSharpenRatio(ratio);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mPresenter.isStarted()) {
            mPresenter.start();
        }
    }

    @Override
    public void show(@NonNull final List<HourWeather> hourWeathers) {
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                mCurveView.drawDefaultStyle(hourWeathers);
            }
        });
    }
}
