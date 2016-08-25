package com.example.suishouji.bean;

/**
 * Created by Administrator on 2016/8/25.
 */
public class CostBean {
    private float money;
    private String month ;
    private String time;

    @Override
    public String toString() {
        return "CostBean{" +
                "money=" + money +
                ", month='" + month + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CostBean(float money, String month, String time) {
        this.money = money;
        this.month = month;
        this.time = time;
    }
}
