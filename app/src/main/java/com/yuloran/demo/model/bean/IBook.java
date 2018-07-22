package com.yuloran.demo.model.bean;

/**
 * Author & Date: Yuloran, 2018/7/22 21:31
 * Function:
 */
public interface IBook {
    /**
     * for entertainment
     */
    int ENTERTAINMENT = 1;

    /**
     * for education
     */
    int EDUCATION = 2;

    /**
     * get book name
     *
     * @return the book name
     */
    String getName();

    /**
     * get book type
     *
     * @return {@link #ENTERTAINMENT} or {@link #EDUCATION}
     */
    int getType();
}
