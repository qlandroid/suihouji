package com.example.suishouji.adapter;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.suishouji.MainActivity;
import com.example.suishouji.R;
import com.example.suishouji.bean.MainMenuItemBean;
import com.example.suishouji.utils.CommonAdapter;
import com.example.suishouji.view.ItemMoveListView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/24.
 */
public class MainMenuListAdapter extends CommonAdapter<String> implements ItemMoveListView.OnMoveListener{

    public MainMenuListAdapter(Context context, List<String> data, int layoutId) {
        super(context, data, layoutId);
    }

    private static final String TAG = "MainMenuListAdapter";
    @Override
    public void setItemContent(ViewHolder holder, String s, int position) {
        holder.setText(R.id.main_menu_list_item_book_name_tv,s);
        ImageView iv = holder.getViewById(R.id.main_menu_list_item_book_iv);
        holder.getViewById(R.id.main_menu_list_item_book_name_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:item 被点击 ");
            }
        });


    }

    @Override
    public void remove(String postion) {
        data.remove(postion);
        this.notifyDataSetChanged();
    }

    @Override
    public void insertItem(int position, String itemData) {
        data.add(position,itemData);
        this.notifyDataSetChanged();
    }
}
