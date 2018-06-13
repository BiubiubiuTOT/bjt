package com.bangjiat.bjt.module.park.apply.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.ReplaceViewHelper;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.park.apply.adapter.LotAdapter;
import com.bangjiat.bjt.module.park.apply.beans.LotResult;
import com.bangjiat.bjt.module.park.apply.beans.ParkApplyHistoryResult;
import com.bangjiat.bjt.module.park.apply.beans.ParkingResult;
import com.bangjiat.bjt.module.park.apply.contract.ParkApplyContract;
import com.bangjiat.bjt.module.park.apply.presenter.ParkApplyPresenter;
import com.bangjiat.bjt.module.park.car.beans.CarBean;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;

/**
 * 选择车位
 */
public class ChooseLotActivity extends BaseWhiteToolBarActivity implements ParkApplyContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private Dialog dialog;
    private ParkApplyContract.Presenter presenter;
    private List<LotResult> list;
    private ReplaceViewHelper mReplaceViewHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        mReplaceViewHelper = new ReplaceViewHelper(this);
        presenter = new ParkApplyPresenter(this);
        int spaceId = getIntent().getIntExtra("data", 0);
        if (spaceId != 0)
            presenter.getLotList(DataUtil.getToken(mContext), spaceId);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);

    }

    private void setAdapter() {
        LotAdapter mAdapter = new LotAdapter(list, mContext);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new LotAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("data", list.get(position));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_choose_lot;
    }

    @Override
    protected String getTitleStr() {
        return "选择车位";
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
        Logger.e(err);
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void getWorkersCarSuccess(List<CarBean> list) {

    }

    @Override
    public void getParkSpaceSuccess(ParkingResult s) {

    }

    @Override
    public void parkApplySuccess() {

    }

    @Override
    public void dealParkApplySuccess() {

    }

    @Override
    public void getParkApplyHistorySuccess(ParkApplyHistoryResult result) {

    }

    @Override
    public void getLotListSuccess(List<LotResult> results) {
        if (results != null && results.size() > 0) {
            list = results;
            setAdapter();
            mReplaceViewHelper.removeView();
            return;
        }
        mReplaceViewHelper.toReplaceView(recyclerView, R.layout.no_data_page);
    }
}
