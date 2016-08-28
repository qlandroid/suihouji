package com.example.suishouji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.suishouji.adapter.AddAccountBookListAdapter;
import com.example.suishouji.base.BaseActivity;
import com.example.suishouji.bean.AddAccountBookBean;
import com.example.suishouji.interfaces.maintop.StartAddResultAccountBookActivityInterface;
import com.example.suishouji.utils.AddAccountBookData;
import com.example.suishouji.utils.RecyclerViewDivider;
import com.example.suishouji.utils.StartAndResult;
import com.example.suishouji.view.MenuButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
public class AddAccountBookActivity extends BaseActivity implements AddAccountBookListAdapter.OnItemClickListener,View.OnClickListener{

    private static final String ACTION_TITLE = "添加账本";

    public static final int RESULT_CODE_FAIL = -1;

    @Bind(R.id.add_account_book_list)
    RecyclerView addAccountBookList;
    @Bind(R.id.to_back)
    MenuButton toBack;
    private AddAccountBookListAdapter mAddAdapter;
    private StartAddResultAccountBookActivityInterface mStartAndResult;

    private List<AddAccountBookBean> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_book);
        ButterKnife.bind(this);
        toBack.setText(ACTION_TITLE);
        init();
        initRecyclerView();

    }

    private void init() {
        mStartAndResult = new StartAndResult();
    }

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
        mList = new AddAccountBookData().getdata();
    }

    @Override
    public void onItemClick(View view) {
        int position = (int) view.getTag();
        int iconType = mList.get(position).getIconType();
        String bookName = mList.get(position).getBookName();
        mStartAndResult.startAddAccountBookActivity(this,iconType,bookName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.to_back:
                finish();
                break;
        }
    }
}
