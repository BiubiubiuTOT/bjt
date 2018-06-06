package com.bangjiat.bjt.module.park.car.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class MyCarAdapter extends RecyclerView.Adapter<MyCarAdapter.ViewHolder> {
    private List<CarBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public MyCarAdapter(List<CarBean> lists, Context context) {
        this.lists = lists;
        this.mContext = context;
    }

    public void setLists(List<CarBean> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    public List<CarBean> getLists() {
        return lists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mycar, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final CarBean bean = lists.get(position);

        viewHolder.tv_number.setText("车牌：" + bean.getPlateNumber());
        viewHolder.tv_model.setText("车型：" + bean.getModel());
        viewHolder.tv_color.setText("颜色：" + bean.getColor());
        Glide.with(mContext).load(bean.getResource()).centerCrop().into(viewHolder.iv_car);
        viewHolder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onDelete(view, viewHolder.getAdapterPosition());
            }
        });
        viewHolder.ll_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, viewHolder.getAdapterPosition());
            }
        });

        viewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_number, tv_model, tv_color, tv_delete;
        ImageView iv_car;
        LinearLayout ll_detail;

        public ViewHolder(View view) {
            super(view);
            tv_color = view.findViewById(R.id.tv_color);
            tv_model = view.findViewById(R.id.tv_model);
            tv_delete = view.findViewById(R.id.tv_delete);
            tv_number = view.findViewById(R.id.tv_number);
            iv_car = view.findViewById(R.id.iv_car);
            ll_detail = view.findViewById(R.id.ll_detail);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);

        void onDelete(View view, int pos);
    }
}