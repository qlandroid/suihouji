package com.example.suishouji.activity.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.suishouji.R;
import com.example.suishouji.adapter.FaceBoardIconAdapter;
import com.example.suishouji.base.BaseActivity;
import com.example.suishouji.bean.AddAccountBookBean;
import com.example.suishouji.utils.IconUtils;
import com.example.suishouji.view.MenuButton;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FaceBoardActivity extends BaseActivity implements FaceBoardIconAdapter.OnItemClickListener, View.OnClickListener {
    public final static String INTENT_KEY_BEAN = "bean";
    private final static int ICON_HEIGHT = 100;
    private final static float ICON_SCALE = 1.5f;
    public final static int REQUEST_BOARD_1 = 0;
    public final static int REQUEST_BOARD_2 = 1;
    public final static int REQUEST_BOARD_3 = 2;
    private static final String TAG = "FaceBoardActivity";
    public final static String INTENT_KEY_BOARD_TYPE = "boardType";
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
    @Bind(R.id.board_0)
    TextView board0;
    @Bind(R.id.board_number_0)
    TextView boardNumber0;
    @Bind(R.id.board_1)
    TextView board1;
    @Bind(R.id.board_number_1)
    TextView boardNumber1;
    @Bind(R.id.income_layout)
    LinearLayout incomeLayout;
    @Bind(R.id.board_2)
    TextView board2;
    @Bind(R.id.board_number_2)
    TextView boardNumber2;
    @Bind(R.id.view_budget)
    View viewBudget;
    @Bind(R.id.text_3)
    TextView text3;
    @Bind(R.id.viewShow)
    View viewShow;
    @Bind(R.id.face_board_1)
    FrameLayout faceBoard1;
    @Bind(R.id.face_board_2)
    FrameLayout faceBoard2;
    @Bind(R.id.face_board_3)
    FrameLayout faceBoard3;
    @Bind(R.id.icon_list)
    RecyclerView iconList;
    @Bind(R.id.face_board_scroll)
    ScrollView faceBoardScroll;
    @Bind(R.id.face_board_content_0)
    TextView faceBoardContent0;
    @Bind(R.id.face_board_content_1)
    TextView faceBoardContent1;
    @Bind(R.id.face_board_content_2)
    TextView faceBoardContent2;

    private int UIWidth;
    private int UIHeight;
    private final static int SPAND_COUNT = 4;
    private FaceBoardIconAdapter mIconListAdapter;
    private AddAccountBookBean mBean;
    private IconUtils mIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_board);
        ButterKnife.bind(this);
        showTop();
        mBean = (AddAccountBookBean) getIntent().getSerializableExtra(INTENT_KEY_BEAN);
        //获得底部所需要的图片；
        mIcons = new IconUtils();
        //获得屏幕的宽高；
        initUISize();

        initIconList();

        topShow(mBean);
        addListener();
    }

    private void setText(AddAccountBookBean bean) {
        String boardText0 = bean.getFaceBoard_0();
        String boardText1 = bean.getFaceBoard_1();
        String boardText2 = bean.getFaceBoard_2();
        board0.setText(getTopText(boardText0));
        faceBoardContent0.setText(boardText0);

        board1.setText(getTopText(boardText1));
        faceBoardContent1.setText(boardText1);

        board2.setText(getTopText(boardText2));
        faceBoardContent2.setText(boardText2);
    }

    @NonNull
    private String getTopText(String boardText0) {
        return boardText0+":";
    }


    private void addListener() {
        toBack.setOnClickListener(this);
        actionBarContentLayout.setOnClickListener(this);
        faceBoard1.setOnClickListener(this);
        faceBoard2.setOnClickListener(this);
        faceBoard3.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        iconList.setAdapter(null);
        mIconListAdapter.setOnItemListener(null);
        toBack.setOnClickListener(null);
        actionBarContentLayout.setOnClickListener(null);
        faceBoard1.setOnClickListener(null);
        faceBoard2.setOnClickListener(null);
        faceBoard3.setOnClickListener(null);
        super.onDestroy();
    }

    private void topShow(AddAccountBookBean bean) {
        setText(bean);
        int position = bean.getIconType();
        setTopShowIcon(mIcons, position);
        int textColor = bean.getTextColor();
        setTopIconTextColor(textColor);
    }

    private void setTopIconTextColor(int topTextColor) {
        board0.setTextColor(topTextColor);
        board1.setTextColor(topTextColor);
        board2.setTextColor(topTextColor);
        boardNumber0.setTextColor(topTextColor);
        boardNumber1.setTextColor(topTextColor);
        boardNumber2.setTextColor(topTextColor);
    }


    /**
     * 设置当前top上面显示哪张图片
     */
    private void setTopShowIcon(IconUtils icons, int position) {
        int showIcon = icons.getPostion(position);
        int iconWidth = (int) (ICON_HEIGHT * ICON_SCALE);
        Glide.with(this).load(showIcon).override(iconWidth, ICON_HEIGHT).into(icon);
    }

    /**
     * 控制列表显示头部；否则会直接显示recyclerView底部；
     */
    private void showTop() {
        viewShow.setFocusable(true);
        viewShow.setFocusableInTouchMode(true);
        viewShow.requestFocus();
    }

    /**
     * 初始化列表底部；
     */
    private void initIconList() {
        iconList.setLayoutManager(new GridLayoutManager(this, SPAND_COUNT));
        mIconListAdapter = new FaceBoardIconAdapter(this, getItemWidth(), mIcons);
        iconList.setAdapter(mIconListAdapter);
        mIconListAdapter.setOnItemListener(this);
        //设置当前显示选择那张图片
        mIconListAdapter.setSelectedPosition(mBean.getIconType());
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
        Intent intent = new Intent();
        intent.putExtra(AddAccountBookDetailActivity.INTENT_KEY_BEAN, mBean);
        setResult(RESULT_CODE_OK, intent);
        mIconListAdapter.finish();
        mIcons = null;
        mBean = null;
        super.finish();
        overridePendingTransition(R.anim.finish_in_right, R.anim.finish_out_left);
    }


    /**
     * item被点击，判断当前被选择的是否与之前被选择的值是否相等，如果相等就进行改变；
     *
     * @param v
     * @param position
     */
    @Override
    public void onItemClick(View v, int position) {
        int lastPosition = mIconListAdapter.getSelectedPosition();
        Log.i(TAG, "onItemClick: " + position + "    " + lastPosition);
        if (lastPosition == position) {
            return;
        } else {
            int textColor;
            int icon;
            int iconType;
            iconType = position;
            //设置position为当前选中项
            mIconListAdapter.setSelectedPosition(position);
            textColor = mIconListAdapter.getTextColor(position);
            icon = mIconListAdapter.getItemIcon(position);
            mBean.setIconType(iconType);
            mBean.setIcon(icon);
            mBean.setTextColor(textColor);
            topShow(mBean);
            mIconListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_back://左上角返回键
                toBack();
                break;
            case R.id.action_bar_content_layout://actionBar中间的按键
                showPopupWindow();

                break;
            case R.id.face_board_1:
                int boardType0 = mBean.getFaceBoardType0();
                startFaceBoardRecordActivity(REQUEST_BOARD_1, 0,boardType0);
                break;
            case R.id.face_board_2:
                int boardType1 = mBean.getFaceBoardType1();
                startFaceBoardRecordActivity(REQUEST_BOARD_2, 1,boardType1);
                break;
            case R.id.face_board_3:
                int boardType2 = mBean.getFaceBoardType2();
                startFaceBoardRecordActivity(REQUEST_BOARD_3, 2,boardType2);
                break;
        }

    }

    /**
     * 显示一个PopupWindow
     */
    private void showPopupWindow() {

    }

    private void startFaceBoardRecordActivity(int request, int i ,int boardType) {
        Intent intent = new Intent(this, FaceBoardRecordActivity.class);
        intent.putExtra(FaceBoardRecordActivity.INTENT_KEY_TITLE_TYPE, i);
        intent.putExtra(FaceBoardRecordActivity.INTENT_KEY_BOARD_TYPE,boardType);
        startActivityForResult(intent, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            int boardType = data.getIntExtra(INTENT_KEY_BOARD_TYPE, -1);
            Log.i(TAG, "onActivityResult: " + requestCode );
            if (boardType != -1) {
                switch (requestCode) {
                    case REQUEST_BOARD_1:
                        mBean.setFaceBoardType0(boardType);
                        break;
                    case REQUEST_BOARD_2:
                        mBean.setFaceBoardType1(boardType);
                        break;
                    case REQUEST_BOARD_3:
                        mBean.setFaceBoardType2(boardType);
                        break;
                }
                topShow(mBean);
            }
        }
    }


    private void toBack() {
        Intent data = new Intent();
        data.putExtra(AddAccountBookDetailActivity.INTENT_KEY_BEAN, mBean);
        setResult(RESULT_CODE_OK, data);
        finish();
    }
}
