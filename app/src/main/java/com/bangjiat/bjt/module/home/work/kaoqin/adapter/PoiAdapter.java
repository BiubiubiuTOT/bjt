package com.bangjiat.bjt.module.home.work.kaoqin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.PoiString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class PoiAdapter extends RecyclerView.Adapter<PoiAdapter.ViewHolder> implements View.OnClickListener {
    private List<PoiString> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;
    private Map<Integer, Boolean> map;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public List<PoiString> getLists() {
        return lists;
    }

    public Map<Integer, Boolean> getMap() {
        return map;
    }

    public PoiAdapter(List<PoiString> lists, Context context) {
        this.lists = lists;
        this.mContext = context;

        map = new HashMap<>();
        initCheck(lists.size());
    }

    private void initCheck(int size) {
        for (int i = 0; i < size; i++) {
            map.put(i, false);
        }
        map.put(0, true);
    }

    public void setLists(List<PoiString> lists) {
        this.lists = lists;
        initCheck(lists.size());
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pois, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.checkBox.setChecked(map.get(position));
        PoiString s = lists.get(position);
        if (s.isDefault()) {
            viewHolder.tv_district.setVisibility(View.VISIBLE);
            viewHolder.tv_district.setText(s.getName());
            viewHolder.tv_name.setText("[位置]");
        } else {
            viewHolder.tv_district.setVisibility(View.GONE);
            viewHolder.tv_name.setText(s.getName());
        }

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
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getKey() == position) {
                Boolean value = entry.getValue();
                entry.setValue(!value);
            } else {
                entry.setValue(false);
            }
        }

        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_district;
        CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_district = view.findViewById(R.id.tv_district);
            checkBox = view.findViewById(R.id.checkBox);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}