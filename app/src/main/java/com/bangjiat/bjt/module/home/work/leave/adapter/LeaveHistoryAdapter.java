package com.bangjiat.bjt.module.home.work.leave.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.home.work.leave.beans.CompanyLeaveResult;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class LeaveHistoryAdapter extends RecyclerView.Adapter<LeaveHistoryAdapter.ViewHolder> implements View.OnClickListener {
    private List<CompanyLeaveResult.RecordsBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public LeaveHistoryAdapter(List<CompanyLeaveResult.RecordsBean> lists, Context context) {
        this.lists = lists;
        this.mContext = context;
    }

    public void setLists(List<CompanyLeaveResult.RecordsBean> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_leave, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        CompanyLeaveResult.RecordsBean noticeBean = lists.get(position);
        int status = noticeBean.getStatus();
        String des = "";
        switch (status) {
            case 1:
                viewHolder.tv_status.setTextColor(mContext.getResources().getColor(R.color.apply_history_applying));
                des = "待审核";
                break;
            case 2:
                viewHolder.tv_status.setTextColor(mContext.getResources().getColor(R.color.apply_history_pass));
                des = "已通过";
                break;
            case 3:
                viewHolder.tv_status.setTextColor(mContext.getResources().getColor(R.color.apply_history_fail));
                des = "未通过";
                break;
        }
        viewHolder.tv_status.setText(des);
        int type = noticeBean.getType();
        String typeDes = "";
        if (type == 1) {
            typeDes = "事假";
        } else if (type == 2) {
            typeDes = "病假";
        } else if (type == 3) {
            typeDes = "出差";
        } else typeDes = "其他";
        viewHolder.tv_type.setText("请假类型：" + typeDes);
        viewHolder.tv_person.setText("申请人：" + noticeBean.getApplyer());
        viewHolder.tv_time.setText("时间：" + TimeUtils.changeToTime(noticeBean.getCtime()));

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
        TextView tv_person, tv_time, tv_type, tv_status;

        public ViewHolder(View view) {
            super(view);
            tv_person = view.findViewById(R.id.tv_person);
            tv_time = view.findViewById(R.id.tv_time);
            tv_status = view.findViewById(R.id.tv_status);
            tv_type = view.findViewById(R.id.tv_type);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}