package com.bangjiat.bjt.module.home.work.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.company.beans.CompanyDetailResult;
import com.bangjiat.bjt.module.home.company.beans.DeleteCompanyInput;
import com.bangjiat.bjt.module.home.company.contract.CompanyContract;
import com.bangjiat.bjt.module.home.company.contract.IntoCompanyContract;
import com.bangjiat.bjt.module.home.company.presenter.CompanyPresenter;
import com.bangjiat.bjt.module.home.company.presenter.IntoCompanyPresenter;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.RuleInput;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfoBean;
import com.bangjiat.bjt.module.me.personaldata.contract.GetUserInfoContract;
import com.bangjiat.bjt.module.me.personaldata.presenter.GetUserInfoPresenter;
import com.bangjiat.bjt.module.secretary.door.beans.IsIntoBuildingResult;
import com.bangjiat.bjt.module.secretary.door.contract.IntoBuildingContract;
import com.bangjiat.bjt.module.secretary.door.presenter.IntoBuildingPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class EditCompanyActivity extends BaseToolBarActivity implements
        CompanyContract.View, IntoCompanyContract.View, GetUserInfoContract.View, IntoBuildingContract.View {
    @BindView(R.id.tv_company_name)
    TextView tv_company;
    @BindView(R.id.et_industry)
    ClearEditText et_industry;
    @BindView(R.id.et_address)
    ClearEditText et_address;
    @BindView(R.id.ll_build)
    LinearLayout ll_build;
    @BindView(R.id.tv_build)
    TextView tv_build;

    private Dialog dialog;
    private CompanyContract.Presenter presenter;
    private IntoCompanyContract.Presenter intoPresenter;
    private IntoBuildingContract.Presenter buildPresenter;

    private String token;
    private CompanyDetailResult result;
    private CompanyUserBean first;
    private TextView tv_title;
    private TextView tv_done;
    private GetUserInfoContract.Presenter userPresenter;
    int count;
    private boolean isInto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        userPresenter = new GetUserInfoPresenter(this);
        userPresenter.getUserInfo(DataUtil.getToken(mContext));
        buildPresenter = new IntoBuildingPresenter(this);
        if (!Constants.isCompanyAdmin()) {//公司管理员才能修改公司信息
            et_address.setEnabled(false);
            et_industry.setEnabled(false);
            et_address.setFocusable(false);
            et_industry.setFocusable(false);
            tv_done.setVisibility(View.GONE);
            tv_title.setText("公司信息");
        }

        first = CompanyUserBean.first(CompanyUserBean.class);
        intoPresenter = new IntoCompanyPresenter(this);
        token = DataUtil.getToken(mContext);
        presenter = new CompanyPresenter(this);
        buildPresenter.isIntoBuilding(token);
    }

    @OnClick(R.id.tv_delete)
    public void clickDelete(View view) {
        showDia();
    }

    private void showDia() {
        new AlertDialog(mContext).builder().setMsg("确认退出公司吗?").
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Constants.isCompanyAdmin()) {//公司管理员不能退出公司
                            if (count == 1) {//剩下最后一个人
                                if (isInto)
                                    error("请联系物业管理员删除公司");
                                else
                                    presenter.deleteCompany(token, new DeleteCompanyInput(first.getCompanyId()));
                            } else {
                                error("请先转交管理员权限");
                            }
                        } else
                            presenter.exitCompany(token);
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_edit_company;
    }

    @Override
    protected void initToolbar(final Toolbar toolbar) {
        toolbar.setTitle("");
        tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_done = toolbar.findViewById(R.id.toolbar_other);

        tv_title.setText("编辑公司");
        tv_done.setText("完成");
        toolbar.setNavigationIcon(R.mipmap.back_black);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        tv_done.setTextColor(getResources().getColor(R.color.black));
        tv_title.setTextColor(getResources().getColor(R.color.black));

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setAddress(et_address.getText().toString());
                result.setIndustry(et_industry.getText().toString());

                presenter.updateCompany(token, result);
            }
        });
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
    public void success() {

    }

    @Override
    public void fail(String err) {
        Logger.e(err);
    }

    @Override
    public void getIsIntoBuildingSuccess(IsIntoBuildingResult result) {
        if (result != null) {
            int status = result.getStatus();
            if (status == 2) {
                Logger.d("isInto: " + true);
                isInto = true;
            }
        }
        if (first != null) {
            intoPresenter.getCompanyDetail(token, first.getCompanyId());
        }
    }

    @Override
    public void getCompanyDetailSuccess(CompanyDetailResult result) {
        if (result != null) {
            this.result = result;
            tv_company.setText(result.getName());
            et_industry.setText(result.getIndustry());
            et_address.setText(result.getAddress());
            String buildName = result.getBuildName();
            if (!TextUtils.isEmpty(buildName)) {
                ll_build.setVisibility(View.VISIBLE);
                tv_build.setText(buildName);
            }
        }
    }

    @Override
    public void addCompanySuccess() {

    }

    @Override
    public void addCompanyFail(String err) {

    }

    @Override
    public void updateCompanySuccess(String str) {
        Constants.showSuccessExitDialog(this, "公司信息修改成功");
    }

    @Override
    public void exitCompanySuccess(String token) {
        first.delete();
        show();
    }

    private void show() {
        new AlertDialog(this).builder().setMsg("退出公司成功").setCancelable(false).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }).show();
    }

    @Override
    public void error(String err) {
        Logger.e(err);
        Constants.showErrorDialog(this, err);
    }

    @Override
    public void deleteCompanySuccess() {
        first.delete();
        RuleInput.deleteAll(RuleInput.class);

        show();
    }

    @Override
    public void getUserInfoFail(String err) {

    }

    @Override
    public void getUserInfoSuccess(UserInfoBean bean) {
        if (bean != null)
            count = bean.getCount();
    }
}
