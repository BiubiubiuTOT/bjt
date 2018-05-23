package com.bangjiat.bjt.module.secretary.service.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.FullImageActivity;
import com.bangjiat.bjt.common.GlideImageLoader;
import com.bangjiat.bjt.common.WCBMenu;
import com.bangjiat.bjt.common.WcbBean;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;
import com.bangjiat.bjt.module.secretary.service.adapter.ImageAdapter;
import com.bangjiat.bjt.module.secretary.service.beans.BuildingAdminListResult;
import com.bangjiat.bjt.module.secretary.service.beans.NewApplyInput;
import com.bangjiat.bjt.module.secretary.service.contract.NewServiceApplyContract;
import com.bangjiat.bjt.module.secretary.service.presenter.NewServiceApplyPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新的申请 服务
 */
public class NewApplyActivity extends BaseToolBarActivity implements NewServiceApplyContract.View {
    public static final int GET_PERSON = 2;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ll_add_photo)
    LinearLayout ll_add_photo;
    @BindView(R.id.tv_company_name)
    TextView tv_company_name;
    @BindView(R.id.et_content)
    ClearEditText et_content;
    @BindView(R.id.et_title)
    ClearEditText et_title;
    @BindView(R.id.tv_person)
    TextView tv_person;

    private List<String> path;
    private List<WcbBean> mList;
    private List<WcbBean> mList1;
    private GalleryConfig galleryConfig;
    private ImageAdapter mAdapter;
    private Dialog dialog;
    private NewServiceApplyContract.Presenter presenter;
    private BuildingAdminListResult result;
    private int size;
    private String strImage = "";
    private String title;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        presenter = new NewServiceApplyPresenter(this);
        CompanyUserBean bean = CompanyUserBean.first(CompanyUserBean.class);
        if (bean != null) {
            tv_company_name.setText(bean.getCompanyName());
        }
        path = new ArrayList<>();
        mList = new ArrayList<>();
        mList.add(new WcbBean("从手机相册选择"));
        mList.add(new WcbBean("相机拍摄"));
        mList1 = new ArrayList<>();
        mList1.add(new WcbBean("查看原图"));
        mList1.add(new WcbBean("删除"));
        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())
                .iHandlerCallBack(iHandlerCallBack)
                .provider("com.bangjiat.bjt.fileprovider")
                .pathList(path)
                .filePath("/Gallery/Pictures")
                .build();

        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        recyclerView.setHasFixedSize(true);
        mAdapter = new ImageAdapter(path, mContext);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ImageAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showDia(position);
            }
        });
    }

    private void showDia(final int po) {
        final WCBMenu wcbMenu = new WCBMenu(mContext);
        wcbMenu.setCancel("取消")
                .setStringList(mList1)
                .setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        wcbMenu.dismiss();
                        if (position == 0) {
                            Intent intent = new Intent(mContext, FullImageActivity.class);
                            intent.putExtra("path", path.get(po));
                            startActivity(intent);
                        } else {
                            path.remove(po);
                            mAdapter.setLists(path);
                            mAdapter.notifyDataSetChanged();

                            if (path.size() < 4)
                                ll_add_photo.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .show();
    }

    IHandlerCallBack iHandlerCallBack = new IHandlerCallBack() {
        @Override
        public void onStart() {
            Logger.d("onStart: 开启");
        }

        @Override
        public void onSuccess(List<String> photoList) {
            Logger.d("onSuccess: 返回数据");
            path.addAll(photoList);
            if (path.size() == 0) {
                recyclerView.setVisibility(View.GONE);
                ll_add_photo.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                if (path.size() == 4) {
                    ll_add_photo.setVisibility(View.GONE);
                } else ll_add_photo.setVisibility(View.VISIBLE);
            }

            mAdapter.setLists(path);
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onCancel() {
            Logger.d("onCancel: 取消");
        }

        @Override
        public void onFinish() {
            Logger.d("onFinish: 结束");
        }

        @Override
        public void onError() {
            Logger.d("onError: 出错");
        }
    };

    @OnClick(R.id.btn_submit)
    public void clickSubmit(View view) {

        if (result == null) {
            error("请选择审批人");
            return;
        }
        title = et_title.getText().toString();
        if (title.isEmpty()) {
            error("请填写申请事项");
            return;
        }
        content = et_content.getText().toString();
        if (content.isEmpty()) {
            error("请填写具体内容");
            return;
        }

        if (path.size() > 0) {
            size = path.size();
            for (String s : path) {
                presenter.uploadImage(s);
            }
        } else {
            save();
        }
    }

    private void save() {
        NewApplyInput input = new NewApplyInput(title, content,
                new NewApplyInput.ProgressBean(result.getUserId(), result.getRealname()));
        presenter.addNewApply(DataUtil.getToken(mContext), input);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_new_apply;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText("新的申请");
        TextView tv_cancel = findViewById(R.id.toolbar_cancel);
        tv_cancel.setText("取消");

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessageDialog();
//                showSuccessDialog();
            }
        });
    }

    @OnClick(R.id.tv_person)
    public void clickPerson(View view) {
        startActivityForResult(new Intent(mContext, BuildingAdminListActivity.class), GET_PERSON);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GET_PERSON) {
                result = (BuildingAdminListResult) data.getSerializableExtra("data");
                tv_person.setText(result.getRealname());

            }
        }
    }

    @OnClick(R.id.ll_add_photo)
    public void clickAddPhoto(View view) {
        showExitDialog();
    }

    private void showExitDialog() {
        final WCBMenu wcbMenu = new WCBMenu(mContext);
        wcbMenu.setCancel("取消")
                .setStringList(mList)
                .setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        wcbMenu.dismiss();
                        if (position == 0) {
                            galleryConfig.getBuilder().isOpenCamera(false).isShowCamera(false).build();
                            GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(NewApplyActivity.this);
                        } else {
                            // 进行正常操作
                            GalleryPick.getInstance().setGalleryConfig(galleryConfig).openCamera(NewApplyActivity.this);
                        }
                    }
                })
                .show();
    }

    private void showSuccessDialog() {
        new AlertDialog(this).builder()
                .setMsg("提交成功，等待审核")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                }).show();
    }

    private void showMessageDialog() {
        new AlertDialog(this).builder()
                .setMsg("确定放弃此次申请?")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();

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
        Logger.e(err);
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success() {
        dismissDialog();
        showSuccessDialog();
    }

    @Override
    public void uploadSuccess(String data) {
        size--;
        if (size > 0) strImage += data + "|";
        else strImage += data;

        if (size == 0) {
            Logger.d(strImage);
            save1();
        }
    }

    private void save1() {
        NewApplyInput input = new NewApplyInput(title, content,
                new NewApplyInput.ProgressBean(result.getUserId(), result.getRealname()), strImage);
        presenter.addNewApply(DataUtil.getToken(mContext), input);
    }

    @Override
    public void uploadFail(String err) {
        error(err);
    }
}
