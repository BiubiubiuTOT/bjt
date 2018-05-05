package com.bangjiat.bjt.module.me.feedback.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.me.feedback.beans.FeedBackInput;
import com.bangjiat.bjt.module.me.feedback.contract.FeedBackContract;
import com.bangjiat.bjt.module.me.feedback.presenter.FeedBackPresenter;
import com.dou361.dialogui.DialogUIUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class FeedBackActivity extends BaseColorToolBarActivity implements FeedBackContract.View {
    private FeedBackContract.Presenter presenter;

    @BindView(R.id.et_content)
    EditText et_content;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FeedBackPresenter(this);
    }


    @OnClick(R.id.btn_submit)
    public void clickSubmit(View view) {
        FeedBackInput input = new FeedBackInput();
        input.setContent(et_content.getText().toString());
        presenter.saveFeedBack(DataUtil.getToken(mContext), input);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected String getTitleStr() {
        return "意见反馈";
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
    public void showError(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success() {
        Toast.makeText(mContext, "提交反馈成功！", Toast.LENGTH_SHORT).show();
        finish();
    }
}
