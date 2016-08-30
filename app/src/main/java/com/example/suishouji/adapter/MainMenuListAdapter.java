package com.example.suishouji.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.suishouji.R;
import com.example.suishouji.view.ItemMoveListView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/24.
 */
public class MainMenuListAdapter extends BaseAdapter{
    private List<String> mData;
    private Context mContext;
    private boolean openSetting = false;
    public int clickPostion = 0;

    private  String selectBackgroundColor ="#fff8d7";
    private String normalBackgroundColor = "#ffffff";
    public MainMenuListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void update(List<String> mData){
        this.mData =mData;
    }


    @Override
    public int getCount() {
        return mData == null ? 0 :mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ItemViewHolder holder ;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.main_menu_list_item,parent,false);
            holder = new ItemViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ItemViewHolder) convertView.getTag();
        }
        if (!openSetting)
            holder.colorView.setVisibility((clickPostion == position)?View.VISIBLE:View.GONE);
            holder.normalLayout.setBackgroundColor((clickPostion == position)? Color.parseColor(selectBackgroundColor):Color.parseColor(normalBackgroundColor));
        showLayout(holder);

        return convertView;
    }

    private void showLayout(ItemViewHolder holder) {
        holder.openLayout.setVisibility(openSetting?View.VISIBLE:View.GONE);
        holder.normalLayout.setVisibility(openSetting?View.GONE: View.VISIBLE);
    }

    public void openSetting(){
        openSetting = true;
        this.notifyDataSetChanged();
    }
    public void colseSetting(){
        openSetting = false;
        this.notifyDataSetChanged();
    }
    static class ItemViewHolder {
        LinearLayout openLayout;
        TextView bookNameTxteView;
        RelativeLayout normalLayout;
        View colorView;
        public ItemViewHolder(View view) {
            this.colorView = view.findViewById(R.id.main_menu_list_item_view);
            view.findViewById(R.id.main_menu_list_item_normal_layout);
            normalLayout = (RelativeLayout) view.findViewById(R.id.main_menu_list_item_normal_layout);
            openLayout = (LinearLayout)view.findViewById(R.id.main_menu_list_item_open_layout);
            bookNameTxteView = (TextView) view.findViewById(R.id.main_menu_list_item_book_name_normal_tv);
        }
    }
}
