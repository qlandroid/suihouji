package com.example.suishouji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.suishouji.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class FaceBoardTextAdapter extends RecyclerView.Adapter<FaceBoardTextAdapter.TextViewHolder> implements View.OnClickListener{
    private Context mContext;
    private String[] texts;

    private List<TextViewHolder> mListHolder;
    public int type ;
    private int showIconPosition ;
    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener l){
        this.listener = l;
    }

    public FaceBoardTextAdapter(Context mContext, String[] texts, int type) {
        mListHolder = new ArrayList<>();
        this.mContext = mContext;
        this.texts = texts;
        this.type = type;
    }

    public void setHideIcon(){
        showIconPosition = -1;
    }

    public String getText(){
        return texts[showIconPosition];
    }

    public int getShowIconPosition() {
        return showIconPosition;
    }

    public void setShowIconPosition(int showIconPosition) {
        this.showIconPosition = showIconPosition;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position,int type);
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.face_board_text_item,parent,false);
        TextViewHolder holder = new TextViewHolder(itemView);
        mListHolder.add(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);

        holder.text.setText(texts[position]);
        Log.i("mtag", "onBindViewHolder: " + showIconPosition  +" -----"+position);
        if (showIconPosition == position){
            holder.icon.setVisibility(View.VISIBLE);
            Log.i("mtag", "onBindViewHolder: visible ");
        }else {
            holder.icon.setVisibility(View.GONE);
            Log.i("mtag", "onBindViewHolder:  gone");
        }

    }

    @Override
    public int getItemCount() {
        return texts == null ? 0 :texts.length;
    }

    public void destroy(){
        texts = null;
        mContext = null;
        for (int i = 0; i < mListHolder.size(); i++) {
            mListHolder.get(i).itemView.setOnClickListener(null);
        }
        mListHolder.clear();
        mListHolder = null;
    }

    @Override
    public void onClick(View v) {
       int position = (int) v.getTag();
        if (listener != null){
            listener.onItemClick(v ,position,type);
        }
    }

    class TextViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView icon;
        TextView text;
        public TextViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            icon = (ImageView) itemView.findViewById(R.id.icon);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
