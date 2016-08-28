package com.example.suishouji.utils;

import android.app.Activity;
import android.content.Intent;

import com.example.suishouji.AddAccountBookDetailActivity;
import com.example.suishouji.bean.AddAccountBookBean;
import com.example.suishouji.interfaces.maintop.StartAddResultAccountBookActivityInterface;

import java.util.List;

/**
 * Created by Administrator on 2016/8/28.
 */
public class StartAndResult implements StartAddResultAccountBookActivityInterface {
    private static final int REQUEST_CODE = 0 ;

    public void startAddAccountBookActivity(Activity activity, AddAccountBookBean itemBean){
        Intent intent = new Intent(activity,AddAccountBookDetailActivity.class);
        int iconType = itemBean.getIconType();
        String bookName = itemBean.getBookName();
        int textColor = itemBean.getTextColor();
        intent.putExtra(AddAccountBookDetailActivity.INTENT_KEY_BOOK_NAME,bookName);
        intent.putExtra(AddAccountBookDetailActivity.INTENT_KEY_ICON_TYPE,iconType);
        intent.putExtra(AddAccountBookDetailActivity.INTENT_KEY_THEME_TEXT_COLOR,textColor);
        activity.startActivityForResult(intent,REQUEST_CODE);
    }
    public void resultAddAccountBookActivity(){

    }
}
