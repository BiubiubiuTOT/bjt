package com.bangjiat.bangjiaapp.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import pl.droidsonroids.gif.GifImageView;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public class YuanJiaoImageView extends GifImageView {
    //圆角弧度
    private float[]  rids = {30.0f, 30.0f, 30.0f, 30.0f, 10.0f, 0.0f, 0.0f, 0.0f,};

    public YuanJiaoImageView(Context context) {
        super(context);
    }

    public YuanJiaoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public YuanJiaoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
      
        path.addRoundRect(new RectF(0, 0, w, h), rids, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
