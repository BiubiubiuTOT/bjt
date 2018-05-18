package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.home.work.kaoqin.adapter.DakaDetailAdapter;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaHistoryResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaTotalResult;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.ClockContract;
import com.bangjiat.bjt.module.home.work.kaoqin.presenter.ClockPresenter;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfoBean;
import com.bangjiat.bjt.module.me.personaldata.contract.GetUserInfoContract;
import com.bangjiat.bjt.module.me.personaldata.presenter.GetUserInfoPresenter;
import com.dinuscxj.progressbar.CircleProgressBar;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;


public class CountDayFragment extends BaseFragment implements GetUserInfoContract.View, ClockContract.View {
    @BindView(R.id.line_progress)
    CircleProgressBar circleProgressBar;
    @BindView(R.id.tv_count)
    TextView tv_count;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private GetUserInfoContract.Presenter presenter;
    private ClockContract.Presenter clockPresenter;
    private int workersCount;
    private int dakaCount;
    private DakaDetailAdapter mAdapter;
    private Dialog dialog;


    @Override
    protected void initView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        presenter = new GetUserInfoPresenter(this);
        clockPresenter = new ClockPresenter(this);
        presenter.getUserInfo(DataUtil.getToken(mContext));
        clockPresenter.getAllClockList(DataUtil.getToken(mContext), TimeUtils.getBeginOfDay(), System.currentTimeMillis());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_count_day;
    }

    @Override
    public void getUserInfoFail(String err) {
        Logger.e(err);
        Toast.makeText(mContext, err, Toast.LENGTH_LONG).show();
    }


    @Override
    public void getUserInfoSuccess(UserInfoBean bean) {
        if (bean != null) {
            workersCount = bean.getCount();
            if (workersCount != 0)
                setNumber();
        }
    }

    private void setNumber() {
        circleProgressBar.setMax(workersCount);
        circleProgressBar.setProgress(dakaCount);
        tv_count.setText(getString(R.string.count_day_progress_message, dakaCount, workersCount));
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
    public void getAllClockListSuccess(List<DakaHistoryResult> results) {
        if (results != null && results.size() > 0) {
            dakaCount = results.size();
            if (dakaCount != 0)
                setNumber();
            setAdapter(results);
        }
    }

    private void setAdapter(List<DakaHistoryResult> results) {
        mAdapter = new DakaDetailAdapter(results, mContext);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getClockListSuccess(List<DakaHistoryResult> results) {

    }

    @Override
    public void getUserClockListSuccess(List<DakaHistoryResult> results) {

    }

    @Override
    public void getClockTotalSuccess(DakaTotalResult result) {

    }

    @Override
    public void getUserClockTotalSuccess(DakaTotalResult result) {

    }
}

