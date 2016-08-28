package com.example.suishouji.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class AddAccountBookBean {
        private String bookName;
        private String content;
        private int icon;
        private int iconType;
        private int textColor;

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    @Override
        public String toString() {
            return "BeanBean{" +
                    "bookName='" + bookName + '\'' +
                    ", content='" + content + '\'' +
                    ", icon=" + icon +
                    ", iconType=" + iconType +
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
