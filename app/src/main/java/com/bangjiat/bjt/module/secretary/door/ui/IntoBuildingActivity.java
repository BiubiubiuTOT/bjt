package com.bangjiat.bjt.module.secretary.door.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.secretary.door.beans.IntoBuildingInput;
import com.bangjiat.bjt.module.secretary.door.beans.IsIntoBuildingResult;
import com.bangjiat.bjt.module.secretary.door.contract.IntoBuildingContract;
import com.bangjiat.bjt.module.secretary.door.presenter.IntoBuildingPresenter;
import com.dou361.dialogui.DialogUIUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 申请入驻楼宇
 * 385F619CDA
 */
public class IntoBuildingActivity extends BaseColorToolBarActivity implements IntoBuildingContract.View {
    private IntoBuildingContract.Presenter presenter;
    private Dialog dialog;

    @BindView(R.id.et_code)
    ClearEditText et_code;

    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        presenter = new IntoBuildingPresenter(this);

        code = getIntent().getStringExtra("data");
        if (code != null)
            et_code.setText(code);
    }

    @OnClick(R.id.btn_submit)
    public void clickSubmit(View view) {
        IntoBuildingInput input = new IntoBuildingInput(et_code.getText().toString());
        presenter.intoBuilding(DataUtil.getToken(mContext), input);
    }

    @Override
    public void showDialog() {
        dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中。。。").show();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void success() {
        new AlertDialog(mContext).builder().setMsg("提交成功，请等待审核！").setCancelable(false).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                }).show();
    }

    @Override
    public void fail(String err) {
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void getIsIntoBuildingSuccess(IsIntoBuildingResult result) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_into_buliding;
    }

    @Override
    protected String getTitleStr() {
        return "申请入驻楼宇";
    }

}
