package com.bangjiat.bangjiaapp.module.park.pay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.park.apply.beans.ApplyHistoryBean;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class PayHistoryAdapter extends RecyclerView.Adapter<PayHistoryAdapter.ViewHolder> implements View.OnClickListener {
    private List<ApplyHistoryBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public PayHistoryAdapter(List<ApplyHistoryBean> lists, Context context) {
        this.lists = lists;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pay_history, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final ApplyHistoryBean historyBean = lists.get(position);

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
        TextView tv_number, tv_parking, tv_money, tv_time;

        public ViewHolder(View view) {
            super(view);
            tv_number = view.findViewById(R.id.tv_number);
            tv_parking = view.findViewById(R.id.tv_parking);
            tv_money = view.findViewById(R.id.tv_money);
            tv_time = view.findViewById(R.id.tv_time);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}