package com.bangjiat.bjt.common;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bjt.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class DialogPopup extends BasePopupWindow {
    private ImageView iv_icon;
    private TextView tv_title;

    public DialogPopup(Activity context, String title, int resId) {
        super(context);

        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        tv_title = (TextView) findViewById(R.id.tv_title);

        tv_title.setText(title);
        iv_icon.setImageResource(resId);
    }

    @Override
    protected Animation initShowAnimation() {
        AnimationSet set = new AnimationSet(false);
        Animation shakeAnima = new RotateAnimation(0, 15, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        shakeAnima.setInterpolator(new CycleInterpolator(5));
        shakeAnima.setDuration(400);
        set.addAnimation(getDefaultAlphaAnimation());
        set.addAnimation(shakeAnima);
        return set;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popup_dialog);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }


}
