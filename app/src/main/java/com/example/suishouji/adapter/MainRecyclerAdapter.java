package com.example.suishouji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suishouji.R;
import com.example.suishouji.bean.MainBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.BottomViewHolder> {
    private List<MainBean> mList;
    private Context mContext;

    public MainRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void updata( List<MainBean> mList){
        this.mList = mList;
    }
    @Override
    public BottomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_recycler_item,parent,false);

        return new BottomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BottomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList != null? mList.size():0;
    }

    class BottomViewHolder extends RecyclerView.ViewHolder{
        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }
}
