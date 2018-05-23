package com.bangjiat.bjt.module.secretary.contact.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.secretary.contact.beans.ContactBean;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

/**
 * 联系人Adapter
 * Created by yunzhao.liu on 2017/11/11
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private Context context;
    private List<ContactBean> mContactList;
    private int type;
    private final HashMap<Integer, Boolean> map;
    private OnCheckListener onCheckListener;

    public ContactAdapter(Context context, List<ContactBean> list, int type) {
        this.context = context;
        this.type = type;
        this.mContactList = list;

        map = new HashMap<>();
        resetMap(list.size());
    }

    private void resetMap(int size) {
        for (int i = 0; i < size; i++) {
            map.put(i, false);
        }
    }

    public HashMap<Integer, Boolean> getMap() {
        return map;
    }

    public List<ContactBean> getmContactList() {
        return mContactList;
    }

    public void setOnCheckChangedListener(OnCheckListener listener) {
        onCheckListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contacts_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.checkbox.setVisibility(type == 1 ? View.VISIBLE : View.GONE);

        ContactBean contactInfo = mContactList.get(position);
        holder.name.setText(contactInfo.getSlaveNickname());
        holder.phone.setText(contactInfo.getSlaveUsername());
        holder.checkbox.setChecked(map.get(position));

        holder.checkbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                map.put(holder.getAdapterPosition(), !map.get(holder.getAdapterPosition()));
                notifyDataSetChanged();

                onCheckListener.onCheckChanged();
            }
        });
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                mOnItemClickListener.onItemClick(view, adapterPosition);
                if (type == 1) {
                    boolean checked = !map.get(adapterPosition);
                    holder.checkbox.setChecked(checked);

                    map.put(adapterPosition, checked);
                    notifyDataSetChanged();

                    onCheckListener.onCheckChanged();
                }
            }
        });

        //判断是否显示索引字母
        String currentLetter = contactInfo.getLetter();
        String previousLetter = position >= 1 ? mContactList.get(position - 1).getLetter() : "";
        if (!TextUtils.equals(currentLetter, previousLetter)) {
            holder.letter.setVisibility(View.VISIBLE);
            holder.letter.setText(currentLetter);
        } else {
            holder.letter.setVisibility(View.GONE);
        }


        //加载联系人头像
        Glide.with(context)
                .load(contactInfo.getAvatar())
                .transform(new GlideCircleTransform(context))
                .placeholder(R.mipmap.my_head)
                .into(holder.iv);

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mContactList == null ? 0 : mContactList.size();
    }

    public void setContactList(List<ContactBean> contactList) {
        mContactList = contactList;
        resetMap(contactList.size());
        notifyDataSetChanged();
    }

    /**
     * 刷新数据
     */
    public void notifyRefreshData() {
        notifyDataSetChanged();
    }

    public void setCheck(int position) {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout itemLayout;
        private TextView letter;
        private ImageView iv;
        private TextView name;
        private TextView phone;
        private CheckBox checkbox;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemLayout = itemView.findViewById(R.id.item_layout);
            letter = itemView.findViewById(R.id.letter);
            iv = itemView.findViewById(R.id.iv);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            checkbox = itemView.findViewById(R.id.checkbox);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnCheckListener {
        void onCheckChanged();
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
