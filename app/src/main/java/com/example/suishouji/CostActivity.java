package com.example.suishouji;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.suishouji.base.BaseActivity;
import com.example.suishouji.view.CostListView;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CostActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2{


    @Bind(R.id.cost_top_surplus_amount_tv)
    TextView costTopSurplusAmountTv;
    @Bind(R.id.cost_top_income_amount_tv)
    TextView costTopIncomeAmountTv;
    @Bind(R.id.cost_top_expend_amount_tv)
    TextView costTopExpendAmountTv;
    @Bind(R.id.cost_bottom_list_view)
    CostListView costBottomListView;
    @Bind(R.id.pull_refresh_scrollview)
    PullToRefreshScrollView mPullRefreshScrollview;
    private String[] costGroupMonth={"01","02","03","04","05","06","07","08","09","10","11","12"};
    private String[] costGroupContent={
            "01.01-01.31","02.01-02.29","03.01-03.31","04.01-04.30"
            ,"05.01-05.31","06.01-06.30","07.06-07.31",
            "08.01-08.31","09.01-09.30","10.01-10.31","11.01-11.30","12.01-12.31"
    };
    private String costGroupContentRun ="02.01-02.28";
    private String costGroupContentNoRun ="02.01-02.29";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);
        ButterKnife.bind(this);
        mPullRefreshScrollview.mHeaderLayout.setBackgroundColor(Color.parseColor("#fbcd45"));
        mPullRefreshScrollview.mFooterLayout.setBackgroundColor(Color.CYAN);
        mPullRefreshScrollview.setOnRefreshListener(this);
        setHeaderContent(num);
    }

    private void setHeaderContent(int number) {
        ILoadingLayout headerContent = mPullRefreshScrollview.getLoadingLayoutProxy(true, false);

        headerContent.setPullLabel("这是2"+number);//加载是文字
        headerContent.setReleaseLabel("这是3"+number);//手指加载时文字
    }
    private void setFootContent(int number) {
        ILoadingLayout headerContent = mPullRefreshScrollview.getLoadingLayoutProxy(false, true);
        headerContent.setPullLabel("这是2"+number);//加载是文字
        headerContent.setReleaseLabel("这是3"+number);//手指加载时文字
    }

    private int num = 0;
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        num ++;
        setHeaderContent(num);
        setFootContent(num);
        refreshView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        num--;
        setHeaderContent(num);
        setFootContent(num);
        refreshView.onRefreshComplete();
    }
}
