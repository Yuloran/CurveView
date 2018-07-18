package com.yuloran.curveview.widget.curve;

import android.graphics.Path;
import android.graphics.PointF;

import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Author & Date: Yuloran, 2017/9/10 0:11
 * Function:
 */

public class BezierAlgorithm {

    public static void calculate(List<PointF> mappedPoints, float sharpenRatio, Path path) {
        if (mappedPoints.size() < 3) {
            throw new IllegalArgumentException("The size of mappedPoints must not less than 3!");
        }

        Logger.d("sharpenRatio: " + sharpenRatio);

        PointF pMidOfLm = new PointF();
        PointF pMidOfMr = new PointF();

        PointF cache = null;

        for (int i = 0; i <= mappedPoints.size() - 3; i++) {
            PointF pL = mappedPoints.get(i);
            PointF pM = mappedPoints.get(i + 1);
            PointF pR = mappedPoints.get(i + 2);

            pMidOfLm.x = (pL.x + pM.x) / 2.0f;
            pMidOfLm.y = (pL.y + pM.y) / 2.0f;

            pMidOfMr.x = (pM.x + pR.x) / 2.0f;
            pMidOfMr.y = (pM.y + pR.y) / 2.0f;

            float lengthOfLm = (float) Math.hypot(pM.x - pL.x, pM.y - pL.y);
            float lengthOfMr = (float) Math.hypot(pR.x - pM.x, pR.y - pM.y);

            float ratio = (lengthOfLm / (lengthOfLm + lengthOfMr)) * sharpenRatio;
            float oneMinusRatio = (1 - ratio) * sharpenRatio;

            Logger.d("ratio:%f, oneMinusRatio:%f.", ratio, oneMinusRatio);

            float dx = pMidOfLm.x - pMidOfMr.x;
            float dy = pMidOfLm.y - pMidOfMr.y;

            PointF cLeft = new PointF();
            cLeft.x = pM.x + dx * ratio;
            cLeft.y = pM.y + dy * ratio;

            PointF cRight = new PointF();
            cRight.x = pM.x + -dx * oneMinusRatio;
            cRight.y = pM.y + -dy * oneMinusRatio;

            if (i == 0) {
                PointF pMidOfLCLeft = new PointF((pL.x + cLeft.x) / 2.0f, (pL.y + cLeft.y) / 2.0f);
                PointF pMidOfCLeftM = new PointF((cLeft.x + pM.x) / 2.0f, (cLeft.y + pM.y) / 2.0f);

                float length1 = (float) Math.hypot(cLeft.x - pL.x, cLeft.y - pL.y);
                float length2 = (float) Math.hypot(pM.x - cLeft.x, pM.y - cLeft.y);

                ratio = (length1 / (length1 + length2)) * sharpenRatio;
                PointF first = new PointF();
                first.x = cLeft.x + (pMidOfLCLeft.x - pMidOfCLeftM.x) * ratio;
                first.y = cLeft.y + (pMidOfLCLeft.y - pMidOfCLeftM.y) * ratio;

                path.cubicTo(first.x, first.y, cLeft.x, cLeft.y, pM.x, pM.y);
            } else {
                path.cubicTo(cache.x, cache.y, cLeft.x, cLeft.y, pM.x, pM.y);
            }

            cache = cRight;

            if (i == mappedPoints.size() - 3) {
                PointF pMidOfMCRight = new PointF((pM.x + cRight.x) / 2.0f, (pM.y + cRight.y) / 2.0f);
                PointF pMidOfCRightR = new PointF((pR.x + cRight.x) / 2.0f, (pR.y + cRight.y) / 2.0f);

                float length1 = (float) Math.hypot(cRight.x - pM.x, cRight.y - pM.y);
                float length2 = (float) Math.hypot(pR.x - cRight.x, pR.y - cRight.y);
                ratio = (length2 / (length1 + length2)) * sharpenRatio;

                PointF last = new PointF();
                last.x = cRight.x + (pMidOfCRightR.x - pMidOfMCRight.x) * ratio;
                last.y = cRight.y + (pMidOfCRightR.y - pMidOfMCRight.y) * ratio;

                path.cubicTo(cRight.x, cRight.y, last.x, last.y, pR.x, pR.y);
            }

            Logger.d("cLeft:%s, cRight:%s.", cLeft, cRight);
        }
    }
}
