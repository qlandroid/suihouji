package com.example.suishouji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suishouji.activity.ChangeMainListActivity;
import com.example.suishouji.activity.CostActivity;
import com.example.suishouji.adapter.MainRecyclerAdapter;
import com.example.suishouji.base.BaseActivity;
import com.example.suishouji.bean.MainLookBoardBean;
import com.example.suishouji.fragment.MainTopFragment;
import com.example.suishouji.fragment.MenuFragment;
import com.example.suishouji.utils.RecyclerViewDivider;
import com.example.suishouji.utils.maintop.MainTopContentThemeInfo;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    public final static int REQUEST_CODE_CHANGE_BOARD = 1;
    public final static String INTENT_KEY_ARRAY ="array";

    public final static int REQUEST_CODE_CHANGE_THEME = 20;
    public final static String INTENT_KEY_BOOK = "bean";
    private final float CONTENT_HEIGHT = 0.4f;
    private final float MENU_WIDTH = 0.8f;
    @Bind(R.id.main_top_frame_layout)
    FrameLayout mainTopFrameLayout;
    @Bind(R.id.main_write_note)
    Button mainWriteNote;
    @Bind(R.id.main_write)
    ImageView mainWrite;
    @Bind(R.id.main_btn_layout)
    LinearLayout mainBtnLayout;
    @Bind(R.id.main_rv_list)
    RecyclerView mainRvList;
    @Bind(R.id.main_board_change)
    Button mainBoardChange;
    @Bind(R.id.main_bottom_show_menu_iv)
    ImageButton mainBottomShowMenuIv;
    @Bind(R.id.main_bottom_tab_iv_0)
    ImageView mainBottomTabIv0;
    @Bind(R.id.main_bottom_tab_tv_0)
    TextView mainBottomTabTv0;
    @Bind(R.id.main_bottom_tab_0)
    LinearLayout mainBottomTab0;
    @Bind(R.id.main_bottom_tab_iv_1)
    ImageView mainBottomTabIv1;
    @Bind(R.id.main_bottom_tab_tv_1)
    TextView mainBottomTabTv1;
    @Bind(R.id.main_bottom_tab_1)
    LinearLayout mainBottomTab1;
    @Bind(R.id.main_bottom_tab_iv_2)
    ImageView mainBottomTabIv2;
    @Bind(R.id.main_bottom_tab_tv_2)
    TextView mainBottomTabTv2;
    @Bind(R.id.main_bottom_tab_2)
    LinearLayout mainBottomTab2;
    @Bind(R.id.main_bottom_tab_iv_3)
    ImageView mainBottomTabIv3;
    @Bind(R.id.main_bottom_tab_tv_3)
    TextView mainBottomTabTv3;
    @Bind(R.id.main_bottom_tab_3)
    LinearLayout mainBottomTab3;
    @Bind(R.id.main_bottom_tab_iv_4)
    ImageView mainBottomTabIv4;
    @Bind(R.id.main_bottom_tab_tv_4)
    TextView mainBottomTabTv4;
    @Bind(R.id.main_bottom_tab_4)
    LinearLayout mainBottomTab4;
    @Bind(R.id.main_bottom_tab)
    LinearLayout mainBottomTab;
    @Bind(R.id.viewShow)
    View viewShow;

    private boolean isFirst = true;
    private int[] tabs = {R.id.main_bottom_show_menu_iv, R.id.main_bottom_tab_0,
            R.id.main_bottom_tab_1,
            R.id.main_bottom_tab_2,
            R.id.main_bottom_tab_3, R.id.main_bottom_tab_4};
    private int UIWidth, UIHeight;
    private MainRecyclerAdapter mListAdapter;
    private List<MainLookBoardBean> mList = new ArrayList<>();
    private FragmentManager mFragmentManager;
    private View menuView;
    private SlidingMenu mSlidingMenu;
    private MainTopFragment mTopFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showTop();
        init();
        showTopContentFragment();
        if (isFirst) {
            isFirst =false;
            //如果是第一次显示则创建type为0,1,2,4；为面板；
            for (int i = 0; i < 5; i++) {
                if (i != 3) {
                    MainLookBoardBean bean = new MainLookBoardBean(i);
                    mList.add(bean);
                }
            }
        }
        listShowUpdate(mList);
    }

    /**
     * 用于隐藏数字
     */
    private void hideNumber() {
        mListAdapter.numberShowHide(false);
        mTopFragment.setShowHideNumber(false);
    }

    /**
     * 用于显示数字；
     */
    private void showNumber() {
        mListAdapter.numberShowHide(true);
        mTopFragment.setShowHideNumber(true);
    }


    /**
     * 解决recyclerView刷新抢占焦点，不能显示顶部；
     */
    private void showTop() {
        viewShow.setFocusable(true);
        viewShow.setFocusableInTouchMode(true);
        viewShow.requestFocus();
    }


    @Override
    public void onBackPressed() {
        if (mSlidingMenu.isSecondaryMenuShowing()) {
            mSlidingMenu.showContent();//显示主页面；
        }else {
            super.onBackPressed();
        }

    }


    /**
     * 设置显示顶部图片
     */
    private void showTopContentFragment() {
        SharedPreferences shared = getSharedPreferences(MainTopContentThemeInfo.SHARED_THEME_NAME
                , MODE_PRIVATE);
        int topContentThemeKey = shared.getInt(MainTopContentThemeInfo.SHARED_THEME_KEY, 0);
        switch (topContentThemeKey) {
            case MainTopContentThemeInfo.THEME_DEFAULT://默认
                mFragmentManager.beginTransaction().add(R.id.main_top_frame_layout, mTopFragment).commit();
                break;
            case MainTopContentThemeInfo.THEME_OTHER://其他
                break;
        }
    }

    /**
     * 初始化控件
     */
    private void init() {
        mTopFragment = new MainTopFragment();
        mFragmentManager = getSupportFragmentManager();
        UIWidth = getResources().getDisplayMetrics().widthPixels;
        UIHeight = getResources().getDisplayMetrics().heightPixels;
        initContentTopSize(UIWidth, UIHeight);
        initRecyclerView();
        initSlidingMenu();
    }

    private void initSlidingMenu() {
        mSlidingMenu = new SlidingMenu(this);

        //设置左菜单；
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        menuView = LayoutInflater.from(this).inflate(R.layout.menu_layout, null, false);
        getSupportFragmentManager().beginTransaction().add(R.id.menu_frame_layout, new MenuFragment()).commit();
        mSlidingMenu.setMenu(menuView);
        //设置手势开启菜单和关闭
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_MARGIN);
        //设置菜单显示宽度；
        mSlidingMenu.setBehindWidth(getShowMenuWidth());
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);


    }

    /**
     * 更新页面显示数据；
     *
     * @param mList
     */
    private void listShowUpdate(List<MainLookBoardBean> mList) {
        mListAdapter.update(this.mList);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mainRvList.setLayoutManager(layoutManager);
        mainRvList.addItemDecoration(new RecyclerViewDivider(this, LinearLayout.VERTICAL));
        mListAdapter = new MainRecyclerAdapter(this);
        mainRvList.setAdapter(mListAdapter);
    }

    private void initContentTopSize(int UIWidth, int UIHeight) {
        ViewGroup.LayoutParams layoutParams = mainTopFrameLayout.getLayoutParams();
        layoutParams.width = UIWidth;
        layoutParams.height = getTopContentHeight(UIHeight);
    }


    @OnClick({R.id.main_bottom_show_menu_iv, R.id.main_bottom_tab_0, R.id.main_bottom_tab_1,
            R.id.main_bottom_tab_2, R.id.main_bottom_tab_3, R.id.main_bottom_tab_4,
            R.id.main_board_change})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_bottom_show_menu_iv:
                mSlidingMenu.showSecondaryMenu();//开启menu
                break;
            case R.id.main_bottom_tab_0:
                startActivity(new Intent(this, CostActivity.class));
                break;
            case R.id.main_bottom_tab_1:
                break;
            case R.id.main_bottom_tab_2:
                break;
            case R.id.main_bottom_tab_3:
                break;
            case R.id.main_bottom_tab_4:
                break;
            case R.id.main_board_change://跳转到ChangeBoardActivity;
                startChangeBoardActivity();
                Toast.makeText(MainActivity.this, "被点击了", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void startChangeBoardActivity() {
        Intent intent = new Intent(this, ChangeMainListActivity.class);
        MainLookBoardBean bean = mList.get(0);
        int[] types = getTypes();
        intent.putExtra(ChangeMainListActivity.INTENT_KEY_ARRAY, types);//传入当前的页面所显示面板的Type数组
        intent.putExtra(ChangeMainListActivity.INTENT_KEY_TYPE_0_ICON, bean.getIcon());//传入type为0的图片；
        intent.putExtra(ChangeMainListActivity.INTENT_KEY_TYPE_0_CONTENT, bean.getContent());//传入type为0的的显示内容；
        startActivityForResult(intent,REQUEST_CODE_CHANGE_BOARD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHANGE_BOARD){
            mList.clear();
            int[] types = data.getIntArrayExtra(INTENT_KEY_ARRAY);
            for (int i = 0; i < types.length; i++) {
                MainLookBoardBean bean = new MainLookBoardBean(types[i]);
                mList.add(bean);
            }
            listShowUpdate(mList);
        }
    }

    public int getShowMenuWidth() {
        return (int) (UIWidth * MENU_WIDTH);
    }

    public int getTopContentHeight(int UIHeight) {
        return (int) (UIHeight * CONTENT_HEIGHT);
    }


    private int[] getTypes() {
        int[] types = new int[mList.size()];
        for (int i = 0; i < mList.size(); i++) {
            types[i] = mList.get(i).getType();
        }
        return types;
    }

    public void setType0Icom(int icon) {
        mList.get(0).setIcon(icon);
    }

    public void setType0Content(String content) {
        mList.get(0).setContent(content);
    }


}
