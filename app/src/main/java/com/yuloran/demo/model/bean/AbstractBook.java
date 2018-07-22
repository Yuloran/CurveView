package com.yuloran.demo.model.bean;

/**
 * Author & Date: Yuloran, 2018/7/22 21:51
 * Function:
 */
public abstract class AbstractBook implements IBook {
    @Override
    public String toString() {
        return "Book{" + "name='" + getName() + "', type='" + prettyType(getType()) + "'}";
    }

    private String prettyType(int type) {
        if (type == ENTERTAINMENT) {
            return "entertainment";
        }
        return "education";
    }
}
