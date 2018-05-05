package com.bangjiat.bjt.module.secretary.contact.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.secretary.contact.beans.ContactBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 联系人Adapter
 * Created by yunzhao.liu on 2017/11/11
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> implements View.OnClickListener {

    private Context context;
    private List<ContactBean> mContactList;

    public ContactAdapter(Context context, List<ContactBean> list) {
        this.context = context;
        this.mContactList = list;
    }

    public List<ContactBean> getmContactList() {
        return mContactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contacts_layout, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ContactBean contactInfo = mContactList.get(position);
        holder.name.setText(contactInfo.getSlaveNickname());
        holder.phone.setText(contactInfo.getSlaveUsername());

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
                .error(R.mipmap.my_head)
                .into(holder.iv);

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mContactList == null ? 0 : mContactList.size();
    }

    public void setContactList(List<ContactBean> contactList) {
        mContactList = contactList;
        notifyDataSetChanged();
    }

    /**
     * 刷新数据
     */
    public void notifyRefreshData() {
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout itemLayout;
        private TextView letter;
        private ImageView iv;
        private TextView name;
        private TextView phone;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemLayout = itemView.findViewById(R.id.item_layout);
            letter = itemView.findViewById(R.id.letter);
            iv = itemView.findViewById(R.id.iv);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
