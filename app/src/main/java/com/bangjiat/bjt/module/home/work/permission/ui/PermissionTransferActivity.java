package com.bangjiat.bjt.module.home.work.permission.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.work.permission.adapter.WorkersAdapter;
import com.bangjiat.bjt.module.home.work.permission.contract.PermissionContract;
import com.bangjiat.bjt.module.home.work.permission.presenter.PermissionPresenter;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.BuildUser;
import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;
import com.bangjiat.bjt.module.me.personaldata.beans.SpaceUser;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfoBean;
import com.bangjiat.bjt.module.me.personaldata.contract.GetUserInfoContract;
import com.bangjiat.bjt.module.me.personaldata.presenter.GetUserInfoPresenter;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.presenter.CompanyUserPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PermissionTransferActivity extends BaseWhiteToolBarActivity implements
        CompanyUserContract.View, PermissionContract.View, GetUserInfoContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<WorkersResult.RecordsBean> beans;
    private WorkersAdapter adapter;
    private CompanyUserContract.Presenter presenter;
    private PermissionContract.Presenter permissionPresenter;
    private String token;
    private Dialog dialog;
    private GetUserInfoContract.Presenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        beans = new ArrayList<>();
        userPresenter = new GetUserInfoPresenter(this);
        presenter = new CompanyUserPresenter(this);
        permissionPresenter = new PermissionPresenter(this);
        token = DataUtil.getToken(mContext);
        presenter.getCompanyUser(token, 1, 10, 1);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
    }

    private void setAdapter() {
        adapter = new WorkersAdapter(beans, mContext);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new WorkersAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showDialog(beans.get(position));
            }
        });
    }

    private void showDialog(final WorkersResult.RecordsBean bean) {
        new AlertDialog(mContext).builder().setMsg("确认进行权限转交吗?").
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        permissionPresenter.updateAdmin(token, bean.getUserId());
                    }
                }).
                setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
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
        Constants.showErrorDialog(mContext, err);
        Logger.e(err);
    }

    @Override
    public void deleteAdminSuccess() {

    }

    @Override
    public void addAdminSuccess() {

    }

    @Override
    public void updateAdminSuccess() {
        Constants.deleteDb();
        userPresenter.getUserInfo(token);
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
    }

    @Override
    public void updateCompanyUserSuccess() {

    }

    @Override
    public void addCompanyUserSuccess() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_permission_transfer;
    }

    @Override
    protected String getTitleStr() {
        return "权限转交";
    }

    @Override
    public void getUserInfoFail(String err) {
        error(err);
    }

    @Override
    public void getUserInfoSuccess(UserInfoBean bean) {
        UserInfo userInfo = bean.getUserInfo();
        CompanyUserBean companyUser = bean.getCompanyUser();
        List<BuildUser> buildUser = bean.getBuildUser();
        List<SpaceUser> spaceUser = bean.getSpaceUser();

        DataUtil.setPhone(mContext, userInfo.getPhone());
        DataUtil.setUserId(mContext, userInfo.getUserId());

        userInfo.save();
        if (companyUser != null)
            companyUser.save();

        if (buildUser != null && buildUser.size() > 0) {
            Logger.d(buildUser.toString());
            SugarRecord.saveInTx(buildUser);
        }

        if (spaceUser != null && spaceUser.size() > 0) {
            Logger.d(spaceUser.toString());
            SugarRecord.saveInTx(spaceUser);
        }

        new AlertDialog(mContext).builder().setMsg("权限转交成功").setCancelable(false).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }).show();
    }
}
