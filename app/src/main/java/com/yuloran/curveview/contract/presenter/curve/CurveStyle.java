package com.yuloran.curveview.contract.presenter.curve;

import android.content.res.Resources;
import android.graphics.Color;
import android.util.TypedValue;

/**
 * Author & Date: Yuloran, 2017/9/9 22:54
 * Function:
 */

public class CurveStyle {
    private int mPaintColor = Color.BLUE;

    private float mPaintWidth;

    /**
     * 曲率[0.0f~1.0f]
     */
    private float mCurvature;

    {
        mPaintWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.5f, Resources.getSystem()
                .getDisplayMetrics());
        mCurvature = 1.0f;
    }

    private CurveStyle() {
    }

    public int getPaintColor() {
        return mPaintColor;
    }

    public float getPaintWidth() {
        return mPaintWidth;
    }

    public float getCurvature() {
        return mCurvature;
    }

    public static class Builder {
        private int mPaintColor = -1;

        private float mPaintWidth = -1;

        /**
         * 曲率[0.0f~1.0f]
         */
        private float mCurvature = -1;

        public Builder() {
        }

        /**
         * 设置画笔颜色
         *
         * @param color 16进制表示的色值，如0xFFFFFFFF
         * @return Builder
         */
        public Builder setPaintColor(int color) {
            mPaintColor = color;
            return this;
        }

        /**
         * 设置画笔粗细
         *
         * @param width 单位：dp
         * @return Builder
         */
        public Builder setPaintWidth(float width) {
            mPaintWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, Resources.getSystem()
                    .getDisplayMetrics());
            return this;
        }

        public Builder setCurvature(float curvature) {
            mCurvature = curvature;
            return this;
        }

        public CurveStyle create() {
            CurveStyle style = new CurveStyle();
            if (mPaintColor >= 0) {
                style.mPaintColor = mPaintColor;
            }
            if (mPaintWidth >= 0) {
                style.mPaintWidth = mPaintWidth;
            }
            if (mCurvature >= 0.0f && mCurvature <= 1.0f) {
                style.mCurvature = mCurvature;
            }
            return style;
        }

    }

}
