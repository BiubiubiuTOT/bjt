package com.bangjiat.bjt.module.home.work.kaoqin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bangjiat.bjt.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class PoiAdapter extends RecyclerView.Adapter<PoiAdapter.ViewHolder> implements View.OnClickListener {
    private List<String> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;
    private Map<Integer, Boolean> map;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public Map<Integer, Boolean> getMap() {
        return map;
    }

    public PoiAdapter(List<String> lists, Context context) {
        this.lists = lists;
        this.mContext = context;

        map = new HashMap<>();
        for (int i = 0; i < lists.size(); i++) {
            map.put(i, false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_work_day, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.checkBox.setChecked(map.get(position));
        String s = lists.get(position);
        viewHolder.tv_name.setText(s);

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

    public void setCheck(int position) {
        Boolean aBoolean = map.get(position);
        map.put(position, !aBoolean);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            checkBox = view.findViewById(R.id.checkBox);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}