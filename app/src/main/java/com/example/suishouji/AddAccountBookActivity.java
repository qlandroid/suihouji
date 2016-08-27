package com.example.suishouji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suishouji.adapter.AddAccountBookListAdapter;
import com.example.suishouji.base.BaseActivity;
import com.example.suishouji.bean.AddAccountBookBean;
import com.example.suishouji.utils.AddAccountBookData;
import com.example.suishouji.utils.RecyclerViewDivider;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
public class AddAccountBookActivity extends BaseActivity implements AddAccountBookListAdapter.OnItemClickListener{
    private static final String FILE_NAME = "add_account_book.json";
    @Bind(R.id.add_account_book_list)
    RecyclerView addAccountBookList;
    @Bind(R.id.to_back)
    LinearLayout toBack;
    private AddAccountBookListAdapter mAddAdapter;

    private List<AddAccountBookBean.BeanBean> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_book);
        ButterKnife.bind(this);
        addAccountBookList.setLayoutManager(new LinearLayoutManager(this));
        addAccountBookList.addItemDecoration(new RecyclerViewDivider(this,LinearLayout.VERTICAL));

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
        startActivity(new Intent(this,AddAccountBookDetailActivity.class));
        Toast.makeText(AddAccountBookActivity.this, "被点击了"+position, Toast.LENGTH_SHORT).show();
    }
}
