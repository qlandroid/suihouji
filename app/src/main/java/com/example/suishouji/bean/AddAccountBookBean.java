package com.example.suishouji.bean;

import android.graphics.Color;

import com.example.suishouji.utils.BoardInfo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/26.
 */
public class AddAccountBookBean implements Serializable{
    private static final long serialVersionUID = 12345678L;
    private String bookName;
    private String content;
    private int icon;
    private int iconType;
    private int textColor = Color.WHITE;
    private int faceBoardType0 = 1;
    private int faceBoardType1 = 0;
    private int faceBoardType2 = 2;

    private String boardNumber0 = "0.00";
    private String boardNumber1 = "0.00";
    private String boardNumber2 = "0.00";

    public String getBoardNumber0() {
        return boardNumber0;
    }

    public void setBoardNumber0(String boardNumber0) {
        this.boardNumber0 = boardNumber0;
    }

    public String getBoardNumber1() {
        return boardNumber1;
    }

    public void setBoardNumber1(String boardNumber1) {
        this.boardNumber1 = boardNumber1;
    }

    public String getBoardNumber2() {
        return boardNumber2;
    }

    public void setBoardNumber2(String boardNumber2) {
        this.boardNumber2 = boardNumber2;
    }

    public int getFaceBoardType0() {
        return faceBoardType0;
    }

    public int getFaceBoardType1() {
        return faceBoardType1;
    }

    public int getFaceBoardType2() {
        return faceBoardType2;
    }

    public String getFaceBoard_0() {
        return BoardInfo.getBoardText(faceBoardType0);
    }

    public void setFaceBoardType0(int type) {
        this.faceBoardType0 = type;
    }

    public String getFaceBoard_1() {
        return BoardInfo.getBoardText(faceBoardType1);
    }

    public void setFaceBoardType1(int type) {
        this.faceBoardType1 = type;
    }

    public String getFaceBoard_2() {
        return BoardInfo.getBoardText(faceBoardType2);
    }

    public void setFaceBoardType2(int type) {
        this.faceBoardType2 = type;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    @Override
    public String toString() {
        return "AddAccountBookBean{" +
                "bookName='" + bookName + '\'' +
                ", content='" + content + '\'' +
                ", icon=" + icon +
                ", iconType=" + iconType +
                ", textColor=" + textColor +
                ", faceBoardType0=" + faceBoardType0 +
                ", faceBoardType1=" + faceBoardType1 +
                ", faceBoardType2=" + faceBoardType2 +
                '}';
    }

    public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public int getIconType() {
            return iconType;
        }

        public void setIconType(int iconType) {
            this.iconType = iconType;
        }

}
