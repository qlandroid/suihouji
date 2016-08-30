package com.example.suishouji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suishouji.R;
import com.example.suishouji.bean.MainLookBoardBean;
import com.example.suishouji.interfaces.maintop.OnItemClickListener;
import com.example.suishouji.utils.LookBoardBeanInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/30.
 */
public class LookBoardAdapter extends RecyclerView.Adapter<LookBoardAdapter.LookBoardViewHolder> implements View.OnClickListener{
    private Context mContext;
    private List<MainLookBoardBean> mList;
    private List<LookBoardViewHolder> listHolder;

    private int leftIcon;
    private OnItemClickListener listener;

    public void destroy(){
        for (int i = 0; i < listHolder.size(); i++) {
            listHolder.get(i).leftIcon.setOnClickListener(null);
        }
        listHolder.clear();
        listener = null;
        listHolder = null;
        mList.clear();
        mList = null;
        listener = null;
        mContext = null;
    }

    public void update(List<MainLookBoardBean> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener l){
        this.listener = l;
    }

    public LookBoardAdapter(Context mContext, List<MainLookBoardBean> mList , boolean isShow) {
        this.mContext = mContext;
        this.mList = mList;
        this.leftIcon = getLeftIcon(isShow);
        listHolder = new ArrayList<>();
    }

    @Override
    public LookBoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.look_board_change_list_item,parent,false);
        LookBoardViewHolder holder = new LookBoardViewHolder(itemView);
        listHolder.add(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(LookBoardViewHolder holder, int position) {
        MainLookBoardBean itemBean = mList.get(position);
        holder.leftIcon.setTag(position);
        holder.leftIcon.setOnClickListener(this);

        holder.leftIcon.setImageResource(leftIcon);
        holder.icon.setImageResource(itemBean.getIcon());
        holder.titleTextView.setText(itemBean.getTitle());


        if (itemBean.getType() == LookBoardBeanInfo.STYlE_TYPE_1_INDEX){
            holder.contentTextView.setVisibility(View.GONE);
        }else {
            holder.contentTextView.setVisibility(View.VISIBLE);
            holder.contentTextView.setText(itemBean.getContent());
        }

        if (position == mList.size()-1){
            holder.dividerView.setVisibility(View.GONE);
        }else {
            holder.dividerView.setVisibility(View.VISIBLE);
        }

        if (itemBean.getType() == 0){
            holder.maskingView.setVisibility(View.VISIBLE);
        }
    }


    private int getLeftIcon(boolean isShow){
        return !isShow?R.drawable.bottom_board_icon_add:R.drawable.bottom_board_icon_minus;
    }


    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public void onClick(View v) {
       int position = (int) v.getTag();
        if (listener != null){
            listener.onItemClick(v,position);
        }
    }

    class LookBoardViewHolder extends RecyclerView.ViewHolder{
        TextView contentTextView,titleTextView;
        ImageView leftIcon,icon;
        View maskingView,dividerView;
        public LookBoardViewHolder(View itemView) {
            super(itemView);
            dividerView = itemView.findViewById(R.id.view_divider);
            contentTextView = (TextView) itemView.findViewById(R.id.look_board_change_item_content);
            titleTextView = (TextView) itemView.findViewById(R.id.look_board_change_item_title);
            leftIcon = (ImageView) itemView.findViewById(R.id.look_board_change_item_left_icon);
            icon = (ImageView) itemView.findViewById(R.id.look_board_change_item_icon);
            maskingView = itemView.findViewById(R.id.look_board_change_item_masking_view);
        }
    }
}
