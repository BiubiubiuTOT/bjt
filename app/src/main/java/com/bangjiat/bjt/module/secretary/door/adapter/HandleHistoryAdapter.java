package com.bangjiat.bjt.module.secretary.door.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.secretary.door.beans.HandleHistoryDetail;

import java.util.List;

/**
 * 邮箱:1256144200@qq.com
 * 操作记录
 */
public class HandleHistoryAdapter extends RecyclerView.Adapter<HandleHistoryAdapter.ViewHolder> implements View.OnClickListener {
    private List<HandleHistoryDetail> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public HandleHistoryAdapter(List<HandleHistoryDetail> lists, Context context) {
        this.lists = lists;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_handle_history, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final HandleHistoryDetail historyBean = lists.get(position);
        viewHolder.tv_name.setText(historyBean.getApplyRealname());
        viewHolder.tv_status.setText("");
        viewHolder.tv_time.setText(historyBean.getCtime());

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
        TextView tv_name, tv_status, tv_time;

        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_status = view.findViewById(R.id.tv_status);
            tv_time = view.findViewById(R.id.tv_time);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}