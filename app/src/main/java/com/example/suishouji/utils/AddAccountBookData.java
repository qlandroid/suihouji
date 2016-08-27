package com.example.suishouji.utils;

import com.example.suishouji.R;
import com.example.suishouji.bean.AddAccountBookBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class AddAccountBookData {
    int[] icons = {R.drawable.suite_bg_for_standard_0,
            R.drawable.suite_bg_for_business,
            R.drawable.suite_bg_for_catering,
            R.drawable.suite_bg_for_travel,
            R.drawable.suite_bg_for_fitment,
            R.drawable.suite_bg_for_marry,
            R.drawable.suite_bg_for_car,
            R.drawable.suite_bg_for_baby,
            R.drawable.suite_bg_for_business_trip,
            R.drawable.suite_bg_for_humanity,};
    String[] names = {"标准理财", "生意账本", "餐饮生意", "旅游账本", "装修账本", "结婚账本",
            "汽车账本", "宝宝账本", "差旅账本", "人情记账"};
    String[] contents = {"标准账本，分类较全",
            "帮你轻松大力生意流水账",
            "轻松打理餐饮生意账，账目清晰赚钱快",
            "适合出游，精心定制旅游分类",
            "装修必备，贴心为装修场景打造",
            "进入神圣殿堂前，记一记更幸福",
            "轻松记录爱车消费", "亲爱的宝宝", "出差报销更省心，差旅认识的记账首选",
            "钻石恒久远，人情永流传"};

    public List<AddAccountBookBean.BeanBean> getdata(){
        List<AddAccountBookBean.BeanBean> list = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            AddAccountBookBean.BeanBean bean = new AddAccountBookBean.BeanBean();
            bean.setContent(contents[i]);
            bean.setIcon(icons[i]);
            bean.setName(names[i]);
            if (i == 0){
                bean.setType(0);
            }
            bean.setType(1);
            list.add(bean);

        }
        return list;
    }
}
