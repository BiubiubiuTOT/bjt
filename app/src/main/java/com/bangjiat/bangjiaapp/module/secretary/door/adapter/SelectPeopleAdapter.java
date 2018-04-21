package com.bangjiat.bangjiaapp.module.secretary.door.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.secretary.door.beans.PeopleBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class SelectPeopleAdapter extends RecyclerView.Adapter<SelectPeopleAdapter.ViewHolder> implements View.OnClickListener {
    private List<PeopleBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private OnCheckListener onCheckListener;
    private Context mContext;
    private final HashMap<Integer, Boolean> map;

    public HashMap<Integer, Boolean> getMap() {
        return map;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnCheckChangedListener(OnCheckListener listener) {
        onCheckListener = listener;
    }

    public SelectPeopleAdapter(List<PeopleBean> lists, Context context) {
        this.lists = lists;
        this.mContext = context;

        map = new HashMap<>();
        for (int i = 0; i < lists.size(); i++) {
            map.put(i, false);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_select_people, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final PeopleBean bean = lists.get(position);
        viewHolder.tv_name.setText(bean.getName() + "(产品总监)");
        viewHolder.tv_phone.setText("电话：" + bean.getPhone());

        viewHolder.checkBox.setChecked(map.get(position));
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                map.put(position, !map.get(position));
                notifyDataSetChanged();

                onCheckListener.onCheckChanged();
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


    /**
     * 全选
     */
    public void selectAll() {
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        boolean shouldall = false;
        for (Map.Entry<Integer, Boolean> entry : entries) {
            Boolean value = entry.getValue();
            if (!value) {
                shouldall = true;
                break;
            }
        }
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(shouldall);
        }
        notifyDataSetChanged();

        onCheckListener.onCheckChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_phone;
        CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_phone = view.findViewById(R.id.tv_phone);
            checkBox = view.findViewById(R.id.checkbox);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnCheckListener {
        void onCheckChanged();
    }
}