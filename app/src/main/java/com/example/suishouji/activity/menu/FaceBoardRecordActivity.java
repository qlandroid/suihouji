package com.example.suishouji.activity.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.suishouji.R;
import com.example.suishouji.adapter.FaceBoardTextAdapter;
import com.example.suishouji.base.BaseActivity;
import com.example.suishouji.utils.BoardInfo;
import com.example.suishouji.utils.IconUtils;
import com.example.suishouji.utils.RecyclerViewDivider;
import com.example.suishouji.view.MenuButton;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FaceBoardRecordActivity extends BaseActivity implements View.OnClickListener,FaceBoardTextAdapter.OnItemClickListener{
    public final static String INTENT_KEY_TITLE_TYPE = "title";
    private final static int TYPE_ADAPTER_MONTH = 0;
    private final static int TYPE_ADAPTER_YEAR = 1;

    public final static String INTENT_KEY_BOARD_TYPE ="boardType";
    @Bind(R.id.showTop)
    View showTop;
    @Bind(R.id.to_back)
    MenuButton toBack;
    @Bind(R.id.month_list)
    RecyclerView monthList;
    @Bind(R.id.year_list)
    RecyclerView yearList;

    private FaceBoardTextAdapter monthAdapter,yearAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_board_record);
        ButterKnife.bind(this);
        showTop();

        Intent intent = getIntent();
        int titleType = intent.getIntExtra(INTENT_KEY_TITLE_TYPE,1);
        int boardType = intent.getIntExtra(INTENT_KEY_BOARD_TYPE,0);
        initActionTitle(titleType);

        addListener();
        initRecyclerView();

        initItemShowIcon(boardType);

    }

    private void addListener() {
        toBack.setOnClickListener(this);

    }

    private static final String TAG = "FaceBoardRecordActivity";
    private void initItemShowIcon(int boardType){
        Log.i(TAG, "initItemShowIcon: " + boardType);
        if (boardType >= BoardInfo.boardMonth.length){
            //是year
            monthAdapter.setHideIcon();
            yearAdapter.setShowIconPosition(BoardInfo.getBoardYearTypeSub(boardType));
        }else {
            //是month
            yearAdapter.setHideIcon();
            monthAdapter.setShowIconPosition(boardType);
        }
    }

    private void initRecyclerView() {
        monthList.setLayoutManager(new LinearLayoutManager(this));
        yearList.setLayoutManager(new LinearLayoutManager(this));

        RecyclerViewDivider itemDivider = new RecyclerViewDivider(this, LinearLayout.VERTICAL);
        monthList.addItemDecoration(itemDivider);
        yearList.addItemDecoration(itemDivider);

        monthAdapter = new FaceBoardTextAdapter(this, BoardInfo.boardMonth,TYPE_ADAPTER_MONTH);
        yearAdapter = new FaceBoardTextAdapter(this,BoardInfo.boardYear,TYPE_ADAPTER_YEAR);

        monthList.setAdapter(monthAdapter);
        yearList.setAdapter(yearAdapter);

        monthAdapter.setOnItemClickListener(this);
        yearAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void onDestroy() {
        monthAdapter.destroy();
        yearAdapter.destroy();
        yearList.setAdapter(null);
        monthList.setAdapter(null);
        toBack.setOnClickListener(null);

        super.onDestroy();
    }

    private void initActionTitle(int titleType) {
        String[] titles = getResources().getStringArray(R.array.face_board_record_action_title);

        toBack.setText(titles[titleType]);
    }

    private void showTop() {
        showTop.setFocusable(true);
        showTop.setFocusableInTouchMode(true);
        showTop.requestFocus();
    }

    @Override
    public void onClick(View v) {
        finish();
    }


    @Override
    public void onItemClick(View view, int position,int type) {
        int boardType;
        switch (type){
            case TYPE_ADAPTER_MONTH:
                boardType = BoardInfo.getBoarMonthType(position);
                yearAdapter.setHideIcon();
                monthAdapter.setShowIconPosition(position);
                break;
            case TYPE_ADAPTER_YEAR:
            default:
                yearAdapter.setShowIconPosition(position);
                monthAdapter.setHideIcon();
                boardType = BoardInfo.getBoardYearTypeAdd(position);

        }
        yearAdapter.notifyDataSetChanged();
        monthAdapter.notifyDataSetChanged();

        Intent intent = new Intent();
        intent.putExtra(FaceBoardActivity.INTENT_KEY_BOARD_TYPE,boardType);
        setResult(RESULT_OK,intent);
        finish();
    }
}
