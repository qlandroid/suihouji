package com.example.suishouji.activity.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.suishouji.R;
import com.example.suishouji.MainActivity;
import com.example.suishouji.adapter.AddAccountBookListAdapter;
import com.example.suishouji.base.BaseActivity;
import com.example.suishouji.bean.AddAccountBookBean;
import com.example.suishouji.utils.AddAccountBookData;
import com.example.suishouji.utils.RecyclerViewDivider;
import com.example.suishouji.view.MenuButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
public class AddAccountBookActivity extends BaseActivity implements AddAccountBookListAdapter.OnItemClickListener,View.OnClickListener{

    private static final String ACTION_TITLE = "添加账本";
    private static final int REQUEST_CODE_DETAIL = 0;

    @Bind(R.id.add_account_book_list)
    RecyclerView addAccountBookList;
    @Bind(R.id.to_back)
    MenuButton toBack;
    private AddAccountBookListAdapter mAddAdapter;

    private List<AddAccountBookBean> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_book);
        ButterKnife.bind(this);
        toBack.setText(ACTION_TITLE);
        toBack.setOnClickListener(this);
        initRecyclerView();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DETAIL){
            //从上个页面返回的值
            if (resultCode == RESULT_CODE_OK){
                //成功那么就需要关闭此页面
                setResult(MainActivity.REQUEST_CODE_CHANGE_THEME,data);
                this.finish();
            }
        }
    }

    /**
     * 初始化列表
     */
    private void initRecyclerView() {
        addAccountBookList.setLayoutManager(new LinearLayoutManager(this));
        addAccountBookList.addItemDecoration(new RecyclerViewDivider(this, LinearLayout.VERTICAL));
        mAddAdapter = new AddAccountBookListAdapter(this);
        addAccountBookList.setAdapter(mAddAdapter);
        initList();
        mAddAdapter.updata(mList);
        mAddAdapter.setListener(this);
    }

    private void initList() {
        mList = new AddAccountBookData().getData();
    }

    /**
     * item的点击事件
     * @param view
     */
    @Override
    public void onItemClick(View view) {
        int position = (int) view.getTag();
        AddAccountBookBean itemBean = mList.get(position);
        startDetailActivity(itemBean);
    }

    /**
     * 跳转页面
     * @param itemBean
     */
    private void startDetailActivity(AddAccountBookBean itemBean) {
        Intent startDetailIntent = new Intent(this,AddAccountBookDetailActivity.class);
        startDetailIntent.putExtra(AddAccountBookDetailActivity.INTENT_KEY_BEAN,itemBean);
        startActivityForResult(startDetailIntent,REQUEST_CODE_DETAIL);
        overridePendingTransition(R.anim.in_left,R.anim.out_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.to_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        toBack.setOnClickListener(null);
        mList.clear();
        mAddAdapter.setListener(null);
        mAddAdapter.destroy();
        addAccountBookList.setAdapter(null);
        super.onDestroy();
    }
}
