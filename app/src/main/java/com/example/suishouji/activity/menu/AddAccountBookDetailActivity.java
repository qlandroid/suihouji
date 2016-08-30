package com.example.suishouji.activity.menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suishouji.R;
import com.example.suishouji.MainActivity;
import com.example.suishouji.base.BaseActivity;
import com.example.suishouji.bean.AddAccountBookBean;
import com.example.suishouji.utils.IconUtils;
import com.example.suishouji.view.MenuButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAccountBookDetailActivity extends BaseActivity {
    public final static String INTENT_KEY_BEAN = "bean";
    private static final String ACTION_BAR_TITLE = "添加";

    private static final String TAG = "mtag";
    public final static int REQUEST_CODE_CHANGE_BOOK = 0x301;
    @Bind(R.id.to_back)
    MenuButton toBack;
    @Bind(R.id.action_bar_save_text_view)
    TextView actionBarSaveTextView;
    @Bind(R.id.book_icon)
    ImageView bookIcon;
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
    @Bind(R.id.add_account_book_edit_name_edit_text)
    EditText bookNameEditText;
    private AddAccountBookBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_book_detail);
        ButterKnife.bind(this);
        mBean = obtainBean();

        initView(mBean);
    }

    private void setThemeTextColor(int textColor) {
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

    private void initView(AddAccountBookBean bean) {
        initThemeIcon(bean);
        setThemeTextColor(bean.getTextColor());
        initActionBarTitle(bean.getBookName());
        bookNameEditText.setText(bean.getBookName());
    }

    private void initThemeIcon(AddAccountBookBean bean) {
        IconUtils icons = new IconUtils();
        int icon = icons.getIcon(bean.getIconType());
        bookIcon.setImageResource(icon);
    }

    private void initActionBarTitle(String bookName) {
        if (bookName != null) {
            toBack.setText(getTitle(bookName));
        }
    }

    private AddAccountBookBean obtainBean() {
        Intent obtainIntent = getIntent();
        return (AddAccountBookBean) obtainIntent.getSerializableExtra(INTENT_KEY_BEAN);

    }


    @OnClick({R.id.to_back, R.id.account_book_detail_theme, R.id.action_bar_save_text_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.to_back://点击左上角返回按钮
                finish();
                break;
            case R.id.account_book_detail_theme://点击图片栏
                startFaceBoardActivity();
                break;
            case R.id.action_bar_save_text_view://点击保存
                saveButton();
                break;
        }
    }

    /**
     * 点击保存按钮，传递数据；通知主页面更新，菜单栏增加item
     */
    private void saveButton() {
        String bookName = bookNameEditText.getText().toString().trim();
        if (bookName!=null && bookName.length() > 0){
            //保存成功
            mBean.setBookName(bookName);
            Intent data = new Intent();
            data.putExtra(MainActivity.INTENT_KEY_BOOK, mBean);
            resultCodeSuccess(data);
        }else {
            //未保存
            Toast.makeText(this,"请输入账本名称",Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 点击保存关闭页面
     *
     * @param data 用于存储bean对象；
     */
    private void resultCodeSuccess(Intent data) {
        setResult(RESULT_CODE_OK, data);
        finish();
    }


    private void startFaceBoardActivity() {

        Intent startThemeIntent = new Intent(this, FaceBoardActivity.class);
        startThemeIntent.putExtra(FaceBoardActivity.INTENT_KEY_BEAN, mBean);
        startActivityForResult(startThemeIntent,REQUEST_CODE_CHANGE_BOOK);
        overridePendingTransition(R.anim.in_left, R.anim.out_right);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHANGE_BOOK) {
            if (resultCode == RESULT_CODE_OK) {
                this.mBean = (AddAccountBookBean) data.getSerializableExtra(INTENT_KEY_BEAN);
                initView(mBean);
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.finish_in_right, R.anim.finish_out_left);
    }
}
