package com.example.suishouji;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suishouji.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAccountBookDetailActivity extends BaseActivity {

    @Bind(R.id.action_bar_title_tv)
    TextView actionBarTitleTv;
    @Bind(R.id.to_back)
    LinearLayout toBack;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_book_detail);
        ButterKnife.bind(this);
    }

    private static final String TAG = "AddAccountBookDetailActivity";
    @OnClick({R.id.to_back, R.id.account_book_detail_theme})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.to_back:
                finish();
                break;
            case R.id.account_book_detail_theme:
                Toast.makeText(AddAccountBookDetailActivity.this, "被点击了", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
