package com.bangjiat.bjt.module.secretary.communication.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.secretary.communication.beans.EmailBean;
import com.bangjiat.bjt.module.secretary.contact.util.GlideCircleTransform;
import com.bumptech.glide.Glide;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.bangjiat.bjt.module.secretary.communication.ui.BoxActivity.IN;

/**
 * Created by Ligh on 2016/9/9 15:33
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */
public class OutBoxAdapter extends RecyclerView.Adapter<OutBoxAdapter.ViewHolder> {
    private final Context context;
    private List<EmailBean> lists;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private OnCheckListener onCheckListener;
    private final HashMap<Integer, Boolean> map;
    private boolean isShowCheck;
    private int type;
    private UserInfo info;

    private onSwipeListener mOnSwipeListener;

    public void setLists(List<EmailBean> lists) {
        this.lists = lists;
        resetMap();
        notifyDataSetChanged();
    }

    public onSwipeListener getOnDelListener() {
        return mOnSwipeListener;
    }

    public void setOnSwipeListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnCheckChangedListener(OnCheckListener listener) {
        onCheckListener = listener;
    }

    public HashMap<Integer, Boolean> getMap() {
        return map;
    }

    public List<EmailBean> getLists() {
        return lists;
    }

    public OutBoxAdapter(List<EmailBean> lists, Context context, int type) {
        this.lists = lists;
        info = UserInfo.first(UserInfo.class);
        this.context = context;
        this.type = type;

        map = new HashMap<>();
        resetMap();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_out_box, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.tv_mark.setVisibility(View.VISIBLE);
        viewHolder.tv_delete.setText("删除");
        final EmailBean bean = lists.get(position);
        if (type == IN) {
            viewHolder.tv_name.setText(info.getNickname());
            viewHolder.view_unRead.setVisibility(bean.getEmailStatus() == 0 ? View.VISIBLE : View.GONE);
        } else {
            viewHolder.tv_name.setText(bean.getReceiver());
        }
        viewHolder.tv_time.setText(TimeUtils.convertTimeToFormat(bean.getSendDate()));
        viewHolder.tv_message.setText(bean.getTitle());
        viewHolder.tv_detail.setText(bean.getContent());
        String receiverAvatar = bean.getReceiverAvatar();
        Glide.with(context).load(receiverAvatar).centerCrop().
                transform(new GlideCircleTransform(context)).placeholder(R.mipmap.my_head).into(viewHolder.iv_head);
        ((SwipeMenuLayout) viewHolder.itemView).setSwipeEnable(!isShowCheck);
        viewHolder.iv_right.setVisibility(isShowCheck ? View.GONE : View.VISIBLE);

        viewHolder.checkBox.setChecked(map.get(position));
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                map.put(position, !map.get(position));
                notifyDataSetChanged();

                onCheckListener.onCheckChanged();
            }
        });

        viewHolder.checkBox.setVisibility(isShowCheck ? View.VISIBLE : View.GONE);

        viewHolder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(viewHolder.getAdapterPosition());
                }
            }
        });
        viewHolder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolder.tv_delete.getText().equals("删除")) {
                    viewHolder.tv_mark.setVisibility(View.GONE);
                    viewHolder.tv_delete.setText("确认删除");
                } else {
                    mOnSwipeListener.onDelete(viewHolder.getAdapterPosition());
                }
            }
        });
        viewHolder.tv_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnSwipeListener.onMark(viewHolder.getAdapterPosition());
            }
        });

        viewHolder.itemView.setTag(position);
    }

    public void resetMap() {
        if (map.size() > 0)
            map.clear();

        int itemCount = getItemCount();

        for (int i = 0; i < itemCount; i++) {
            map.put(i, false);
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

    @Override
    public int getItemCount() {
        return lists.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_message, tv_detail, tv_time, tv_delete, tv_mark;
        ImageView iv_right, iv_head;
        CheckBox checkBox;
        LinearLayout ll_main;
        View view_unRead;

        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_mark = view.findViewById(R.id.tv_mark);
            tv_delete = view.findViewById(R.id.tv_delete);
            tv_message = view.findViewById(R.id.tv_message);
            tv_detail = view.findViewById(R.id.tv_detail);
            tv_time = view.findViewById(R.id.tv_time);
            iv_right = view.findViewById(R.id.iv_right);
            iv_head = view.findViewById(R.id.iv_head);
            checkBox = view.findViewById(R.id.checkbox);
            ll_main = view.findViewById(R.id.ll_main);
            view_unRead = view.findViewById(R.id.view_unRead);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public interface OnCheckListener {
        void onCheckChanged();
    }

    /**
     * 和Activity通信的接口
     */
    public interface onSwipeListener {
        void onDelete(int pos);

        void onMark(int pos);
    }
}