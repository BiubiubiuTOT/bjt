package com.bangjiat.bjt.module.home.visitor.ui;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.visitor.adapter.VisitorAdapter;
import com.bangjiat.bjt.module.home.visitor.contract.VisitorContract;
import com.bangjiat.bjt.module.home.visitor.presenter.VisitorPresenter;
import com.dou361.dialogui.DialogUIUtils;

import butterknife.BindView;


public class VisitorFragment extends BaseFragment implements VisitorContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    private Dialog dialog;
    private VisitorContract.Presenter presenter;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_visitor;
    }

    @Override
    protected void initView() {
        presenter = new VisitorPresenter(this);
        presenter.getVisitorHistory(DataUtil.getToken(mContext), 1, 10);

    }

    private void setAdapter() {
        recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_view.setHasFixedSize(true);
        VisitorAdapter mAdapter = new VisitorAdapter(null);
        recycler_view.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new VisitorAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
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
    public void success() {

    }
}

