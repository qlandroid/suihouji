package com.example.suishouji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.suishouji.R;
import com.example.suishouji.utils.IconUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class FaceBoardIconAdapter extends RecyclerView.Adapter<FaceBoardIconAdapter.IconViewHolder> implements View.OnClickListener{

    private Context mContext;
    private IconUtils icons = new IconUtils();
    private int itemWidth;
    private int itemHeight;
    private OnItemClickListener mListener;
    private List<IconViewHolder> mListHolder = new ArrayList<>();
    private int mSelectedPosition = 0;
    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }
    public void setOnItemListener(OnItemClickListener mListener){
        this.mListener = mListener;
    }

    public FaceBoardIconAdapter( Context mContext,int itemWidth) {
        this.itemWidth = itemWidth;
        this.mContext = mContext;
        this.itemHeight = (int) (itemWidth/1.5f);
    }

    @Override
    public FaceBoardIconAdapter.IconViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.face_board_icon_item, parent, false);
        IconViewHolder holder = new IconViewHolder(itemView);
        mListHolder.add(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(FaceBoardIconAdapter.IconViewHolder holder, int position) {

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
        Glide.with(mContext).load(icons.getPostion(position)).override(itemWidth,itemHeight).into(holder.iconSrc);
        if (mSelectedPosition == position){
            holder.iconSelected.setVisibility(View.VISIBLE);
        }else {
            holder.iconSelected.setVisibility(View.GONE);
        }
    }

    public void finish(){
        for (int i = 0; i < mListHolder.size(); i++) {
            mListHolder.get(i).itemView.setOnClickListener(null);
        }
        mListHolder.clear();
        mContext = null;
        icons = null;
    }

    @Override
    public int getItemCount() {
        return icons.getIconLength();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (mListener!=null){
            mListener.onItemClick(v,position);
        }
    }

    public void setSelected(int postion){
        this.mSelectedPosition = postion;
        notifyDataSetChanged();
    }

    public int getmSelectedPosition(){
        return mSelectedPosition;
    }

    public int getItemIcon(int position){
        return icons.getPostion(position);
    }


    class IconViewHolder extends RecyclerView.ViewHolder {
        ImageView iconSrc,iconSelected;
        View itemView ;
        public IconViewHolder(View itemView) {
            super(itemView);
            this.itemView =itemView;
            iconSelected = (ImageView) itemView.findViewById(R.id.face_board_icon_item_selected_image_view);
            iconSrc = (ImageView) itemView.findViewById(R.id.face_board_icon_item_src_image_view);
            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            layoutParams.height = itemHeight;
            layoutParams.width = itemWidth;
        }
    }
}
