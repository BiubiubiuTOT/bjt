package com.bangjiat.bjt.module.secretary.workers.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.secretary.door.beans.PeopleBean;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class AddWorkersAdapter extends RecyclerView.Adapter<AddWorkersAdapter.ViewHolder> implements View.OnClickListener {
    private List<PeopleBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public List<PeopleBean> getLists() {
        return lists;
    }

    public AddWorkersAdapter(List<PeopleBean> lists) {
        this.lists = lists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_worker, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final PeopleBean bean = lists.get(position);

        viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(position);
            }
        });

        viewHolder.itemView.setTag(position);
    }

    private void delete(int position) {
        lists.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
        if (position != getItemCount() - 1) { // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, getItemCount() - position);
        }
    }

    public void add() {
        lists.add(new PeopleBean());
        notifyItemInserted(getItemCount() - 1);
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
        EditText et_name, et_phone, et_duty, et_department;
        ImageView iv_delete;

        public ViewHolder(View view) {
            super(view);
            et_name = view.findViewById(R.id.et_name);
            et_phone = view.findViewById(R.id.et_phone);
            et_duty = view.findViewById(R.id.et_duty);
            et_department = view.findViewById(R.id.et_department);
            iv_delete = view.findViewById(R.id.iv_delete);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

}