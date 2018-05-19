package com.bangjiat.bjt.module.secretary.service.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.FullImageActivity;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.BuildUser;
import com.bangjiat.bjt.module.secretary.door.beans.ApprovalServiceInput;
import com.bangjiat.bjt.module.secretary.service.adapter.ImageAdapter;
import com.bangjiat.bjt.module.secretary.service.beans.ServiceApplyHistoryResult;
import com.bangjiat.bjt.module.secretary.service.contract.ServiceApplyHistoryContract;
import com.bangjiat.bjt.module.secretary.service.presenter.ServiceApplyHistoryPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 服务申请 详情
 */
public class DetailActivity extends BaseColorToolBarActivity implements ServiceApplyHistoryContract.View {
    @BindView(R.id.recycler_view_photo)
    RecyclerView recycler_view_photo;
    @BindView(R.id.tv_company_name)
    TextView tv_company_name;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.rl_btn)
    RelativeLayout rl_btn;

    private List<String> photoList;
    private ServiceApplyHistoryResult.RecordsBean data;
    private int type;
    private AlertDialog alertDialog;
    private ServiceApplyHistoryContract.Presenter presenter;
    private Dialog dialog;
    private BuildUser first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    /**
     * 关于竖线的问题用 string.split("\\|")解决。 关于星号的问题用 string.split("\\*")解决。
     * 关于斜线的问题用 sring.split("\\\\")解决。 关于中括号的问题用 sring.split("\\[\\]")解决。
     */
    private void initData() {
        first = BuildUser.first(BuildUser.class);
        presenter = new ServiceApplyHistoryPresenter(this);
        data = (ServiceApplyHistoryResult.RecordsBean) getIntent().getSerializableExtra("data");
        if (data != null) {
            tv_company_name.setText(data.getCompanyName());
            tv_content.setText(data.getContent());
            tv_title.setText(data.getApplication());

            String sources = data.getSources();
            if (sources != null) {
                Logger.d(sources);
                String[] split = sources.split("\\|");
                Logger.d(split[0]);
                photoList = Arrays.asList(split);
            } else photoList = new ArrayList<>();

            Logger.d(photoList.toString());
            setPhotoAdapter();
        }
        if (Constants.isBuildingAdmin()) {
            rl_btn.setVisibility(View.VISIBLE);
            initDia();
        }
    }

    @OnClick(R.id.btn_agree)
    public void clickAgree(View view) {
        type = 2;
        alertDialog.show();
    }

    @OnClick(R.id.btn_refuse)
    public void clickRefuse(View view) {
        type = 1;
        alertDialog.show();
    }

    private void initDia() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.refluse_message_layout, null);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        final EditText et_msg = view.findViewById(R.id.et_msg);


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
                String string = et_msg.getText().toString();
                if (string.isEmpty()) {
                    error("请填写审批信息");
                    return;
                }
                if (alertDialog.isShowing())
                    alertDialog.dismiss();

                ApprovalServiceInput input = new ApprovalServiceInput(data.getBApprovalId(), string, type);
                presenter.approvalService(DataUtil.getToken(mContext), first.getBuildId(), input);
            }
        });
    }

    private void setPhotoAdapter() {
        recycler_view_photo.setLayoutManager(new GridLayoutManager(mContext, 4));
        recycler_view_photo.setHasFixedSize(true);
        ImageAdapter adapter = new ImageAdapter(photoList, mContext);
        recycler_view_photo.setAdapter(adapter);

        adapter.setOnItemClickListener(new ImageAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, FullImageActivity.class);
                intent.putExtra("path", photoList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_datail;
    }

    @Override
    protected String getTitleStr() {
        return "申请详情";
    }

    @Override
    public void error(String err) {
        Logger.e(err);
        Constants.showErrorDialog(mContext, err);
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
    public void success(ServiceApplyHistoryResult result) {
        new com.adorkable.iosdialog.AlertDialog(mContext).builder().setMsg("审批成功").setCancelable(false).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }).show();
    }


    @Override
    public void approvalServiceSuccess() {

    }

    @Override
    public void getAdminHistorySuccess(ServiceApplyHistoryResult result) {

    }
}
