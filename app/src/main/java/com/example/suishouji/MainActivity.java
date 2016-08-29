package com.example.suishouji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.suishouji.adapter.MainMenuListAdapter;
import com.example.suishouji.adapter.MainRecyclerAdapter;
import com.example.suishouji.base.BaseActivity;
import com.example.suishouji.bean.MainBean;
import com.example.suishouji.fragment.MenuFragment;
import com.example.suishouji.interfaces.maintop.ChangeMainTopContentInterface;
import com.example.suishouji.interfaces.maintop.ShowMainTopContentInterface;
import com.example.suishouji.utils.RecyclerViewDivider;
import com.example.suishouji.utils.maintop.ShowMainToContentFragmentUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements ChangeMainTopContentInterface {


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
    @Bind(R.id.main_custom_made_btn)
    Button mainCustomMadeBtn;
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


    private int[] tabs = {R.id.main_bottom_show_menu_iv, R.id.main_bottom_tab_0,
            R.id.main_bottom_tab_1,
            R.id.main_bottom_tab_2,
            R.id.main_bottom_tab_3, R.id.main_bottom_tab_4};
    private int UIWidth, UIHeight;
    private MainRecyclerAdapter adapter;
    private List<MainBean> mList = new ArrayList<>();
    private FragmentManager mFragmentManager;
    private ShowMainTopContentInterface mShowMainTop;
    private View menuView;
    private List<String> mMenuData;
    private MainMenuListAdapter mMainMenuListAdapter;
    private ListView menuNotesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showTop();

        init();


        recyclerUpdata(mList);
        showTopContentFragment();

    }

    /**
     * 解决recyclerView刷新抢占焦点，不能显示顶部；
     */
    private void showTop() {
        viewShow.setFocusable(true);
        viewShow.setFocusableInTouchMode(true);
        viewShow.requestFocus();
    }


    private void showTopContentFragment() {
        mShowMainTop.showTopContentFragment(mFragmentManager, R.id.main_top_frame_layout);
    }

    private void init() {
        mFragmentManager = getSupportFragmentManager();
        UIWidth = getResources().getDisplayMetrics().widthPixels;
        UIHeight = getResources().getDisplayMetrics().heightPixels;
        mShowMainTop = new ShowMainToContentFragmentUtils(this);
        initContentTopSize(UIWidth, UIHeight);
        initRecyclerView();
        initSlidingMenu();
    }

    private void initSlidingMenu() {
        SlidingMenu menu = new SlidingMenu(this);

        //设置左菜单；
        menu.setMode(SlidingMenu.LEFT);
        menuView = LayoutInflater.from(this).inflate(R.layout.menu_layout, null, false);
        getSupportFragmentManager().beginTransaction().add(R.id.menu_frame_layout, new MenuFragment()).commit();
        menu.setMenu(menuView);
        //设置手势开启菜单和关闭
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setTouchModeBehind(SlidingMenu.TOUCHMODE_MARGIN);
        //设置菜单显示宽度；
        menu.setBehindWidth(getShowMenuWidth());
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
    }

    private void recyclerUpdata(List<MainBean> mList) {
        for (int i = 0; i < 5; i++) {
            MainBean bean = new MainBean();
            this.mList.add(bean);
        }
        adapter.updata(this.mList);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mainRvList.setLayoutManager(layoutManager);
        mainRvList.addItemDecoration(new RecyclerViewDivider(this, LinearLayout.VERTICAL));
        adapter = new MainRecyclerAdapter(this);
        mainRvList.setAdapter(adapter);

    }

    private void initContentTopSize(int UIWidth, int UIHeight) {
        ViewGroup.LayoutParams layoutParams = mainTopFrameLayout.getLayoutParams();
        layoutParams.width = UIWidth;
        layoutParams.height = getTopContentHeight(UIHeight);
    }


    @OnClick({R.id.main_bottom_show_menu_iv, R.id.main_bottom_tab_0, R.id.main_bottom_tab_1, R.id.main_bottom_tab_2, R.id.main_bottom_tab_3, R.id.main_bottom_tab_4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_bottom_show_menu_iv:
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

        }
    }

    @Override
    public void changeTopContentTheme() {
        //更改TOPContent类型；
        mShowMainTop.showTopContentFragment(mFragmentManager, R.id.main_top_frame_layout);
    }

    public int getShowMenuWidth() {
        return (int) (UIWidth * MENU_WIDTH);
    }

    public int getTopContentHeight(int UIHeight) {
        return (int) (UIHeight * CONTENT_HEIGHT);
    }


}
