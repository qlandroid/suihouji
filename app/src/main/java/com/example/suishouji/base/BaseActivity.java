package com.example.suishouji.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by Administrator on 2016/8/23.
 */
public class BaseActivity extends AppCompatActivity {
    public static final int RESULT_CODE_OK = 0x250;
    public static final int RESULT_CODE_FAIL = 0x251;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }
}
