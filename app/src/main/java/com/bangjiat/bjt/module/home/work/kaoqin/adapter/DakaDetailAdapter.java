package com.bangjiat.bjt.module.home.work.kaoqin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaHistoryResult;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class DakaDetailAdapter extends RecyclerView.Adapter<DakaDetailAdapter.ViewHolder> implements View.OnClickListener {
    private List<DakaHistoryResult> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public void setLists(List<DakaHistoryResult> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    public DakaDetailAdapter(List<DakaHistoryResult> lists, Context context) {
        this.lists = lists;
        this.mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_daka_detail, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        DakaHistoryResult bean = lists.get(position);
        viewHolder.tv_name.setText(bean.getUserRealname());
        viewHolder.tv_in_time.setText(TimeUtils.changeToHM(bean.getInTime()));
        if (bean.getOutType() != 0)
            viewHolder.tv_out_time.setText(TimeUtils.changeToHM(bean.getOutTime()));

        if (bean.getOutType() == 1 && bean.getInType() == 1) {
            viewHolder.btn_update.setVisibility(View.GONE);
        }
        viewHolder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onBtnClick(view, viewHolder.getAdapterPosition());
            }
        });
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
        TextView tv_name, tv_in_time, tv_out_time;
        Button btn_update;

        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_in_time = view.findViewById(R.id.tv_in_time);
            tv_out_time = view.findViewById(R.id.tv_out_time);
            btn_update = view.findViewById(R.id.btn_update);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);

        void onBtnClick(View view, int position);
    }
}