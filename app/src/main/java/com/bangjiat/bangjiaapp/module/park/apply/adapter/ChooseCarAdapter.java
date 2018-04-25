package com.bangjiat.bangjiaapp.module.park.apply.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.park.apply.beans.CarInfoBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class ChooseCarAdapter extends RecyclerView.Adapter<ChooseCarAdapter.ViewHolder> implements View.OnClickListener {
    private final HashMap<Integer, Boolean> map;
    private List<CarInfoBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public HashMap<Integer, Boolean> getMap() {
        return map;
    }

    public ChooseCarAdapter(List<CarInfoBean> lists, Context context) {
        this.lists = lists;
        this.mContext = context;

        map = new HashMap<>();
        for (int i = 0; i < lists.size(); i++) {
            map.put(i, false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_choose_car, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final CarInfoBean bean = lists.get(position);

        viewHolder.tv_car_number.setText(bean.getCarNumber());
        viewHolder.tv_name.setText(bean.getUserName());
        viewHolder.tv_type.setText(bean.getType());
        viewHolder.tv_color.setText(bean.getColor());

        viewHolder.checkbox.setChecked(map.get(position));
        viewHolder.checkbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                map.put(position, !map.get(position));
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
        TextView tv_name, tv_car_number, tv_type, tv_color;
        CheckBox checkbox;

        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_type = view.findViewById(R.id.tv_type);
            tv_color = view.findViewById(R.id.tv_color);
            tv_car_number = view.findViewById(R.id.tv_car_number);
            checkbox = view.findViewById(R.id.checkbox);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}