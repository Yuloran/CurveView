package com.yuloran.curveview.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.yuloran.curveview.R;
import com.yuloran.curveview.model.bean.HourWeather;
import com.yuloran.curveview.widget.curve.CurveView;

import java.util.List;

public class MainActivity extends Activity implements IMainContract.IView {

    private CurveView mCurveView;
    private SeekBar mSeekBar;

    private IMainContract.IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mPresenter = new MainPresenter(this);
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
    public void show(final List<HourWeather> hourWeathers) {
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                mCurveView.drawDefaultStyle(hourWeathers);
            }
        });
    }
}
