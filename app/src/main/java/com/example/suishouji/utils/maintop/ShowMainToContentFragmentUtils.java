package com.example.suishouji.utils.maintop;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;

import com.example.suishouji.fragment.MainTopFragment;
import com.example.suishouji.interfaces.maintop.ShowMainTopContentInterface;

/**
 * Created by Administrator on 2016/8/23.
 */
public class ShowMainToContentFragmentUtils implements ShowMainTopContentInterface{
    private Context mContext;

    public ShowMainToContentFragmentUtils(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void showTopContentFragment(FragmentManager manager,int layoutId) {
        SharedPreferences shared = mContext.getSharedPreferences(MainTopContentThemeInfo.SHARED_THEME_NAME
                , mContext.MODE_PRIVATE);
        int topContentThemeKey = shared.getInt(MainTopContentThemeInfo.SHARED_THEME_KEY,0);
        switch (topContentThemeKey){
            case MainTopContentThemeInfo.THEME_DEFAULT://默认
                manager.beginTransaction().add(layoutId,new MainTopFragment());
                break;
            case MainTopContentThemeInfo.THEME_OTHER://其他
                break;
        }
    }

}
