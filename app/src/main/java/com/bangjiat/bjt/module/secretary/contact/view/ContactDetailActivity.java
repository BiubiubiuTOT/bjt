package com.bangjiat.bjt.module.secretary.contact.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.WCBMenu;
import com.bangjiat.bjt.common.WcbBean;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.secretary.communication.ui.WriteEmailActivity;
import com.bangjiat.bjt.module.secretary.contact.beans.ContactBean;
import com.bangjiat.bjt.module.secretary.contact.contract.UpdateContactContract;
import com.bangjiat.bjt.module.secretary.contact.presenter.UpdateContactPresenter;
import com.dou361.dialogui.DialogUIUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ContactDetailActivity extends BaseToolBarActivity implements UpdateContactContract.View {
    private Toolbar toolbar;
    private TextView tv_edit;
    private TextView toolbar_cancel;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.ll_remark)
    LinearLayout ll_remark;
    @BindView(R.id.ll_phone)
    LinearLayout ll_phone;
    @BindView(R.id.et_remark)
    EditText et_remark;
    @BindView(R.id.view_phone)
    View view_phone;
    @BindView(R.id.view_remark)
    View view_remark;
    @BindView(R.id.iv_clear)
    ImageView iv_clear;
    @BindView(R.id.tv_send)
    TextView tv_send;
    @BindView(R.id.tv_delete)
    TextView tv_delete;
    private List<WcbBean> mList;
    private ContactBean bean;
    private Dialog dialog;
    private UpdateContactContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {

        presenter = new UpdateContactPresenter(this);
        mList = new ArrayList<>();
        mList.add(new WcbBean("删除联系人", getResources().getColor(R.color.red)));

        bean = (ContactBean) getIntent().getSerializableExtra("data");
        if (bean != null) {
            tv_phone.setText(bean.getSlaveUsername());
            tv_name.setText(bean.getSlaveNickname());
            et_remark.setText(bean.getRemark());
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_contact_detail;
    }

    @OnClick(R.id.tv_delete)
    public void clickDelete(View view) {
        showDeleteDialog();
    }

    @OnClick(R.id.iv_clear)
    public void clickClear(View view) {
        et_remark.setText("");
    }

    private void showDeleteDialog() {
        final WCBMenu wcbMenu = new WCBMenu(mContext);
        wcbMenu.setTitle("联系人删除后不可恢复？")
                .setCancel("取消")
                .setStringList(mList)
                .setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        wcbMenu.dismiss();
                        presenter.deleteContact(DataUtil.getToken(mContext), bean.getAddressListId());
                    }
                })
                .show();
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        toolbar.setTitle("");
        tv_edit = toolbar.findViewById(R.id.toolbar_other);
        tv_edit.setVisibility(View.VISIBLE);
        toolbar_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        toolbar_cancel.setTextColor(getResources().getColor(R.color.mine_bg));
        toolbar_cancel.setText("取消");
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));

        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_edit.getText().equals("编辑")) {
                    showEdit();
                } else {
                    bean.setRemark(et_remark.getText().toString());
                    presenter.updateContact(DataUtil.getToken(mContext), bean);
                }
            }
        });
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustom();
            }
        });

        showCustom();
    }

    private void showCustom() {
        tv_edit.setText("编辑");
        toolbar_cancel.setVisibility(View.GONE);
        tv_edit.setTextColor(getResources().getColor(R.color.mine_bg));
        toolbar.setNavigationIcon(R.mipmap.back_black);

        view_phone.setVisibility(View.GONE);
        view_remark.setVisibility(View.VISIBLE);
        iv_clear.setVisibility(View.GONE);
        tv_name.setTextColor(getResources().getColor(R.color.dialog_title));
        et_remark.setTextColor(getResources().getColor(R.color.dialog_title));
        tv_phone.setTextColor(getResources().getColor(R.color.dialog_title));
        et_remark.setEnabled(false);

        tv_send.setVisibility(View.VISIBLE);
        tv_delete.setVisibility(View.GONE);

        ll_phone.setPadding(0, 0, 0, 0);
        ll_remark.setPadding(0, 0, 0, 0);
    }

    private void showEdit() {
        tv_edit.setText("保存");
        toolbar.setNavigationIcon(null);
        toolbar_cancel.setVisibility(View.VISIBLE);

        view_phone.setVisibility(View.VISIBLE);
        view_remark.setVisibility(View.GONE);
        iv_clear.setVisibility(View.VISIBLE);
        tv_name.setTextColor(getResources().getColor(R.color.personal_message));
        tv_phone.setTextColor(getResources().getColor(R.color.personal_message));
        et_remark.setEnabled(true);

        tv_send.setVisibility(View.GONE);
        tv_delete.setVisibility(View.VISIBLE);

        ll_phone.setPadding(100, 0, 0, 0);
        ll_remark.setPadding(100, 0, 0, 0);
    }

    @Override
    public void showDialog() {
        if (dialog == null)
            dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
        else dialog.show();
    }

    @OnClick(R.id.tv_send)
    public void clickSend(View view) {
        Intent intent = new Intent(mContext, WriteEmailActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErr(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteSuccess() {
        EventBus.getDefault().post("");
        Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void updateSuccess() {
        EventBus.getDefault().post("");
        Toast.makeText(mContext, "修改成功", Toast.LENGTH_SHORT).show();
        showCustom();
        finish();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }
}
