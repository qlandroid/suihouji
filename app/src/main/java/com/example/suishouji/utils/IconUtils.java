package com.example.suishouji.utils;

import com.example.suishouji.R;

/**
 * Created by Administrator on 2016/8/28.
 */
public class IconUtils {
    int[] icons = {R.drawable.head_report_travel_bg,
            R.drawable.head_report_baby_bg,
            R.drawable.head_report_merried_bg,
            R.drawable.head_report_car_bg,
            R.drawable.head_report_redecoration_bg,
            R.drawable.head_report_business_trip_bg,
            R.drawable.head_report_humanity_bg,
            R.drawable.head_report_dining_bg,
            R.drawable.head_report_business_bg,
            R.drawable.book_template_bg_1,
            R.drawable.book_template_bg_2,
            R.drawable.book_template_bg_3,
            R.drawable.book_template_bg_4,
            R.drawable.book_template_bg_5,
            R.drawable.book_template_bg_6,
            R.drawable.book_template_bg_7,
            R.drawable.book_template_bg_8,
            R.drawable.book_template_bg_9,
            R.drawable.book_template_bg_10,
            R.drawable.book_template_bg_11,
            R.drawable.book_template_bg_12,
            R.drawable.book_template_bg_13,
            R.drawable.book_template_bg_14,
            R.drawable.book_template_bg_15,
            R.drawable.book_template_bg_16,
            R.drawable.book_template_bg_17,
            R.drawable.book_template_bg_18,
            R.drawable.book_template_bg_19,
            R.drawable.book_template_bg_20,
            R.drawable.book_template_bg_21,
            R.drawable.book_template_bg_22,
            R.drawable.book_template_bg_23,
            R.drawable.book_template_bg_24,
            R.drawable.book_template_bg_25};

    public int getIcon(int type){
        if (type<0){
            type = 0;
        }else if (type>=icons.length){
            type = icons.length -1;
        }
        return icons[type];
    }
    public int getIconLength(){
        return icons.length;
    }
    public int getPostion(int position){
        return icons[position];
    }

}
