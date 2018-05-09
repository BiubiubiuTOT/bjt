package com.bangjiat.bjt.module.secretary.workers.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class AddWorkersAdapter extends RecyclerView.Adapter<AddWorkersAdapter.ViewHolder> implements View.OnClickListener {
    private List<WorkersResult.RecordsBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public List<WorkersResult.RecordsBean> getLists() {
        return lists;
    }

    public AddWorkersAdapter(List<WorkersResult.RecordsBean> lists) {
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
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        final WorkersResult.RecordsBean bean = lists.get(position);

        viewHolder.rl_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.d("pi: " + viewHolder.getAdapterPosition());
                delete(viewHolder.getAdapterPosition());
            }
        });

        viewHolder.et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                bean.setRealname(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        viewHolder.et_department.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                bean.setDepartment(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        viewHolder.et_duty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                bean.setJob(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        viewHolder.et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                bean.setPhone(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        viewHolder.et_card.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                bean.setIdNumber(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        viewHolder.itemView.setTag(position);
    }

    private void delete(int position) {
        lists.remove(position);
        notifyItemRemoved(position);
        if (position != getItemCount() - 1) { // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, getItemCount() - position);
        }
    }

    public void add() {
        lists.add(new WorkersResult.RecordsBean());
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
        EditText et_name, et_phone, et_duty, et_department, et_card;
        RelativeLayout rl_delete;

        public ViewHolder(View view) {
            super(view);
            et_name = view.findViewById(R.id.et_name);
            et_phone = view.findViewById(R.id.et_phone);
            et_card = view.findViewById(R.id.et_card);
            et_duty = view.findViewById(R.id.et_duty);
            et_department = view.findViewById(R.id.et_department);
            rl_delete = view.findViewById(R.id.rl_delete);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

}