package com.example.suishouji;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suishouji.base.BaseActivity;
import com.example.suishouji.utils.IconUtils;
import com.example.suishouji.view.MenuButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAccountBookDetailActivity extends BaseActivity {
    public static final String INTENT_KEY_ICON_TYPE = "icon";
    public static final String INTENT_KEY_BOOK_NAME = "bookName";
    public static final String INTENT_KEY_THEME_TEXT_COLOR = "themeTextColor";
    private static final String ACTION_BAR_TITLE = "添加";
    @Bind(R.id.to_back)
    MenuButton toBack;
    @Bind(R.id.action_bar_save_text_view)
    TextView actionBarSaveTextView;
    @Bind(R.id.action_bar)
    FrameLayout actionBar;
    @Bind(R.id.account_book_name_layout)
    FrameLayout accountBookNameLayout;
    @Bind(R.id.book_icon)
    ImageView bookIcon;
    @Bind(R.id.account_book_detail_theme)
    FrameLayout accountBookDetailTheme;
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tv3)
    TextView tv3;
    @Bind(R.id.tv4)
    TextView tv4;
    @Bind(R.id.tv5)
    TextView tv5;
    @Bind(R.id.tv6)
    TextView tv6;
    private int mObtainIconType = 0;
    private String mObtainBookName;
    private int mObtainThemeTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_book_detail);
        ButterKnife.bind(this);
        obtainIconType();

        init();


    }
    private void setThemeTextColor(int textColor){
        tv1.setTextColor(textColor);
        tv2.setTextColor(textColor);
        tv3.setTextColor(textColor);
        tv4.setTextColor(textColor);
        tv5.setTextColor(textColor);
        tv6.setTextColor(textColor);
    }

    private String getTitle(String bookName) {
        return ACTION_BAR_TITLE + bookName;
    }

    private void init() {
        initThemeIcon();
        setThemeTextColor(mObtainThemeTextColor);
        initActionBarTitle();
    }

    private void initThemeIcon() {
        IconUtils icons = new IconUtils();
        int icon = icons.getIcon(mObtainIconType);
        bookIcon.setImageResource(icon);
    }

    private void initActionBarTitle() {
        if (mObtainBookName != null) {
            toBack.setText(getTitle(mObtainBookName));
        }
    }

    private void obtainIconType() {
        Intent obtainIntent = getIntent();
        if (obtainIntent != null) {
            mObtainIconType = obtainIntent.getIntExtra(INTENT_KEY_ICON_TYPE, 0);
            mObtainBookName = obtainIntent.getStringExtra(INTENT_KEY_BOOK_NAME);
            mObtainThemeTextColor = obtainIntent.getIntExtra(INTENT_KEY_THEME_TEXT_COLOR, Color.WHITE);
        }
    }

    private static final String TAG = "AddAccountBookDetailActivity";

    @OnClick({R.id.to_back, R.id.account_book_detail_theme})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.to_back:
                setResult(AddAccountBookActivity.RESULT_CODE_FAIL);
                finish();
                break;
            case R.id.account_book_detail_theme:
                startActivity(new Intent(this,FaceBoardActivity.class));
                overridePendingTransition(R.anim.in_left,R.anim.out_right);
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.finish_in_right,R.anim.finish_out_left);
    }
}
