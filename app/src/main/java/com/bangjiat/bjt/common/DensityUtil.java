package com.bangjiat.bjt.common;

import android.content.Context;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/21 0021
 */

public class DensityUtil {
    public static float dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return dpValue * scale;
    }
}
