package com.bangjiat.bjt.common;

import android.app.Dialog;
import android.content.Context;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/18 0018
 */

public class BaseDialog extends Dialog {
    private int res;

    public BaseDialog(Context context, int res) {
        super(context);
        setContentView(res);
        this.res = res;
        setCanceledOnTouchOutside(false);
    }

}