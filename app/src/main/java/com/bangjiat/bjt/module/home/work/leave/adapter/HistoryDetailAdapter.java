package com.bangjiat.bjt.module.home.work.leave.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.secretary.workers.beans.ApproverBean;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class HistoryDetailAdapter extends RecyclerView.Adapter<HistoryDetailAdapter.ViewHolder> implements View.OnClickListener {
    private List<ApproverBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public HistoryDetailAdapter(List<ApproverBean> lists, Context context) {
        this.lists = lists;
        this.mContext = context;
    }

    public void setLists(List<ApproverBean> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_detail, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final ApproverBean bean = lists.get(position);
        int status = bean.getStatus();
        String des = "";
        switch (status) {
            case 0:
                viewHolder.tv_status.setTextColor(mContext.getResources().getColor(R.color.apply_history_applying));
                des = "待审核";
                break;
            case 1:
                viewHolder.tv_status.setTextColor(mContext.getResources().getColor(R.color.apply_history_pass));
                des = "已同意";
                break;
            case 2:
                viewHolder.tv_status.setTextColor(mContext.getResources().getColor(R.color.apply_history_fail));
                des = "已拒绝";
                break;
            case 3:
                viewHolder.tv_status.setTextColor(mContext.getResources().getColor(R.color.apply_history_applying));
                des = "已转批";
                break;
            default:
                viewHolder.tv_status.setTextColor(mContext.getResources().getColor(R.color.dialog_title));
                des = "发起申请";
                break;
        }
        viewHolder.tv_status.setText(des);
        viewHolder.tv_time.setText(TimeUtils.changeToTime(bean.getLastTime()));
        viewHolder.tv_name.setText(bean.getLastUserRealname());


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