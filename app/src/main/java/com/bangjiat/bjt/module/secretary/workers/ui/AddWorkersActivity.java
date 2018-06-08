package com.bangjiat.bjt.module.secretary.workers.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.company.ui.CompanyInfoActivity;
import com.bangjiat.bjt.module.home.scan.beans.QrCodeDataCompany;
import com.bangjiat.bjt.module.home.scan.beans.QrCodeDataUser;
import com.bangjiat.bjt.module.home.scan.ui.CompanyCodeActivity;
import com.bangjiat.bjt.module.home.scan.ui.ScanActivity;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.secretary.contact.beans.ScanUser;
import com.bangjiat.bjt.module.secretary.contact.beans.SearchContactResult;
import com.bangjiat.bjt.module.secretary.contact.contract.SearchContactContract;
import com.bangjiat.bjt.module.secretary.contact.presenter.SearchContactPresenter;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.presenter.CompanyUserPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.QrManager;

/**
 * 新增员工
 */
public class AddWorkersActivity extends BaseToolBarActivity implements CompanyUserContract.View, SearchContactContract.View {
    @BindView(R.id.et_name)
    ClearEditText et_name;
    @BindView(R.id.et_phone)
    ClearEditText et_phone;
    @BindView(R.id.et_department)
    ClearEditText et_department;
    @BindView(R.id.et_duty)
    ClearEditText et_duty;
    @BindView(R.id.et_card)
    ClearEditText et_card;

    private Dialog dialog;
    private CompanyUserContract.Presenter presenter;
    private SearchContactContract.Presenter searchPresenter;
    private WorkersResult.RecordsBean bean;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        presenter = new CompanyUserPresenter(this);
        searchPresenter = new SearchContactPresenter(this);
        bean = new WorkersResult.RecordsBean();

        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                bean.setRealname(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_department.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                bean.setDepartment(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_duty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                bean.setJob(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                bean.setPhone(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_card.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                bean.setIdNumber(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_workers;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        TextView tv_done = toolbar.findViewById(R.id.toolbar_other);

        tv_title.setText("新增员工");
        tv_done.setText("完成");

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.d(bean.toString());
                presenter.addCompanyUser(DataUtil.getToken(mContext), bean);
            }
        });

        toolbar.setNavigationIcon(R.mipmap.back_white);
    }


    @OnClick(R.id.tv_invite)
    public void clickInvite(View view) {
        startActivity(new Intent(mContext, CompanyCodeActivity.class));
    }

    @OnClick(R.id.tv_add)
    public void clickAddUser(View view) {
        startScan();
    }

    private void startScan() {
        QrConfig options = new QrConfig.Builder().create();
        QrManager.getInstance().init(options).startScan(new QrManager.OnScanResultCallback() {
            @Override
            public void onScanSuccess(String result) {
                try {
                    type = new JSONObject(result).getInt("type");
                    if (type == 2) {
                        QrCodeDataUser user = new Gson().fromJson(result, QrCodeDataUser.class);
                        if (user != null) {
                            searchPresenter.getContactByScan(DataUtil.getToken(mContext), user.getUn());
                        } else {
                            fail("");
                        }
                    } else if (type == 1) {
                        QrCodeDataCompany company = new Gson().fromJson(result, QrCodeDataCompany.class);
                        if (company != null) {
                            Intent intent = new Intent(mContext, CompanyInfoActivity.class);
                            intent.putExtra("data", result);
                            startActivity(intent);
                        } else {
                            fail("");
                        }
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    fail("");
                } catch (JSONException e) {
                    fail("");
                    e.printStackTrace();
                }
            }
        });

        Intent intent = new Intent(mContext, ScanActivity.class);
        intent.putExtra(QrConfig.EXTRA_THIS_CONFIG, options);
        intent.putExtra(ScanActivity.SCAN_TYPE, 3);
        startActivity(intent);
    }

    @Override
    public void showDialog() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        } else {
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
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCompanyUserSuccess(WorkersResult result) {

    }

    @Override
    public void deleteCompanyUserSuccess() {

    }

    @Override
    public void updateCompanyUserSuccess() {

    }

    @Override
    public void addCompanyUserSuccess() {
        error("添加成功");
        EventBus.getDefault().post("");
        finish();
    }

    @Override
    public void fail(String err) {
        Toast.makeText(mContext, "二维码解析失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(SearchContactResult bean) {

    }

    @Override
    public void getContactByScanSuccess(ScanUser user) {
        if (user != null) {
            Logger.d(user.toString());
            String username = user.getUsername();
            String realname = user.getRealname();
            String idNumber = user.getIdNumber();

            et_name.setText(realname);
            et_phone.setText(username);
            et_card.setText(idNumber);
        }
    }
}
