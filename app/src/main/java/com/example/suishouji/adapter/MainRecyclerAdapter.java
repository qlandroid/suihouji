package com.example.suishouji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.suishouji.R;
import com.example.suishouji.bean.MainLookBoardBean;
import com.example.suishouji.utils.LookBoardBeanInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.BottomViewHolder> implements View.OnClickListener{
    private List<MainLookBoardBean> mList;
    private Context mContext;

    private boolean isShow = true;

    public MainRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void update(List<MainLookBoardBean> mList){
        this.mList = mList;
        notifyDataSetChanged();
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
        holder.numberLayout.setVisibility(isShow?View.VISIBLE:View.GONE);

        MainLookBoardBean bean = mList.get(position);
        int icon = bean.getIcon();
        String expend = bean.getExpendNumber();
        String income = bean.getIncomeNumber();
        String title = bean.getTitle();
        String content = bean.getContent();
        holder.titleTextView.setText(title);
        if (bean.getType() == LookBoardBeanInfo.STYlE_TYPE_1_INDEX){
            holder.numberLayout.setVisibility(View.GONE);
            holder.financingTextView.setVisibility(View.VISIBLE);
            holder.contentTextView.setVisibility(View.GONE);
        }else {
            holder.contentTextView.setVisibility(View.VISIBLE);
            holder.numberLayout.setVisibility(View.VISIBLE);
            holder.financingTextView.setVisibility(View.GONE);
            holder.expendTextView.setText(expend);
            holder.incomeTextView.setText(income);
        }
        holder.contentTextView.setText(content);
        Glide.with(mContext).load(icon).into(holder.icon);
    }

    public void numberShowHide(boolean isShow){
        this.isShow = isShow;
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
        View numberLayout;
        TextView expendTextView,incomeTextView,titleTextView,contentTextView,financingTextView;
        ImageView icon;
        public BottomViewHolder(View itemView) {
            super(itemView);
            this.itemView =itemView;
            financingTextView = (TextView) itemView.findViewById(R.id.main_recycler_item_financing_tv);
            numberLayout = itemView.findViewById(R.id.main_item_outlay_layout);
            titleTextView = (TextView) itemView.findViewById(R.id.main_recycler_item_title_tv);
            contentTextView = (TextView) itemView.findViewById(R.id.main_recycler_item_content_tv);
            expendTextView = (TextView) itemView.findViewById(R.id.main_recycler_item_expend_tv);
            incomeTextView = (TextView) itemView.findViewById(R.id.main_recycler_item_income_tv);
            icon = (ImageView) itemView.findViewById(R.id.main_recycler_item_iv);
        }
    }
}
