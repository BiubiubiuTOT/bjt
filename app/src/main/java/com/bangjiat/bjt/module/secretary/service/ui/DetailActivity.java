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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.FullImageActivity;
import com.bangjiat.bjt.module.home.work.leave.beans.Progress;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.BuildUser;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.secretary.door.beans.ApprovalServiceInput;
import com.bangjiat.bjt.module.secretary.service.adapter.ImageAdapter;
import com.bangjiat.bjt.module.secretary.service.beans.BuildingAdminListResult;
import com.bangjiat.bjt.module.secretary.service.beans.DetailResult;
import com.bangjiat.bjt.module.secretary.service.beans.ServiceApplyHistoryResult;
import com.bangjiat.bjt.module.secretary.service.contract.ServiceApplyHistoryContract;
import com.bangjiat.bjt.module.secretary.service.presenter.ServiceApplyHistoryPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.bangjiat.bjt.module.secretary.service.ui.NewApplyActivity.GET_PERSON;

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
    @BindView(R.id.ll_reason)
    LinearLayout ll_reason;
    @BindView(R.id.tv_reason)
    TextView tv_reason;
    @BindView(R.id.tv_to)
    TextView tv_to;

    private List<String> photoList;
    private ServiceApplyHistoryResult.RecordsBean data;
    private AlertDialog alertDialog;
    private ServiceApplyHistoryContract.Presenter presenter;
    private Dialog dialog;
    private BuildUser first;
    private BuildingAdminListResult result;

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
        int status = data.getStatus();
        if (Constants.isBuildingAdmin()) {
            String approvalUser = data.getApprovalUser();
            UserInfo first = UserInfo.first(UserInfo.class);
            try {
                List<DetailResult> detailResults = new Gson().fromJson(approvalUser, new TypeToken<List<DetailResult>>() {
                }.getType());
                if (detailResults != null && detailResults.size() > 0) {
                    DetailResult detailResult = detailResults.get(detailResults.size() - 1);
                    if (!detailResult.getUserId().equals(first.getUserId())) {
                        if (status == 1) {
                            rl_btn.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (status == 1) {
                            tv_to.setVisibility(View.VISIBLE);
                        }
                    }
                }
                if (detailResults == null) {
                    if (status == 1) {
                        rl_btn.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (detailResults.size() == 0) {
                        if (status == 1) {
                            rl_btn.setVisibility(View.VISIBLE);
                        }
                    }
                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                if (status == 1) {
                    rl_btn.setVisibility(View.VISIBLE);
                }
            }
            initDia();
        }
        if (status == 3) {
            ll_reason.setVisibility(View.VISIBLE);
            tv_reason.setText(data.getRemark());
        }
    }

    @OnClick(R.id.btn_agree)
    public void clickAgree(View view) {
        ApprovalServiceInput input = new ApprovalServiceInput(data.getBApprovalId(), 1);
        presenter.approvalService(DataUtil.getToken(mContext), first.getBuildId(), input);
    }

    @OnClick(R.id.btn_refuse)
    public void clickRefuse(View view) {
        alertDialog.show();
    }

    @OnClick(R.id.btn_to)
    public void clickTo(View view) {
        Intent intent = new Intent(mContext, BuildingAdminListActivity.class);
        intent.putExtra("type", 1);
        startActivityForResult(intent, GET_PERSON);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data1) {
        super.onActivityResult(requestCode, resultCode, data1);
        if (resultCode == RESULT_OK) {
            if (requestCode == GET_PERSON) {
                result = (BuildingAdminListResult) data1.getSerializableExtra("data");
                Progress progress = new Progress(result.getUserId(), result.getUsername(), result.getRealname());
                ApprovalServiceInput input = new ApprovalServiceInput(data.getBApprovalId(), 3);
                input.setProgress(progress);
                presenter.approvalService(DataUtil.getToken(mContext), first.getBuildId(), input);
            }
        }
    }

    private void initDia() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.refluse_message_layout, null);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        final ClearEditText et_msg = view.findViewById(R.id.et_msg);


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

                ApprovalServiceInput input = new ApprovalServiceInput(data.getBApprovalId(), string, 2);
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

    }


    @Override
    public void approvalServiceSuccess() {
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
    public void getAdminHistorySuccess(ServiceApplyHistoryResult result) {

    }
}
