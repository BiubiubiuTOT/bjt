package com.bangjiat.bangjiaapp.module.secretary.communication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bangjiaapp.module.secretary.communication.adpter.OutBoxAdapter;
import com.bangjiat.bangjiaapp.module.secretary.communication.beans.OutBoxBean;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 发件箱
 */
public class OutBoxActivity extends BaseToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv_delete)
    TextView tv_delete;
    @BindView(R.id.tv_mark)
    TextView tv_mark;
    @BindView(R.id.tv_edit)
    TextView tv_edit;

    private TextView tv_select;
    private TextView tv_done;
    private ImageView image;
    private OutBoxAdapter mAdapter;
    private Toolbar toolbar;
    private List<OutBoxBean> boxBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        boxBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            boxBeans.add(new OutBoxBean("老陈" + i));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        mAdapter = new OutBoxAdapter(boxBeans);

        recyclerView.setAdapter(mAdapter);

        // 可以用在：当点击外部空白处时，关闭正在展开的侧滑菜单。我个人觉得意义不大，
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    SwipeMenuLayout viewCache = SwipeMenuLayout.getViewCache();
                    if (null != viewCache) {
                        viewCache.smoothClose();
                    }
                }
                return false;
            }
        });

        mAdapter.setOnCheckChangedListener(new OutBoxAdapter.OnCheckListener() {
            @Override
            public void onCheckChanged() {
                int total = mAdapter.getItemCount();
                int select = getSelectCount();
                if (select == 0) {
                    tv_delete.setTextColor(getResources().getColor(R.color.red_1));
                    tv_mark.setTextColor(getResources().getColor(R.color.mine_bg_1));
                    tv_select.setText("全选");
                } else {
                    if (select == total) {
                        tv_select.setText("取消全选");
                    } else {
                        tv_select.setText("全选");
                    }
                    tv_delete.setTextColor(getResources().getColor(R.color.red));
                    tv_mark.setTextColor(getResources().getColor(R.color.mine_bg));
                }
            }
        });

        mAdapter.setOnSwipeListener(new OutBoxAdapter.onSwipeListener() {
            @Override
            public void onDelete(int pos) {
                boxBeans.remove(pos);
                mAdapter.notifyItemRemoved(pos);
                mAdapter.notifyItemRangeChanged(pos, boxBeans.size() - pos);

                mAdapter.resetMap();
            }

            @Override
            public void onMark(int pos) {

            }
        });

        mAdapter.setOnItemClickListener(new OutBoxAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick( int position) {
                startActivity(new Intent(mContext, OutBoxDetailActivity.class));
            }
        });
    }

    private int getSelectCount() {
        int select = 0;
        HashMap<Integer, Boolean> map = mAdapter.getMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue())
                select++;
        }
        Logger.d("map: " + map.size() + " select: " + select);
        return select;
    }

    @OnClick(R.id.tv_edit)
    public void clickEdit(View view) {
        showSelect();
    }

    @OnClick(R.id.tv_delete)
    public void clickDelete(View view) {
        int i = 0;
        HashMap<Integer, Boolean> map = mAdapter.getMap();
        Logger.d("map size: " + map.size() + " list size:" + boxBeans.size());
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            Boolean value = entry.getValue();
            int key = entry.getKey();
            if (value) {
                Logger.d(key);
                boxBeans.remove(key - i);
                i++;
            }
        }

        mAdapter.setDone();
        showCustom();
        mAdapter.resetMap();
    }

    private void showSelect() {
        tv_done.setVisibility(View.VISIBLE);
        tv_select.setVisibility(View.VISIBLE);
        image.setVisibility(View.GONE);
        toolbar.setNavigationIcon(null);
        rl.setVisibility(View.VISIBLE);
        tv_edit.setVisibility(View.GONE);

        mAdapter.setShowCheck(true);
    }

    private void showCustom() {
        tv_done.setVisibility(View.GONE);
        tv_select.setVisibility(View.GONE);
        image.setVisibility(View.VISIBLE);
        toolbar.setNavigationIcon(R.mipmap.back_black);
        rl.setVisibility(View.GONE);
        tv_edit.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_out_box;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        toolbar.setNavigationIcon(R.mipmap.back_black);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        toolbar.setTitle("");
        image = toolbar.findViewById(R.id.toolbar_image);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_select = toolbar.findViewById(R.id.toolbar_cancel);
        tv_done = toolbar.findViewById(R.id.toolbar_other);

        tv_title.setText("已发送");
        tv_select.setText("全选");
        tv_done.setText("完成");
        image.setImageResource(R.mipmap.email_color);
        tv_title.setTextColor(getResources().getColor(R.color.black));

        tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.selectAll();
            }
        });
        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_select.setText("全选");
                mAdapter.setDone();
                showCustom();
            }
        });
    }
}
