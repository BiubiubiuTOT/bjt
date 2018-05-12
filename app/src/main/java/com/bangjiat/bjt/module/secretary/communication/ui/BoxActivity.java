package com.bangjiat.bjt.module.secretary.communication.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.secretary.communication.adpter.OutBoxAdapter;
import com.bangjiat.bjt.module.secretary.communication.beans.EmailBean;
import com.bangjiat.bjt.module.secretary.communication.beans.EmailResult;
import com.bangjiat.bjt.module.secretary.communication.contract.BoxContract;
import com.bangjiat.bjt.module.secretary.communication.contract.DealBoxContract;
import com.bangjiat.bjt.module.secretary.communication.presenter.BoxPresenter;
import com.bangjiat.bjt.module.secretary.communication.presenter.DealBoxPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 发件箱
 */
public class BoxActivity extends BaseToolBarActivity implements BoxContract.View, DealBoxContract.View {
    public static final int WRITE_EMAIL = 2;
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
    @BindView(R.id.et_search)
    EditText et_search;

    private TextView tv_select;
    private TextView tv_done;
    private ImageView image;
    private OutBoxAdapter mAdapter;
    private Toolbar toolbar;
    private List<EmailBean> boxBeans;
    private Dialog dialog;
    private BoxContract.Presenter presenter;
    private DealBoxContract.Presenter dealPresenter;
    private String token;
    private int type;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        presenter = new BoxPresenter(this);
        dealPresenter = new DealBoxPresenter(this);
        token = DataUtil.getToken(mContext);

        type = getIntent().getIntExtra("type", 1);
        if (type == 1) {
            tv_title.setText("已发送");
            presenter.getOutBoxList(token, "", 1, 10);
        } else {
            tv_title.setText("收件箱");
            presenter.getInBoxList(token, "", 1, 10);
        }

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                presenter.getOutBoxList(token, et_search.getText().toString(), 1, 10);
                return true;
            }
        });
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        mAdapter = new OutBoxAdapter(boxBeans, mContext, type);

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
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, BoxDetailActivity.class);
                Bundle extras = new Bundle();
                EmailBean value = boxBeans.get(position);
                extras.putSerializable("data", value);
                extras.putInt("type", type);
                intent.putExtras(extras);
                startActivity(intent);

                if (type == 0) {
                    value.setEmailStatus(1);
                    mAdapter.notifyDataSetChanged();
                }
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
        if (getSelectCount() > 0) {
            String[] strings = getString();
            Logger.d(strings);
            dealPresenter.deleteInBox(token, strings);
        }
    }

    @OnClick(R.id.tv_mark)
    public void clickMark(View view) {
        if (getSelectCount() > 0) {
            String[] strings = getString();
            Logger.d(strings);
            dealPresenter.markBox(token, strings);
        }
    }

    private String[] getString() {
        int size = getSelectCount();
        int n = 0;
        String[] strings = new String[size];
        HashMap<Integer, Boolean> map = mAdapter.getMap();
        Logger.d("map size: " + map.size() + " list size:" + boxBeans.size());
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            Boolean value = entry.getValue();
            int key = entry.getKey();
            if (value) {
                strings[n] = String.valueOf(boxBeans.get(key).getEmailId());
                n++;
            }
        }
        return strings;
    }

    private void delete() {
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
        tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_select = toolbar.findViewById(R.id.toolbar_cancel);
        tv_done = toolbar.findViewById(R.id.toolbar_other);

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
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(mContext, WriteEmailActivity.class), WRITE_EMAIL);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == WRITE_EMAIL) {
                if (type == 1) {
                    presenter.getOutBoxList(token, "", 1, 10);
                } else {
                    presenter.getInBoxList(token, "", 1, 10);
                }
            }
        }
    }

    @Override
    public void showDialog() {
        if (dialog != null) {
            if (!dialog.isShowing())
                dialog.show();
        } else
            dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void success(EmailResult list) {
        if (list != null) {
            List<EmailBean> records = list.getRecords();
            if (records != null && records.size() > 0) {
                boxBeans = records;
                Logger.d(boxBeans.toString());
                setAdapter();
            }
        }
    }

    @Override
    public void fail(String err) {
        Logger.e(err);
    }

    @Override
    public void deleteOutBoxSuccess() {
        delete();
    }

    @Override
    public void deleteInBoxSuccess() {

    }

    @Override
    public void markBoxSuccess() {
        showCustom();
    }

    @Override
    public void getUnReadCountsSuccess(String s) {

    }
}
