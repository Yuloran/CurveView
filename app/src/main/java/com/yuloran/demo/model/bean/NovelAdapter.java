package com.yuloran.demo.model.bean;

/**
 * Author & Date: Yuloran, 2018/7/22 21:32
 * Function:
 */
public class NovelAdapter extends AbstractBook {
    private Novel mNovel;

    public NovelAdapter(Novel novel) {
        mNovel = novel;
    }

    @Override
    public String getName() {
        return mNovel.getName();
    }

    @Override
    public int getType() {
        return IBook.ENTERTAINMENT;
    }
}
