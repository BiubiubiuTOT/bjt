package com.bangjiat.bjt.module.park.apply.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.park.car.beans.CarBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> implements View.OnClickListener {
    private List<CarBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public CarAdapter(List<CarBean> lists, Context context) {
        this.lists = lists;
        this.mContext = context;
    }

    public void setLists(List<CarBean> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_car, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final CarBean bean = lists.get(position);

        viewHolder.tv_number.setText("车牌：" + bean.getPlateNumber());
        viewHolder.tv_name.setText("姓名：" + bean.getName());
        viewHolder.tv_type.setText("车型：" + bean.getModel());
        viewHolder.tv_color.setText("颜色：" + bean.getColor());
        Glide.with(mContext).load(bean.getResource()).centerCrop().into(viewHolder.iv_car);

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
        TextView tv_name, tv_number, tv_type, tv_color;
        ImageView iv_car;

        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_type = view.findViewById(R.id.tv_type);
            tv_color = view.findViewById(R.id.tv_color);
            tv_number = view.findViewById(R.id.tv_number);
            iv_car = view.findViewById(R.id.iv_car);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}