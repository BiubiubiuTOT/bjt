package com.bangjiat.bangjiaapp.module.home.visitor.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.home.visitor.beans.VisitorBean;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.ViewHolder> implements View.OnClickListener {
    private List<VisitorBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public VisitorAdapter(List<VisitorBean> lists) {
        this.lists = lists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_visitor, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final VisitorBean visitorBean = lists.get(position);
        viewHolder.tv_name.setText(visitorBean.getName());
        viewHolder.tv_reason.setText(visitorBean.getReason());

        if (visitorBean.isRead()) {
            viewHolder.tv_message.setVisibility(View.VISIBLE);
            viewHolder.tv_message.setText(visitorBean.getMessage());
            viewHolder.btn_refuse.setVisibility(View.GONE);
            viewHolder.btn_agree.setVisibility(View.GONE);
        } else {
            viewHolder.tv_message.setVisibility(View.GONE);
            viewHolder.btn_refuse.setVisibility(View.VISIBLE);
            viewHolder.btn_agree.setVisibility(View.VISIBLE);
        }

        viewHolder.btn_refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visitorBean.setMessage("已拒绝");
                visitorBean.setRead(true);
                notifyDataSetChanged();
            }
        });
        viewHolder.btn_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visitorBean.setMessage("已同意");
                visitorBean.setRead(true);
                notifyDataSetChanged();
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
        ImageView iv_icon;
        TextView tv_name, tv_message, tv_reason;
        Button btn_agree, btn_refuse;

        public ViewHolder(View view) {
            super(view);
            iv_icon = view.findViewById(R.id.iv_icon);
            tv_name = view.findViewById(R.id.tv_name);
            tv_message = view.findViewById(R.id.tv_message);
            tv_reason = view.findViewById(R.id.tv_reason);

            btn_agree = view.findViewById(R.id.btn_agree);
            btn_refuse = view.findViewById(R.id.btn_refuse);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}