package com.bangjiat.bjt.module.secretary.service.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.secretary.service.beans.BuildingAdminListResult;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class AdminListAdapter extends RecyclerView.Adapter<AdminListAdapter.ViewHolder> implements View.OnClickListener {
    private List<BuildingAdminListResult> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public AdminListAdapter(List<BuildingAdminListResult> lists, Context context) {
        this.lists = lists;
        this.mContext = context;
    }

    public void setLists(List<BuildingAdminListResult> lists) {
        this.lists = lists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_admin_list, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        BuildingAdminListResult s = lists.get(position);
        viewHolder.tv_name.setText(s.getRealname());
        viewHolder.tv_phone.setText(s.getUsername());

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
        TextView tv_phone, tv_name;

        public ViewHolder(View view) {
            super(view);
            tv_phone = view.findViewById(R.id.tv_phone);
            tv_name = view.findViewById(R.id.tv_name);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}