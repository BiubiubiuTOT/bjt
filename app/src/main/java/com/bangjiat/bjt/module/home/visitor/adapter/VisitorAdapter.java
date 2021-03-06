package com.bangjiat.bjt.module.home.visitor.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.home.visitor.beans.VisitorBean;

import java.util.List;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.ViewHolder> {
    private List<VisitorBean.RecordsBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private int type;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public VisitorAdapter(List<VisitorBean.RecordsBean> lists, int type) {
        this.lists = lists;
        this.type = type;
    }

    public void setLists(List<VisitorBean.RecordsBean> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_visitor, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        VisitorBean.RecordsBean visitorBean = lists.get(position);
        if (type == 1)
            viewHolder.tv_name.setText("拜访人姓名：" + visitorBean.getVisitorName());
        else
            viewHolder.tv_name.setText("被访人姓名：" + visitorBean.getInterviewName());

        viewHolder.tv_reason.setText("访问事宜：" + visitorBean.getVisitMatter());
        viewHolder.tv_time.setText("拜访时间" + TimeUtils.changeToTime(visitorBean.getVisitTime()));
        int status = visitorBean.getStatus();

        String des = "";
        if (status != 1) {
            viewHolder.tv_message.setVisibility(View.VISIBLE);
            viewHolder.btn_refuse.setVisibility(View.GONE);
            viewHolder.btn_agree.setVisibility(View.GONE);
            if (status == 2) {
                des = "已同意";
            } else if (status == 3) {
                des = "已拒绝";
            }
            viewHolder.tv_message.setText(des);

        } else {
            viewHolder.tv_message.setVisibility(View.GONE);
            viewHolder.btn_refuse.setVisibility(View.VISIBLE);
            viewHolder.btn_agree.setVisibility(View.VISIBLE);
        }


        viewHolder.btn_refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onRefuseClick(view, position);
            }
        });
        viewHolder.btn_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onAgreeClick(view, position);
            }
        });

        if (visitorBean.getType() == 2 && type != 2) {
            viewHolder.btn_refuse.setVisibility(View.GONE);
            viewHolder.btn_agree.setVisibility(View.GONE);
            viewHolder.tv_message.setVisibility(View.GONE);
        }

        viewHolder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onDelete(view, viewHolder.getAdapterPosition());
            }
        });
        viewHolder.ll_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, viewHolder.getAdapterPosition());
            }
        });

        viewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_icon;
        TextView tv_name, tv_message, tv_reason, tv_time, tv_delete;
        Button btn_agree, btn_refuse;
        CardView ll_detail;

        public ViewHolder(View view) {
            super(view);
            iv_icon = view.findViewById(R.id.iv_icon);
            tv_name = view.findViewById(R.id.tv_name);
            tv_message = view.findViewById(R.id.tv_message);
            tv_time = view.findViewById(R.id.tv_time);
            tv_reason = view.findViewById(R.id.tv_reason);
            tv_delete = view.findViewById(R.id.tv_delete);

            btn_agree = view.findViewById(R.id.btn_agree);
            btn_refuse = view.findViewById(R.id.btn_refuse);
            ll_detail = view.findViewById(R.id.ll_detail);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);

        void onAgreeClick(View view, int position);

        void onRefuseClick(View view, int position);

        void onDelete(View view, int pos);
    }
}