package com.yuloran.demo.model.bean;

/**
 * Author & Date: Yuloran, 2018/7/22 21:25
 * Function:
 */
public class Novel {
    /**
     * 编号
     */
    private int serialNumber;

    /**
     * 书名
     */
    private String name;

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Novel{" + "serialNumber=" + serialNumber + ", name='" + name + '\'' + '}';
    }
}
