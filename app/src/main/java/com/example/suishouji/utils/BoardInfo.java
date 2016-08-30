package com.example.suishouji.utils;

/**
 * Created by Administrator on 2016/8/29.
 */
public class BoardInfo {
    public final static String[] boardMonth = {"本月收入",
            "本月支出",
            "预算余额",
            "本月结余"};
    public final static String[] boardYear ={ "总资产",
            "总负债",
            "净资产"};
    public static String getBoardText(int type){
        if (type >= boardMonth.length && (type - boardMonth.length)<boardYear.length){
            return BoardInfo.boardYear[(type-boardMonth.length)];
        }else if (type >= 0 && type<boardMonth.length){
            return BoardInfo.boardMonth[type];
        }
        return null;
    }

    /**
     * 用于列表时获得数据；
     * @param position
     * @return
     */
    public static int getBoardYearTypeSub(int position){
        int boardYearPosition = position - boardMonth.length;
        return boardYearPosition;
    }

    public static int getBoardYearTypeAdd(int position){
        return position+boardMonth.length;
    }
    public static int getBoarMonthType(int position){
        return position;
    }
}
