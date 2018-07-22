package com.yuloran.demo.model.bean;

/**
 * Author & Date: Yuloran, 2018/7/22 21:29
 * Function:
 */
public class RxJava2Tutorial {
    /**
     * 书名
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "RxJava2Tutorial{" + "title='" + title + '\'' + ", author='" + author + '\'' + '}';
    }
}
