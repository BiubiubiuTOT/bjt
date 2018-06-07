package com.bangjiat.bjt.module.home.work.permission.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class WorkersAdapter extends RecyclerView.Adapter<WorkersAdapter.ViewHolder> implements View.OnClickListener {
    private List<WorkersResult.RecordsBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public WorkersAdapter(List<WorkersResult.RecordsBean> lists, Context context) {
        this.lists = lists;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_workers, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final WorkersResult.RecordsBean bean = lists.get(position);
        String realname = bean.getRealname();
        String job = bean.getJob();

        if (realname != null && realname.equals("null")) {
            realname = "无";
        }
        if (realname == null)
            realname = "无";

        if (job != null && job.equals("null")) {
            job = "无";
        }
        if (job == null)
            job = "无";

        viewHolder.tv_name.setText(realname + "(" + job + ")");
        viewHolder.tv_phone.setText("电话：" + bean.getPhone());

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
        TextView tv_name, tv_phone;

        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_phone = view.findViewById(R.id.tv_phone);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

}