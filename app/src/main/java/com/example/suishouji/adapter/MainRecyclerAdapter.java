package com.example.suishouji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.suishouji.R;
import com.example.suishouji.bean.MainBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.BottomViewHolder> implements View.OnClickListener{
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
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mList != null? mList.size():0;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        Toast.makeText(mContext,"text被点击"+position,Toast.LENGTH_SHORT).show();
    }

    class BottomViewHolder extends RecyclerView.ViewHolder{
        View itemView ;
        public BottomViewHolder(View itemView) {
            super(itemView);
            this.itemView =itemView;
        }
    }
}
