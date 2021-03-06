package com.bangjiat.bjt.module.home.work.kaoqin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.PoiAddressBean;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> implements View.OnClickListener {
    private List<PoiAddressBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public List<PoiAddressBean> getLists() {
        return lists;
    }

    public void setLists(List<PoiAddressBean> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    public TipsAdapter(List<PoiAddressBean> lists, Context context) {
        this.lists = lists;
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tip, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        PoiAddressBean bean = lists.get(position);
        viewHolder.tv_name.setText(bean.getTitle());
        viewHolder.tv_district.setText(bean.getText());

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
        TextView tv_name, tv_district;

        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_district = view.findViewById(R.id.tv_district);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}