package com.example.suishouji.utils;

import com.example.suishouji.R;
import com.example.suishouji.bean.MainLookBoardBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于创建对象
 * Created by Administrator on 2016/8/30.
 */
public class LookBoardBeanInfo {
    /**
     * styleType标记 0 时间流水，1 理财，2 超级流水；
     */

    /**
     * type本身特有的标记；0. 今天，1 本周，2 本月，3 本年
     * 4 理财钱包 ，5日常收支，6 应收应付 ，7投资收支 ，8 全部时间
     *
     */
    public static String[] titles ={"今天","本周","本月","本年","理财钱包","日常收支"
            ,"应收应付","投资收支","全部时间"};
    public static final int STYlE_TYPE_1_INDEX = 4;
    public static final int STYlE_TYPE_2_INDEX = 5;
    private List<MainLookBoardBean> showList;
    private List<MainLookBoardBean> listStyleType0;
    private List<MainLookBoardBean> listStyleType1;
    private List<MainLookBoardBean> listStyleType2;

    private DateUtils dateUtils;
    private int[] showTyps;

    public void destroy(){
        showList.clear();
        showList = null;
        listStyleType0.clear();
        listStyleType1.clear();
        listStyleType2.clear();
        listStyleType0 = null;
        listStyleType1 = null;
        listStyleType2 = null;
    }

    public LookBoardBeanInfo(int[] showTypes) {
        showList = new ArrayList<>();
        listStyleType0 = new ArrayList<>();
        listStyleType1 = new ArrayList<>();
        listStyleType2 = new ArrayList<>();
        this.showTyps = showTypes;
    }

    private boolean isShowType(int type){
        for (int i = 0; i < showTyps.length; i++) {
            if (type == showTyps[i]){
                return true;
            }
        }
        return false;
    }

    public List<MainLookBoardBean> initShowList(){
        for (int i = 0; i < titles.length ; i++) {
            if (isShowType(i)){
                MainLookBoardBean bean = new MainLookBoardBean(i);
                showList.add(bean);
            }
        }
        return showList;
    }
    public List<MainLookBoardBean> initListStyleType0(){
        for (int i = 0; i < STYlE_TYPE_1_INDEX; i++) {
            if (!isShowType(i)){
                MainLookBoardBean bean = new MainLookBoardBean(i);
                listStyleType0.add(bean);
            }
        }
        return listStyleType0;
    }
    public List<MainLookBoardBean> initListStyleType1(){
            if (!isShowType(STYlE_TYPE_1_INDEX)){
                MainLookBoardBean bean = new MainLookBoardBean(STYlE_TYPE_1_INDEX);
                listStyleType1.add(bean);
            }
        return listStyleType1;
    }
    public List<MainLookBoardBean> initListStyleType2(){
        for (int i = STYlE_TYPE_2_INDEX; i < titles.length; i++) {
            if (!isShowType(i)){
                MainLookBoardBean bean = new MainLookBoardBean(i);
                listStyleType2.add(bean);
            }
        }
        return listStyleType2;
    }


    public static int getStyleType(int type){
        if (type < STYlE_TYPE_1_INDEX){
            return 0;
        }else if(type == STYlE_TYPE_1_INDEX ){
            return 1;
        }else {
            return 2;
        }

    }

    public static String getContent(int type){
        switch (type){
            case 0:
                return "还没有记过账";
            case 1://本周 获得时间
                return DateUtils.getMondayOFWeek0() +"-"+DateUtils.getCurrentWeekday0();
            case 2://本月 获取本月的启示 和终止日期；
                return DateUtils.getFirstDayOfMonth0()+"-" +DateUtils.getDefaultDay0();
            case 3://本年
                return "01月01日-12月31日";
            case 4://理财钱包
                return "";
            case 5://日常收支
            case 6://应收应付
            case 7://投资收支
                return "本月";
            case 8://全部时间
                return "全部时间";
        }
        return "";
    }

    public static int getIcon(int type){
        int icon = 0;
        switch (type){
            case 0:
                icon = R.drawable.icon_trans_item_day;
                break;
            case 1:
                icon = R.drawable.icon_trans_item_week;
                break;
            case 2:
                icon = R.drawable.icon_trans_item_month;
                break;
            case 3:
                icon = R.drawable.icon_trans_item_month;
                break;
            case 4:
                icon = R.drawable.bottom_board_wallet_icon;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                icon = R.drawable.icon_template_item;
                break;
        }
        return icon;
    }


}
