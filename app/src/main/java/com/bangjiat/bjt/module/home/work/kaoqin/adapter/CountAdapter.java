package com.bangjiat.bjt.module.home.work.kaoqin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.CountBean;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class CountAdapter extends RecyclerView.Adapter<CountAdapter.ViewHolder> implements View.OnClickListener {
    private List<CountBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public List<CountBean> getLists() {
        return lists;
    }

    public void setLists(List<CountBean> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    public CountAdapter(List<CountBean> lists, Context context) {
        this.lists = lists;
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_count, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        CountBean bean = lists.get(position);
        int time = bean.getTime();
        if (time != 0)
            viewHolder.tv_text.setText(bean.getName() + "," + bean.getCounts() + "次，共" + time + "分钟");
        else
            viewHolder.tv_text.setText(bean.getName() + "," + bean.getCounts() + "次");
        viewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_text;

        public ViewHolder(View view) {
            super(view);
            tv_text = view.findViewById(R.id.tv_text);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}