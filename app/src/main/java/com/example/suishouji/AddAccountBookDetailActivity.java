package com.example.suishouji;

import android.content.Intent;
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
    public static final String INTENT_KEY_TYPE = "icon";
    public static final String INTENT_KEY_BOOK_NAME = "bookName";

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
    private int mObtainIconType = 0;
    private String mObtainBookName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_book_detail);
        ButterKnife.bind(this);
        obtainIconType();

        initBookIcon();

        if (mObtainBookName != null){
            toBack.setText(getTitle(mObtainBookName));
        }
    }

    private String getTitle(String bookName){
        return ACTION_BAR_TITLE+bookName;
    }

    private void initBookIcon() {
        IconUtils  icons = new IconUtils();
        int icon = icons.getIcon(mObtainIconType);
        bookIcon.setImageResource(icon);
    }

    private void obtainIconType() {
        Intent obtainIntent = getIntent();
        if (obtainIntent !=null){
            mObtainIconType = obtainIntent.getIntExtra(INTENT_KEY_TYPE,0);
            mObtainBookName = obtainIntent.getStringExtra(INTENT_KEY_BOOK_NAME);
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
                Toast.makeText(AddAccountBookDetailActivity.this, "被点击了", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
