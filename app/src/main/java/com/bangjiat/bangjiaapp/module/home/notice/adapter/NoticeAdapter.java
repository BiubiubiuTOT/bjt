package com.bangjiat.bangjiaapp.module.home.notice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.home.notice.beans.NoticeBean;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> implements View.OnClickListener {
    private List<NoticeBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public NoticeAdapter(List<NoticeBean> lists) {
        this.lists = lists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notice, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        NoticeBean noticeBean = lists.get(position);
        viewHolder.iv_not_read.setVisibility(noticeBean.isRead() ? View.VISIBLE : View.INVISIBLE);
        viewHolder.tv_title.setText(noticeBean.getTitle());
        viewHolder.tv_content.setText(noticeBean.getContent());
        viewHolder.tv_time.setText(noticeBean.getTime());

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
        ImageView iv_not_read;
        TextView tv_title, tv_time, tv_content;

        public ViewHolder(View view) {
            super(view);
            iv_not_read = view.findViewById(R.id.iv_not_read);
            tv_title = view.findViewById(R.id.tv_title);
            tv_time = view.findViewById(R.id.tv_time);
            tv_content = view.findViewById(R.id.tv_content);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}