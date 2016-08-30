package com.example.suishouji.bean;

import com.example.suishouji.utils.LookBoardBeanInfo;

/**
 * Created by Administrator on 2016/8/23.
 */
public class MainLookBoardBean {

    /**
     * styleType标记 0 时间流水，1 理财，2 超级流水；
     */
    private int styleType;

    /**
     * type本身特有的标记；0. 今天，1 本周，2 本月，3 本年
     * 4 理财钱包 ，5 应收应付 ，6 投资收支 ，7 全部时间
     * 8 日常收支；
     */
    private int type;
    private int icon;
    private String title = "";
    private String content= "";
    private String expendNumber= "0.00";
    private String incomeNumber= "0.00";


    private long startTime;
    private long endTime;

    public MainLookBoardBean() {
    }

    public MainLookBoardBean(int type) {
        this.setType(type);
    }

    public int getStyleType() {
        return styleType;
    }

    public void setStyleType(int styleType) {
        this.styleType = styleType;
    }

    public int getType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
        this.title = LookBoardBeanInfo.titles[type];

        this.icon = LookBoardBeanInfo.getIcon(type);

        String content = LookBoardBeanInfo.getContent(type);
        this.content = content;

        int styleType = LookBoardBeanInfo.getStyleType(type);
        this.styleType = styleType;


    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExpendNumber() {
        return expendNumber;
    }

    public void setExpendNumber(String expendNumber) {
        this.expendNumber = expendNumber;
    }

    public String getIncomeNumber() {
        return incomeNumber;
    }

    public void setIncomeNumber(String incomeNumber) {
        this.incomeNumber = incomeNumber;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "MainLookBoardBean{" +
                "styleType=" + styleType +
                ", type=" + type +
                ", icon=" + icon +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", expendNumber='" + expendNumber + '\'' +
                ", incomeNumber='" + incomeNumber + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
