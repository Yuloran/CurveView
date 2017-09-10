package com.yuloran.curveview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.yuloran.curveview.R;
import com.yuloran.curveview.contract.presenter.curve.BezierAlgorithm;
import com.yuloran.curveview.contract.presenter.curve.CurveStyle;
import com.yuloran.curveview.model.bean.HourTemperature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author & Date: Yuloran, 2017/9/10 0:11
 * Function:
 */

public class CurveView extends View {
    private CurveStyle mStyle;

    private int mWidth;

    private int mHeight;

    private List<HourTemperature> mOriginalPoints = new ArrayList<>();

    private List<PointF> mMappedPoints = new ArrayList<>();

    private Path mPath = new Path();

    private Paint mPaint = new Paint();

    public CurveView(Context context) {
        this(context, null);
    }

    public CurveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CurveView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CurveView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        CurveStyle.Builder builder = new CurveStyle.Builder();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CurveView);
        mStyle = builder.setPaintColor(a.getInt(R.styleable.CurveView_paint_color, -1))
                .setPaintWidth(a.getFloat(R.styleable.CurveView_paint_width, -1))
                .setCurvature(a.getFloat(R.styleable.CurveView_curvature, -1))
                .create();

        a.recycle();

        mPaint.setAntiAlias(true);
        mPaint.setColor(mStyle.getPaintColor());
        mPaint.setStrokeWidth(mStyle.getPaintWidth());
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mWidth = getWidth();
        mHeight = getHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (mWidth == 0 || mHeight == 0) {
            return;
        }

        if (mMappedPoints.isEmpty()) {
            return;
        }

        mPaint.setColor(Color.BLUE);
        canvas.drawPath(mPath, mPaint);

        for (PointF p : mMappedPoints) {
            mPaint.setColor(Color.WHITE);
            canvas.drawCircle(p.x, p.y, 10, mPaint);
        }
    }

    public void drawDefaultStyle(List<HourTemperature> originalPoints) {
        if (mWidth == 0 || mHeight == 0) {
            return;
        }

        mapHourTemperature(originalPoints);

        mPath.reset();
        PointF first = mMappedPoints.get(0);
        mPath.moveTo(first.x, first.y);

        BezierAlgorithm.calculate(mMappedPoints, 1.0f, mPath);

        invalidate();
    }

    public void changeSharpenRatio(float ratio) {
        if (mWidth == 0 || mHeight == 0) {
            return;
        }

        if (mMappedPoints.isEmpty()) {
            return;
        }

        if (ratio < 0 || ratio > 1) {
            return;
        }

        mPath.reset();
        PointF first = mMappedPoints.get(0);
        mPath.moveTo(first.x, first.y);

        BezierAlgorithm.calculate(mMappedPoints, ratio, mPath);

        invalidate();
    }

    private void mapHourTemperature(List<HourTemperature> originalPoints) {
        if (mWidth == 0 || mHeight == 0) {
            return;
        }

        // step1.
        mOriginalPoints.clear();
        mOriginalPoints.addAll(originalPoints);

        // step2.
        Collections.sort(originalPoints);

        // step3.
        int min = originalPoints.get(0).getTemperature();
        int size = originalPoints.size();
        int max = originalPoints.get(size - 1).getTemperature();
        float unit = (max - min) / (float) (mHeight - 100);
        int interval = (mWidth - 100) / (size - 1);
        Logger.d("min:%d, max:%d, unit:%f, interval:%d.", min, max, unit, interval);

        mMappedPoints.clear();
        int centerVertical = mHeight / 2;
        for (int i = 0; i < size; i++) {
            HourTemperature hourTemperature = mOriginalPoints.get(i);
            PointF point = new PointF();
            point.x = 50 + i * interval;
            if (unit == 0) {
                point.y = centerVertical;
            } else {
                point.y = (int) ((max - hourTemperature.getTemperature()) * unit) + 50;
            }
            mMappedPoints.add(point);
        }
        Logger.d("mOriginalPoints:\n" + mOriginalPoints);
        Logger.d("mMappedPoints:\n" + mMappedPoints);
    }

}
