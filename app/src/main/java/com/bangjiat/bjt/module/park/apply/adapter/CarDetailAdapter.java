package com.bangjiat.bjt.module.park.apply.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.module.park.apply.beans.ParkApplyDetail;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class CarDetailAdapter extends RecyclerView.Adapter<CarDetailAdapter.ViewHolder> implements View.OnClickListener {
    private List<ParkApplyDetail> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;
    private int status;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public CarDetailAdapter(List<ParkApplyDetail> lists, Context context, int status) {
        this.lists = lists;
        this.status = status;
        this.mContext = context;
    }

    public void setLists(List<ParkApplyDetail> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    public List<ParkApplyDetail> getLists() {
        return lists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_car_detail, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final ParkApplyDetail bean = lists.get(position);
        viewHolder.tv_name.setText(bean.getCarName());
        viewHolder.tv_car_number.setText(bean.getPlateNumber());
        int type = bean.getType();
        if (type == 1) {
            String lotNumber = bean.getLotNumber();
            if (Constants.isParkAdmin()) {//停车场管理员才能看见车位
                switch (status) {
                    case 1://待审核 显示 可以点击
                        if (lotNumber == null)
                            viewHolder.tv_number.setHint("请选择");
                        else
                            viewHolder.tv_number.setText(lotNumber);

                        viewHolder.ll_number.setVisibility(View.VISIBLE);
                        viewHolder.tv_number.setEnabled(true);
                        break;
                    case 2://已同意 不能点击 需要显示
                        viewHolder.tv_number.setText(lotNumber);
                        viewHolder.tv_number.setEnabled(false);
                        viewHolder.ll_number.setVisibility(View.VISIBLE);
                        break;
                    case 3://已拒绝 不显示
                        viewHolder.ll_number.setVisibility(View.GONE);
                        viewHolder.tv_number.setEnabled(false);
                        break;
                }

            }
            if (Constants.isWorkAdmin() && status == 2) {
                viewHolder.tv_number.setText(lotNumber);
                viewHolder.tv_number.setEnabled(false);
                viewHolder.ll_number.setVisibility(View.VISIBLE);
            }

            viewHolder.tv_type.setText("固定车位");
        } else {
            viewHolder.ll_number.setVisibility(View.GONE);
            viewHolder.tv_type.setText("临停车位");
        }
        viewHolder.tv_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onLotClick(view, viewHolder.getAdapterPosition());
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
        TextView tv_name, tv_car_number, tv_type, tv_number;
        LinearLayout ll_number;

        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_car_number = view.findViewById(R.id.tv_car_number);
            tv_type = view.findViewById(R.id.tv_type);
            tv_number = view.findViewById(R.id.tv_number);
            ll_number = view.findViewById(R.id.ll_number);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);

        void onLotClick(View view, int position);
    }
}