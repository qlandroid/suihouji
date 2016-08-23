package com.example.suishouji.interfaces.maintop;


import android.support.v4.app.FragmentManager;

/**
 * Created by Administrator on 2016/8/23.
 */
public interface ShowMainTopContentInterface {
    void showTopContentFragment(FragmentManager manager,int frameLayoutId);
    void changeTopContentFragment(FragmentManager manager ,int frameLayoutId);
}
