package com.example.suishouji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.suishouji.bean.CostBean;
import com.example.suishouji.bean.CostChildBean;
import com.example.suishouji.view.CostListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CostActivity extends AppCompatActivity {

    @Bind(R.id.cost_list)
    CostListView costList;
    List<CostBean> groupList;
    Map<Integer,List<CostChildBean>> childMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);
        ButterKnife.bind(this);
        initData();
        costList.setLayoutManager(new LinearLayoutManager(this));
        costList.run(groupList,childMap);
    }

    private void initData() {
        groupList = new ArrayList<>();
        childMap = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            CostBean bean = new CostBean(1500.00f,i+"","time");
            groupList.add(bean);
        }
    }
}
