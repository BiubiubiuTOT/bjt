package com.bangjiat.bjt.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.home.notice.beans.NoticeBean;
import com.gongwen.marqueen.MarqueeFactory;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/6/14 0014
 */

//MarqueeFactory<T extends View, E>
//泛型T:指定ItemView的类型
//泛型E:指定ItemView填充的数据类型
public class ComplexViewMF extends MarqueeFactory<LinearLayout, NoticeBean.SysNoticeListBean> {
    private LayoutInflater inflater;

    public ComplexViewMF(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public LinearLayout generateMarqueeItemView(NoticeBean.SysNoticeListBean data) {
        LinearLayout mView = (LinearLayout) inflater.inflate(R.layout.item_notice_1, null);
        ((TextView) mView.findViewById(R.id.tv_title)).setText(data.getName());
        ((TextView) mView.findViewById(R.id.tv_content)).setText(data.getContent());
        return mView;
    }
}
