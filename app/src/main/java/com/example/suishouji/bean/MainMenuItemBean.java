package com.example.suishouji.bean;

/**
 * Created by Administrator on 2016/8/24.
 */
public class MainMenuItemBean {
    private String bookName;
    private int imageId;

    public MainMenuItemBean(String bookName, int imageId) {
        this.bookName = bookName;
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "MainMenuItemBean{" +
                "bookName='" + bookName + '\'' +
                ", imageId=" + imageId +
                '}';
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
