package com.example.suishouji.utils;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.media.TransportMediator;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 获得日期
 * Created by Administrator on 2016/8/30.
 */
public class DateUtils {
    private int weeks = 0;// 用来全局控制 上一周，本周，下一周的周数变化
    private Date mDate;

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(long time) {
        mDate.setTime(time);
    }

    public DateUtils() {
        this.mDate = new Date();
    }

    public DateUtils(long time) {
        this.mDate = new Date(time);
    }

    private  Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        return calendar;
    }

    @NonNull
    private GregorianCalendar getGregorianCalendar() {
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.setGregorianChange(mDate);
        return currentDate;
    }
    /**
     * 获得当天日期
     * @param dateformat
     * @return
     */
    public String getNowTime(String dateformat) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
        String hehe = dateFormat.format(now);
        return hehe;
    }

    /**
     * 获得当前是一周的第几天；
     * @return
     */
    private int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        cd.setTime(mDate);
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    /**
     * 获得本周的第一天日期毫秒值
     * @return
     */
    public long getMondayOfWeekTime(){
        weeks = 0;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = getGregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        return monday.getTime();
    }
    /**
     * 获得本周的第一天日期
     * @return
     */
    public String getMondayOFWeek() {
        weeks = 0;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = getGregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();

        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }
    /**
     * 获得本周的第一天日期
     * @return
     */
    public static String getMondayOFWeek0() {

        int weeks = 0;
        int mondayPlus;
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            mondayPlus = 0;
        } else {
            mondayPlus = 1 - dayOfWeek;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");

        String preMonday = simpleDateFormat.format(monday);

        return preMonday;
    }

    /**
    * * 获得本周最后一天
    * @return
    */
    public long getCurrentWeekDayTime(){
        weeks = 0;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = getGregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();
        return monday.getTime();
    }

    /**
     * 获得本周的最后一天
     * @return
     */
    public String getCurrentWeekday() {
        weeks = 0;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = getGregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();

        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }
    /**
     * 获得本周的最后一天
     * @return
     */
    public static String getCurrentWeekday0() {
        int weeks = 0;
        int mondayPlus ;
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            mondayPlus = 0;
        } else {
            mondayPlus = 1 - dayOfWeek;
        };
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();

        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("MM月dd日");
        String preMonday = simpleDateFormat.format(monday);

        /*DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);*/
        return preMonday;
    }


    /**
     * 获得本月第一天的毫秒值
     * @return
     */
    public long getFirstDayOfMonthTime(){
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar lastDate = getCalendar();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        return lastDate.getTime().getTime();
    }

    /**
     * 获得本月的第一天
     * @return
     */
    public String getFirstDayOfMonth() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");

        Calendar lastDate = getCalendar();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        str = sdf.format(lastDate.getTime());
        return str;
    }
    public static String getFirstDayOfMonth0() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        str = sdf.format(lastDate.getTime());
        return str;
    }
    /**
     * 获得本月的最后一天的日期毫秒值；
     * @return
     */
    public long getDefaultDayTime(){
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar lastDate = getCalendar();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        return lastDate.getTime().getTime();
    }


    public String getWeek(){
        return getCurrentWeekday() + "-" + getMondayOFWeek();
    }

    /**
     * 获得本月的最后一天的日期
     * @return
     */
    public String getDefaultDay() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");

        Calendar lastDate = getCalendar();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

        str = sdf.format(lastDate.getTime());
        return str;
    }
    /**
     * 获得本月的最后一天的日期
     * @return
     */
    public static String getDefaultDay0() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");

        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

        str = sdf.format(lastDate.getTime());
        return str;
    }


    /**
     * 获得本年的第一天事件
     * @return
     */
    public long getCurrentYearFirstTime(){
        int yearPlus = this.getYearPlus();
        GregorianCalendar currentDate = getGregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, yearPlus);
        Date yearDay = currentDate.getTime();
        return yearDay.getTime();
    }

    /**
     * 获得本年的第一天的日期
     * @return
     */
    public String getCurrentYearFirst() {
        int yearPlus = this.getYearPlus();
        GregorianCalendar currentDate = getGregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, yearPlus);
        Date yearDay = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preYearDay = df.format(yearDay);
        return preYearDay;
    }

    private int getYearPlus() {
        Calendar cd = getCalendar();
        int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
        cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
        cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
        int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
        if (yearOfNumber == 1) {
            return -MaxYear;
        } else {
            return 1 - yearOfNumber;
        }
    }

    /**
     * 获得本年最后一天
     * @return
     */
    public long getCurrentYearEndTime(){
        long time = 0;
        Date date = mDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
        String years = dateFormat.format(date);
        String timeStr = years+"-12-31-23-59-59";
        try {
            Calendar calendar = getCalendar();
            Date times = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").parse(timeStr);
            time = times.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 获得本年的最后一天；
     * @return
     */
    public String getCurrentYearEnd() {
        Date date = mDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
        String years = dateFormat.format(date);
        return years + "-12-31";
    }

    /**
     * 获得当前时间在年里的天数
     * @return
     */
    public int getDayOfYear() {
        return getCalendar().get(Calendar.DAY_OF_YEAR);

    }

    /**
     * 当前时间在本月里的天数；
     * @return
     */
    public int getDayOfMonth() {
        return getCalendar().get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 当前时间在本周的天数
     * @return
     */
    public  int getDayOfWeek() {
        return getCalendar().get(Calendar.DAY_OF_WEEK);
    }
}
