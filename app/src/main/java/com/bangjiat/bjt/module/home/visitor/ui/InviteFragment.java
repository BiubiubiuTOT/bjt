package com.bangjiat.bjt.module.home.visitor.ui;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.visitor.adapter.VisitorAdapter;
import com.bangjiat.bjt.module.home.visitor.beans.VisitorBean;
import com.bangjiat.bjt.module.home.visitor.contract.VisitorContract;
import com.bangjiat.bjt.module.home.visitor.presenter.VisitorPresenter;
import com.dou361.dialogui.DialogUIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class InviteFragment extends BaseFragment implements VisitorContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.ll_none)
    LinearLayout ll_none;
    private VisitorContract.Presenter presenter;
    private Dialog dialog;
    private List<VisitorBean.RecordsBean> list;
    private VisitorAdapter mAdapter;


    @Override
    protected void initView() {
        presenter = new VisitorPresenter(this);
        presenter.getVisitorHistory(DataUtil.getToken(mContext), 2, 10, 1);
        setAdapter();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_invite;
    }

    private void setAdapter() {
        list = new ArrayList<>();
        recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_view.setHasFixedSize(true);
        mAdapter = new VisitorAdapter(list);
        recycler_view.setAdapter(mAdapter);
    }


    @Override
    public void showDialog() {
        dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void error(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(VisitorBean bean) {
        if (bean != null) {
            List<VisitorBean.RecordsBean> records = bean.getRecords();
            if (records != null && records.size() > 0) {
                list = records;
                mAdapter.setLists(list);
            }
        }
    }

    @Override
    public void dealSuccess(String str) {
    }

    @Override
    public void addInviteSuccess(String str) {

    }

}

