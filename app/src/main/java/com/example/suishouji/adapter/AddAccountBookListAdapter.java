package com.example.suishouji.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.suishouji.R;
import com.example.suishouji.bean.AddAccountBookBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class AddAccountBookListAdapter extends RecyclerView.Adapter<AddAccountBookListAdapter.AddAccountViewHolder>implements View.OnClickListener {
    private List<AddAccountBookBean.BeanBean> mList;
    private Context mContext;
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view);
    }

    public AddAccountBookListAdapter( Context mContext) {

        this.mContext = mContext;
    }
    public void updata(List<AddAccountBookBean.BeanBean> mList){
        this.mList = mList;
        this.notifyDataSetChanged();
    }

    @Override
    public AddAccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =LayoutInflater.from(mContext).inflate(R.layout.add_acount_book_item,parent,false);
        return new AddAccountViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AddAccountViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
        AddAccountBookBean.BeanBean bean = mList.get(position);
        holder.contentTv.setText(bean.getContent());
        holder.nameTv.setText(bean.getName());
        holder.iv.setImageResource(bean.getIcon());
    }

    @Override
    public int getItemCount() {
        return mList == null? 0 : mList.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onItemClick(v);
        }
    }

    static class AddAccountViewHolder extends RecyclerView.ViewHolder{
        View itemView ;
        ImageView iv;
        TextView nameTv,contentTv;
        public AddAccountViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv = (ImageView) itemView.findViewById(R.id.add_account_book_icon_imageView);
            nameTv = (TextView) itemView.findViewById(R.id.add_account_book_name_textView);
            contentTv = (TextView) itemView.findViewById(R.id.add_account_book_content_textView);
        }
    }
}
