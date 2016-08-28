package com.example.suishouji.utils;

import android.app.Activity;
import android.content.Intent;

import com.example.suishouji.AddAccountBookDetailActivity;
import com.example.suishouji.interfaces.maintop.StartAddResultAccountBookActivityInterface;

/**
 * Created by Administrator on 2016/8/28.
 */
public class StartAndResult implements StartAddResultAccountBookActivityInterface {
    private static final int REQUEST_CODE = 0 ;

    public void startAddAccountBookActivity(Activity activity,int iconType,String bookName){
        Intent intent = new Intent(activity,AddAccountBookDetailActivity.class);
        intent.putExtra(AddAccountBookDetailActivity.INTENT_KEY_BOOK_NAME,bookName);
        intent.putExtra(AddAccountBookDetailActivity.INTENT_KEY_TYPE,iconType);
        activity.startActivityForResult(intent,REQUEST_CODE);
    }
    public void resultAddAccountBookActivity(){

    }
}
