package com.bangjiat.bangjiaapp.module.secretary.workers.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseToolBarActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateWorkerActivity extends BaseToolBarActivity {
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_id_card)
    EditText et_card;
    @BindView(R.id.et_duty)
    EditText et_duty;
    @BindView(R.id.et_department)
    EditText et_department;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        et_name.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Drawable drawable = et_name.getCompoundDrawables()[2];
                if (drawable == null)
                    return false;
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > et_name.getWidth()
                        - et_name.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    et_name.setText("");
                }
                return false;
            }
        });

        et_card.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = et_card.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > et_card.getWidth()
                        - et_card.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    et_card.setText("");
                }
                return false;
            }
        });

        et_department.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = et_department.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > et_department.getWidth()
                        - et_department.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    et_department.setText("");
                }
                return false;
            }
        });
        et_duty.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = et_duty.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > et_duty.getWidth()
                        - et_duty.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    et_duty.setText("");
                }
                return false;
            }
        });

        et_phone.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = et_phone.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > et_phone.getWidth()
                        - et_phone.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    et_phone.setText("");
                }
                return false;
            }
        });

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_update_worker;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_done = toolbar.findViewById(R.id.toolbar_other);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_done.setText("保存");
        tv_title.setText("编辑资料");
        tv_done.setTextColor(getResources().getColor(R.color.black));
        tv_title.setTextColor(getResources().getColor(R.color.black));

        toolbar.setNavigationIcon(R.mipmap.back_black);
    }

    @OnClick(R.id.tv_delete)
    public void clickDelete(View view) {
        showDeleteDialog();
    }

    private void showDeleteDialog() {
        new AlertDialog(mContext).builder().setMsg("确认删除吗?").
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }
}
