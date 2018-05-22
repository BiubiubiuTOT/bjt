package com.bangjiat.bjt.module.me.bill.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.me.bill.beans.PageBillBean;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> implements View.OnClickListener {
    private List<PageBillBean.RecordsBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context context;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public BillAdapter(Context context, List<PageBillBean.RecordsBean> lists) {
        this.lists = lists;
        this.context = context;
    }

    public void setLists(List<PageBillBean.RecordsBean> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bill, viewGroup, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        PageBillBean.RecordsBean bean = lists.get(position);
        viewHolder.tv_company_name.setText(bean.getCompanyName());
        viewHolder.tv_time.setText("截止时间：" + TimeUtils.changeToTime(bean.getCutOffTime()));
        viewHolder.tv_money.setText(bean.getMoney() + "元");
        int type = bean.getType();
        if (type == 1) {
            viewHolder.tv_type.setText("房租");
        } else if (type == 2)
            viewHolder.tv_type.setText("物业费");
        int status = bean.getStatus();
        String des = "";
        switch (status) {
            case 0:
                des = "已逾期";
                viewHolder.tv_status.setTextColor(context.getResources().getColor(R.color.apply_history_fail));
                break;
            case 1:
                des = "已缴纳";
                viewHolder.tv_status.setTextColor(context.getResources().getColor(R.color.apply_history_pass));
                break;
            case 2:
                viewHolder.tv_status.setTextColor(context.getResources().getColor(R.color.apply_history_applying));
                des = "未缴纳";
                break;
        }
        viewHolder.tv_status.setText(des);


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
        TextView tv_company_name, tv_time, tv_status, tv_money, tv_type;

        public ViewHolder(View view) {
            super(view);
            tv_company_name = view.findViewById(R.id.tv_company_name);
            tv_time = view.findViewById(R.id.tv_time);
            tv_status = view.findViewById(R.id.tv_status);
            tv_money = view.findViewById(R.id.tv_money);
            tv_type = view.findViewById(R.id.tv_type);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}