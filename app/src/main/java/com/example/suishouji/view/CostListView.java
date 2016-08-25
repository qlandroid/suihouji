package com.example.suishouji.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suishouji.R;
import com.example.suishouji.bean.CostBean;
import com.example.suishouji.bean.CostChildBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/25.
 */
public class CostListView extends RecyclerView {

    public CostListView(Context context) {
        super(context);

    }

    public CostListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public CostListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }



    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
    }

    GroupAdapter mGroupAdapter;


    public void run(List<CostBean> list, Map<Integer, List<CostChildBean>> childMap) {
        mGroupAdapter = new GroupAdapter(list, childMap, getContext());
        setAdapter(mGroupAdapter);
    }


    class GroupAdapter extends Adapter<GroupAdapter.GroupViewHolder> implements OnClickListener {
        private List<CostBean> list;
        private Map<Integer, List<CostChildBean>> childMap;
        private Context mContext;
        private boolean showItem = false;
        private ChildAdapter childAdapter;
        private boolean[] isShow ;
        public GroupAdapter(List<CostBean> list, Map<Integer, List<CostChildBean>> childMap, Context mContext) {
            this.list = list;
            this.childMap = childMap;
            this.mContext = mContext;
            if (list!=null){
                isShow = new boolean[list.size()];
            }
        }

        @Override
        public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.cost_list, parent, false);
            return new GroupViewHolder(view);
        }

        private static final String TAG = "GroupAdapter";
        @Override
        public void onBindViewHolder(GroupViewHolder holder, int position) {
            holder.itemView.setTag(holder.showContent);
            holder.showContent.setTag(position);
            holder.itemView.setOnClickListener(this);
            if(isShow[position]){
                holder.showContent.setVisibility(View.VISIBLE);
            }else {
                holder.showContent.setVisibility(View.GONE);
            }
            Log.i(TAG, "onBindViewHolder: "+isShow[position]);
            CostBean bean = list.get(position);
            holder.tv_month.setText(bean.getMonth());
            holder.tv_money.setText(bean.getMoney() + "");
            holder.tv_time_content.setText(bean.getTime());

            /*if (childMap != null) {
                List<CostChildBean> childList = childMap.get(position);
                if (childList != null && childList.size() != 0) {
                    holder.nullContent.setVisibility(View.GONE);
                } else {
                    holder.nullContent.setVisibility(View.VISIBLE);
                }
                childAdapter = new ChildAdapter(mContext, childList);
                holder.rv.setAdapter(childAdapter);
            }else {
                holder.nullContent.setVisibility(View.VISIBLE);
            }*/
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public void onClick(View v) {
            View holder = (View) v.getTag();
            int position = (int) holder.getTag();
            Toast.makeText(mContext, position+"被点击", Toast.LENGTH_SHORT).show();

            isShow[position] = !isShow[position];
            notifyItemChanged(position);
        }

        class GroupViewHolder extends ViewHolder {
            View itemView;
            View nullContent;
            View showContent;
            RecyclerView rv;
            TextView tv_month, tv_time_content, tv_money;

            public GroupViewHolder(View itemView) {
                super(itemView);
                this.itemView = itemView;
                tv_time_content = (TextView) itemView.findViewById(R.id.cost_time_content_textView);
                tv_money = (TextView) itemView.findViewById(R.id.cost_money_textView);
                tv_month = (TextView) itemView.findViewById(R.id.cost_month_textView);
                showContent = itemView.findViewById(R.id.cost_content_layout);
                nullContent = itemView.findViewById(R.id.null_content_layout);
                rv = (RecyclerView) itemView.findViewById(R.id.cost_child_list_view);
                rv.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            }
        }
    }

    class ChildAdapter extends Adapter<ChildAdapter.ChildViewHolder> {
        private Context mContext;
        private List<CostChildBean> mList;

        public ChildAdapter(Context mContext, List<CostChildBean> mList) {
            this.mContext = mContext;
            this.mList = mList;
        }

        @Override
        public ChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.cost_child_list, parent, false);
            return new ChildViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ChildViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        class ChildViewHolder extends ViewHolder {
            View itemView;

            public ChildViewHolder(View itemView) {
                super(itemView);
                this.itemView = itemView;
            }
        }
    }
}
