package com.example.suishouji.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.suishouji.MainActivity;
import com.example.suishouji.R;
import com.example.suishouji.adapter.LookBoardAdapter;
import com.example.suishouji.base.BaseActivity;
import com.example.suishouji.bean.MainLookBoardBean;
import com.example.suishouji.interfaces.maintop.OnItemClickListener;
import com.example.suishouji.utils.LookBoardBeanInfo;
import com.example.suishouji.view.MenuButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChangeMainListActivity extends BaseActivity implements View.OnClickListener {
    public final static String INTENT_KEY_ARRAY = "array";
    public final static String INTENT_KEY_TYPE_0_CONTENT = "type0Content";
    public final static String INTENT_KEY_TYPE_0_ICON = "type0Icon";

    LookBoardBeanInfo info;
    @Bind(R.id.showList)
    RecyclerView showType;
    @Bind(R.id.styleType0)
    RecyclerView styleType0;
    @Bind(R.id.styleType1)
    RecyclerView styleType1;
    @Bind(R.id.styleType2)
    RecyclerView styleType2;
    @Bind(R.id.to_back)
    MenuButton toBack;
    @Bind(R.id.styleType0_layout)
    LinearLayout styleType0Layout;
    @Bind(R.id.styleType1_layout)
    LinearLayout styleType1Layout;
    @Bind(R.id.styleType2_layout)
    LinearLayout styleType2Layout;


    private List<MainLookBoardBean> listShowType;
    private List<MainLookBoardBean> listStyleType0;
    private List<MainLookBoardBean> listStyleType1;
    private List<MainLookBoardBean> listStyleType2;
    private LookBoardAdapter showTypeAdapter;
    private LookBoardAdapter type0Adapter;
    private LookBoardAdapter type1Adapter;
    private LookBoardAdapter type2Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_main_list);
        ButterKnife.bind(this);

        setActionTitle();

        Intent intent = getIntent();
        int icon = intent.getIntExtra(INTENT_KEY_TYPE_0_ICON, 0);
        String content = intent.getStringExtra(INTENT_KEY_TYPE_0_CONTENT);
        int[] showTypes = intent.getIntArrayExtra(INTENT_KEY_ARRAY);

        initList(showTypes);
        initShowType0(icon, content);

        init();

        addListener();
        setType0Layout();
        setType1Layout();
        setType2Layout();
    }

    private void setType0Layout(){
        if (listStyleType0.size() == 0){
            styleType0Layout.setVisibility(View.GONE);
        }else {
            styleType0Layout.setVisibility(View.VISIBLE);
        }
    }
    private void setType1Layout(){
        if (listStyleType1.size() == 0){
            styleType1Layout.setVisibility(View.GONE);
        }else {
            styleType1Layout.setVisibility(View.VISIBLE);
        }
    }
    private void setType2Layout(){
        if (listStyleType2.size() == 0){
            styleType2Layout.setVisibility(View.GONE);
        }else {
            styleType2Layout.setVisibility(View.VISIBLE);
        }
    }

    private void addListener() {
        toBack.setOnClickListener(this);

        showTypeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //如果这里被点击了那么
                MainLookBoardBean bean = listShowType.get(position);
                listShowType.remove(bean);
                showTypeAdapter.update(listShowType);
                otherTypeListAdd(bean);
            }
        });

        type0Adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                //点击则添加到show
                MainLookBoardBean bean = listStyleType0.get(position);

                listStyleType0.remove(bean);
                type0Adapter.update(listStyleType0);

                if (listStyleType0.size() == 0) {
                    styleType0Layout.setVisibility(View.GONE);
                }
                showTypeListAdd(bean);

            }
        });

        type1Adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //点击则添加到show

                MainLookBoardBean bean = listStyleType1.get(position);
                listStyleType1.remove(bean);
                type1Adapter.update(listStyleType1);
                if (listStyleType1.size() == 0) {
                    styleType1Layout.setVisibility(View.GONE);
                }
                showTypeListAdd(bean);

            }
        });

        type2Adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                //点击则添加到show
                MainLookBoardBean bean = listStyleType2.get(position);
                listStyleType2.remove(bean);
                type2Adapter.update(listStyleType2);
                if (listStyleType2.size() == 0) {
                    styleType2Layout.setVisibility(View.GONE);
                }
                showTypeListAdd(bean);
            }
        });
    }

    private void otherTypeListAdd(MainLookBoardBean bean) {

        switch (bean.getStyleType()) {
            case 0://流水
                listStyleType0.add(bean);
                styleType0Layout.setVisibility(View.VISIBLE);
                type0Adapter.update(listStyleType0);
                break;
            case 1://理财
                listStyleType1.add(bean);
                styleType1Layout.setVisibility(View.VISIBLE);
                type1Adapter.update(listStyleType1);
                break;
            case 2://超级流水
                listStyleType2.add(bean);
                styleType2Layout.setVisibility(View.VISIBLE);
                type2Adapter.update(listStyleType2);
                break;
        }
    }

    private void showTypeListAdd(MainLookBoardBean bean) {
        listShowType.add(bean);
        showTypeAdapter.update(listShowType);
    }

    private void initShowType0(int icon, String content) {
        MainLookBoardBean bean = listShowType.get(0);
        bean.setContent(content);
        bean.setIcon(icon);
    }

    private void setActionTitle() {
        String title = getString(R.string.change_board_title);
        toBack.setText(title);
    }

    private void initList(int[] showTypes) {
        info = new LookBoardBeanInfo(showTypes);
        listShowType = info.initShowList();
        listStyleType0 = info.initListStyleType0();
        listStyleType1 = info.initListStyleType1();
        listStyleType2 = info.initListStyleType2();
    }

    private void init() {
        showType.setLayoutManager(new LinearLayoutManager(this));
        styleType0.setLayoutManager(new LinearLayoutManager(this));
        styleType1.setLayoutManager(new LinearLayoutManager(this));
        styleType2.setLayoutManager(new LinearLayoutManager(this));
        showTypeAdapter = new LookBoardAdapter(this, listShowType, true);
        type0Adapter = new LookBoardAdapter(this, listStyleType0, false);
        type1Adapter = new LookBoardAdapter(this, listStyleType1, false);
        type2Adapter = new LookBoardAdapter(this, listStyleType2, false);

        showType.setAdapter(showTypeAdapter);
        styleType0.setAdapter(type0Adapter);
        styleType1.setAdapter(type1Adapter);
        styleType2.setAdapter(type2Adapter);
    }


    @Override
    protected void onDestroy() {
        info.destroy();
        info = null;
        type0Adapter.destroy();
        type1Adapter.destroy();
        type2Adapter.destroy();
        showTypeAdapter.destroy();
        type0Adapter = null;
        type2Adapter = null;
        type1Adapter = null;
        super.onDestroy();
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
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.INTENT_KEY_ARRAY,getTypes());
        setResult(RESULT_CODE_OK,intent);
        super.finish();
    }

    private int[] getTypes(){
        int[] types = new int[listShowType.size()];
        for (int i = 0; i < listShowType.size(); i++) {
            types[i] = listShowType.get(i).getType();
        }
        return types;
    }
}
