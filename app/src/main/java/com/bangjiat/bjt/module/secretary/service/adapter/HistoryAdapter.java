package com.bangjiat.bjt.module.secretary.service.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.secretary.door.beans.ApplyHistoryBean;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> implements View.OnClickListener {
    private List<ApplyHistoryBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public HistoryAdapter(List<ApplyHistoryBean> lists, Context context) {
        this.lists = lists;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_apply_history, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final ApplyHistoryBean historyBean = lists.get(position);
        switch (5) {
            case 1:
                viewHolder.tv_apply_status.setTextColor(mContext.getResources().getColor(R.color.apply_history_applying));
                break;
            case 2:
                viewHolder.tv_apply_status.setTextColor(mContext.getResources().getColor(R.color.apply_history_pass));
                break;
            case 3:
                viewHolder.tv_apply_status.setTextColor(mContext.getResources().getColor(R.color.apply_history_fail));
                break;
        }

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
        TextView tv_company_name, tv_apply_people, tv_apply_status;

        public ViewHolder(View view) {
            super(view);
            tv_company_name = view.findViewById(R.id.tv_company_name);
            tv_apply_people = view.findViewById(R.id.tv_apply_people);
            tv_apply_status = view.findViewById(R.id.tv_apply_status);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}