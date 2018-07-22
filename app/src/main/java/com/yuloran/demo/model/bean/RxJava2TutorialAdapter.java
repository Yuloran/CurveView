package com.yuloran.demo.model.bean;

/**
 * Author & Date: Yuloran, 2018/7/22 21:32
 * Function:
 */
public class RxJava2TutorialAdapter extends AbstractBook {
    private RxJava2Tutorial mRxJava2Tutorial;

    public RxJava2TutorialAdapter(RxJava2Tutorial rxJava2Tutorial) {
        mRxJava2Tutorial = rxJava2Tutorial;
    }

    @Override
    public String getName() {
        return mRxJava2Tutorial.getTitle();
    }

    @Override
    public int getType() {
        return IBook.EDUCATION;
    }
}
