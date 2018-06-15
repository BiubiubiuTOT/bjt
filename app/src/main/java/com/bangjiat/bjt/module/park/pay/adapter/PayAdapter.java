package com.bangjiat.bjt.module.park.pay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.park.pay.beans.PayListResult;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class PayAdapter extends RecyclerView.Adapter<PayAdapter.ViewHolder> implements View.OnClickListener {
    private List<PayListResult> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public PayAdapter(List<PayListResult> lists, Context context) {
        this.lists = lists;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pay, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final PayListResult historyBean = lists.get(position);
        viewHolder.tv_number.setText("车牌号：" + historyBean.getPlateNumber());
        viewHolder.tv_time1.setText("停车有效期：" + TimeUtils.changeToYMD(historyBean.getCtime()) + "至"
                + TimeUtils.changeToYMD(historyBean.getTerminalTime()));
        viewHolder.tv_parking.setText("停车场：" + historyBean.getSpaceName());
        viewHolder.tv_time.setText("缴费起止日期：" + TimeUtils.changeToYMD(historyBean.getBeginTime())
                + "至" + TimeUtils.changeToYMD(historyBean.getEndTime()));
        Glide.with(mContext).load(historyBean.getResource()).centerCrop().error(R.mipmap.my_head).into(viewHolder.iv_car);

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
        TextView tv_number, tv_parking, tv_time, tv_time1;
        ImageView iv_car;

        public ViewHolder(View view) {
            super(view);
            tv_number = view.findViewById(R.id.tv_number);
            tv_time1 = view.findViewById(R.id.tv_time1);
            tv_parking = view.findViewById(R.id.tv_parking);
            tv_time = view.findViewById(R.id.tv_time);
            iv_car = view.findViewById(R.id.iv_car);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}