package com.example.suishouji;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.suishouji.adapter.FaceBoardIconAdapter;
import com.example.suishouji.base.BaseActivity;
import com.example.suishouji.view.MenuButton;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FaceBoardActivity extends BaseActivity {

    @Bind(R.id.to_back)
    MenuButton toBack;
    @Bind(R.id.action_bar_content_layout)
    LinearLayout actionBarContentLayout;
    @Bind(R.id.icon)
    ImageView icon;
    @Bind(R.id.text_0)
    TextView text0;
    @Bind(R.id.text_1)
    TextView text1;
    @Bind(R.id.view_budget_0)
    View viewBudget0;
    @Bind(R.id.text_2)
    TextView text2;
    @Bind(R.id.view_expand)
    View viewExpand;
    @Bind(R.id.expand)
    TextView expand;
    @Bind(R.id.expand_number)
    TextView expandNumber;
    @Bind(R.id.income_number)
    TextView incomeNumber;
    @Bind(R.id.income)
    LinearLayout income;
    @Bind(R.id.budget)
    TextView budget;
    @Bind(R.id.budget_number)
    TextView budgetNumber;
    @Bind(R.id.view_budget)
    View viewBudget;
    @Bind(R.id.text_3)
    TextView text3;
    @Bind(R.id.face_board_content_1)
    TextView faceBoardContent1;
    @Bind(R.id.face_board_1)
    FrameLayout faceBoard1;
    @Bind(R.id.face_board_content_2)
    TextView faceBoardContent2;
    @Bind(R.id.face_board_2)
    FrameLayout faceBoard2;
    @Bind(R.id.face_board_content_3)
    TextView faceBoardContent3;
    @Bind(R.id.face_board_3)
    FrameLayout faceBoard3;
    @Bind(R.id.icon_list)
    RecyclerView iconList;
    @Bind(R.id.face_board_scroll)
    ScrollView faceBoardScroll;
    @Bind(R.id.viewShow)
    View viewShow;

    private int UIWidth;
    private int UIHeight;
    private final static int SPAND_COUNT = 4;
    private FaceBoardIconAdapter mIconListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_board);
        ButterKnife.bind(this);
        initUISize();
        initIconList();
        faceBoardScroll.scrollTo(0, 0);
        viewShow.setFocusable(true);
        viewShow.setFocusableInTouchMode(true);
        viewShow.requestFocus();
    }

    private void initIconList() {
        iconList.setLayoutManager(new GridLayoutManager(this, SPAND_COUNT));
        mIconListAdapter = new FaceBoardIconAdapter(this, getItemWidth());
        iconList.setAdapter(mIconListAdapter);
    }

    private void initUISize() {
        UIWidth = getResources().getDisplayMetrics().widthPixels;
        UIHeight = getResources().getDisplayMetrics().heightPixels;
    }

    private int getItemWidth() {
        return UIWidth / SPAND_COUNT;
    }

    @Override
    public void finish() {
        mIconListAdapter.finish();
        super.finish();
        overridePendingTransition(R.anim.finish_in_right, R.anim.finish_out_left);
    }
}
