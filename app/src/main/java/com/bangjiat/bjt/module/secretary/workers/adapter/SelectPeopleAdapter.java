package com.bangjiat.bjt.module.secretary.workers.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;

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
    private List<WorkersResult.RecordsBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private OnCheckListener onCheckListener;
    private Context mContext;
    private final HashMap<Integer, Boolean> map;
    private boolean isShowCheck;

    public HashMap<Integer, Boolean> getMap() {
        return map;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnCheckChangedListener(OnCheckListener listener) {
        onCheckListener = listener;
    }

    public SelectPeopleAdapter(List<WorkersResult.RecordsBean> lists, Context context) {
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
        final WorkersResult.RecordsBean bean = lists.get(position);
        String realname = bean.getRealname();
        String job = bean.getJob();

        if (realname != null && realname.equals("null")) {
            realname = "无";
        }
        if (realname == null)
            realname = "无";

        if (job != null && job.equals("null")) {
            job = "无";
        }
        if (job == null)
            job = "无";

        viewHolder.tv_name.setText(realname + "(" + job + ")");
        viewHolder.tv_phone.setText("电话：" + bean.getPhone());

        viewHolder.checkBox.setChecked(map.get(position));
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                map.put(position, !map.get(position));
                notifyDataSetChanged();

                if (onCheckListener != null)
                    onCheckListener.onCheckChanged();
            }
        });

        viewHolder.checkBox.setVisibility(isShowCheck ? View.VISIBLE : View.GONE);

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

    public void setDone() {
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(false);
        }
        isShowCheck = false;
        notifyDataSetChanged();
    }

    public void setShowCheck(boolean showCheck) {
        isShowCheck = showCheck;
        notifyDataSetChanged();
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