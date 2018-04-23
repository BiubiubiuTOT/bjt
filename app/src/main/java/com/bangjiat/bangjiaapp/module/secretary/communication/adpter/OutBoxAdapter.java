package com.bangjiat.bangjiaapp.module.secretary.communication.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.secretary.communication.beans.OutBoxBean;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class OutBoxAdapter extends RecyclerView.Adapter<OutBoxAdapter.ViewHolder> implements View.OnClickListener {
    private List<OutBoxBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public List<OutBoxBean> getLists() {
        return lists;
    }

    public OutBoxAdapter(List<OutBoxBean> lists) {
        this.lists = lists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_out_box, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final OutBoxBean bean = lists.get(position);

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
        TextView tv_name, tv_message, tv_detail, tv_time;
        ImageView iv_right;
        CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_message = view.findViewById(R.id.tv_message);
            tv_detail = view.findViewById(R.id.tv_detail);
            tv_time = view.findViewById(R.id.tv_time);
            iv_right = view.findViewById(R.id.iv_right);
            checkBox = view.findViewById(R.id.checkbox);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

}