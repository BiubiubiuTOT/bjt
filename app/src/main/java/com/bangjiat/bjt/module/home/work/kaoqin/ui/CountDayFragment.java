package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
    private AlertDialog alertDialog;


    @Override
    protected void initView() {
        initDia();
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
        mAdapter.setOnItemClickListener(new DakaDetailAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onBtnClick(View view, int position) {
                alertDialog.show();
                //设置大小
                WindowManager.LayoutParams layoutParams = alertDialog.getWindow().getAttributes();
                WindowManager m = getActivity().getWindowManager();
                Display d = m.getDefaultDisplay();
                layoutParams.width = (int) (d.getWidth() * 0.8);
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                alertDialog.getWindow().setAttributes(layoutParams);
            }
        });
    }

    private void initDia() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.leave_layout, null);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        final EditText et_msg = view.findViewById(R.id.et_msg);
        TextView txt_title = view.findViewById(R.id.txt_title);
        txt_title.setText("修改备注");
        btn_cancel.setText("取消");


        builder.setCancelable(false)
                .setView(view);
        alertDialog = builder.create();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertDialog.isShowing())
                    alertDialog.dismiss();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                error("修改成功");
                if (alertDialog.isShowing())
                    alertDialog.dismiss();
            }
        });
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

