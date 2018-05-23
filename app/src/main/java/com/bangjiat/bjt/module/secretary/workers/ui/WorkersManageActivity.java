package com.bangjiat.bjt.module.secretary.workers.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.secretary.workers.adapter.SelectPeopleAdapter;
import com.bangjiat.bjt.module.secretary.workers.adapter.TextAdapter;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.presenter.CompanyUserPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.jiang.android.indicatordialog.IndicatorBuilder;
import com.jiang.android.indicatordialog.IndicatorDialog;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 员工管理
 */
public class WorkersManageActivity extends BaseToolBarActivity implements CompanyUserContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_delete)
    TextView tv_delete;
    @BindView(R.id.card_delete)
    CardView card_delete;

    private Toolbar toolbar;

    private List<WorkersResult.RecordsBean> beans;
    private SelectPeopleAdapter adapter;
    private TextAdapter textAdapter;
    private TextView tv_all;
    private TextView tv_done;
    private Dialog dialog;
    private CompanyUserContract.Presenter presenter;
    private String token;
    private int size;
    private ImageView img;
    private List<String> texts;
    private IndicatorDialog addDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_workers_manage;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        toolbar.setTitle("");
        tv_all = toolbar.findViewById(R.id.toolbar_cancel);
        tv_done = toolbar.findViewById(R.id.toolbar_other);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        img = toolbar.findViewById(R.id.toolbar_image);
        img.setImageResource(R.mipmap.add);

        tv_done.setText("完成");
        tv_title.setText("员工管理");
        showCustom();

        if (Constants.isCompanyAdmin()) {
            img.setVisibility(View.VISIBLE);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addDialog.show(img);
                }
            });
        } else img.setVisibility(View.GONE);

        tv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.selectAll();
            }
        });
        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setDone();
                showCustom();
            }
        });
    }

    private void showCustom() {
        card_delete.setVisibility(View.GONE);
        tv_all.setText("全选");
        img.setVisibility(View.VISIBLE);
        toolbar.setNavigationIcon(R.mipmap.back_white);
        tv_delete.setVisibility(View.GONE);
        tv_done.setVisibility(View.GONE);
        tv_all.setVisibility(View.GONE);
    }

    private void initData() {
        EventBus.getDefault().register(this);
        beans = new ArrayList<>();
        presenter = new CompanyUserPresenter(this);
        token = DataUtil.getToken(mContext);
        presenter.getCompanyUser(token, 1, 10, 1);

        texts = new ArrayList<>();
        texts.add("添加人员");
        texts.add("删除人员");
        textAdapter = new TextAdapter(texts);
        textAdapter.setOnItemClickListener(new TextAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                addDialog.dismiss();
                if (position != 0) {
                    card_delete.setVisibility(View.VISIBLE);
                    adapter.setShowCheck(true);
                    showDelete();
                } else {
                    startActivity(new Intent(mContext, AddWorkersActivity.class));
                }
            }
        });

        initDialog();
    }

    private void showDelete() {
        img.setVisibility(View.GONE);
        tv_delete.setVisibility(View.VISIBLE);
        tv_all.setVisibility(View.VISIBLE);

        toolbar.setNavigationIcon(null);
        tv_done.setVisibility(View.VISIBLE);
    }

    private void initDialog() {
        addDialog = new IndicatorBuilder(this)
                .width(300)
                .height(-1)
                .ArrowDirection(IndicatorBuilder.TOP)
                .bgColor(getResources().getColor(R.color.white))
                .radius(8)
                .gravity(IndicatorBuilder.GRAVITY_RIGHT)
                .ArrowRectage(0.9f)
                .layoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
                .adapter(textAdapter).create();
        addDialog.setCanceledOnTouchOutside(true);
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        adapter = new SelectPeopleAdapter(beans, mContext);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SelectPeopleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, UpdateWorkerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", beans.get(position));
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });
        adapter.setOnCheckChangedListener(new SelectPeopleAdapter.OnCheckListener() {
            @Override
            public void onCheckChanged() {
                int total = adapter.getItemCount();
                int select = getSelectCount();
                if (select == 0) {
                    tv_delete.setTextColor(getResources().getColor(R.color.red_1));
                    tv_all.setText("全选");
                } else {
                    if (select == total) {
                        tv_all.setText("取消全选");
                    } else {
                        tv_all.setText("全选");
                    }
                    tv_delete.setTextColor(getResources().getColor(R.color.red));
                }
            }
        });
    }

    private int getSelectCount() {
        int select = 0;
        HashMap<Integer, Boolean> map = adapter.getMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue())
                select++;
        }
        return select;
    }

    private List<String> getSelectItem() {
        List<String> select = new ArrayList<>();
        UserInfo userInfo = UserInfo.first(UserInfo.class);
        HashMap<Integer, Boolean> map = adapter.getMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue()) {
                String userId = beans.get(entry.getKey()).getUserId();
                select.add(userId);
            }
        }
        return select;
    }


    @OnClick(R.id.tv_delete)
    public void clickDelete(View view) {
        if (getSelectCount() != 0) {
            if (getSelectItem().size() == 0) {
                error("删除失败,请先进行权限转交");
            } else
                showDeleteDialog();
        }
    }


    private void showDeleteDialog() {
        new AlertDialog(this).builder()
                .setMsg("确认删除吗?")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showCustom();
                        adapter.setShowCheck(false);
                        List<String> selectItem = getSelectItem();
                        size = selectItem.size();
                        Logger.d(selectItem.toString());
                        for (String s : selectItem) {
                            presenter.deleteCompanyUser(token, s);
                        }
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String s) {
        presenter.getCompanyUser(DataUtil.getToken(mContext), 1, 10, 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showDialog() {
        if (dialog != null && !dialog.isShowing())
            dialog.show();
        else {
            dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
        }
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void error(String err) {
        Constants.showErrorDialog(mContext,err);
    }

    @Override
    public void getCompanyUserSuccess(WorkersResult result) {
        if (result != null) {
            List<WorkersResult.RecordsBean> records = result.getRecords();
            if (records != null) {
                beans = records;
                setAdapter();
            }
        }
    }

    @Override
    public void deleteCompanyUserSuccess() {
        size--;
        if (size == 0) {
            error("删除成功");
            presenter.getCompanyUser(token, 1, 10, 1);
        }
    }

    @Override
    public void updateCompanyUserSuccess() {

    }

    @Override
    public void addCompanyUserSuccess() {

    }
}
