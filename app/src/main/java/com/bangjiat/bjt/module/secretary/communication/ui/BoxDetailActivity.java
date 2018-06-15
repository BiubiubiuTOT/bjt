package com.bangjiat.bjt.module.secretary.communication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.FullImageActivity;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.common.WCBMenu;
import com.bangjiat.bjt.common.WcbBean;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.secretary.communication.beans.EmailBean;
import com.bangjiat.bjt.module.secretary.communication.contract.DealBoxContract;
import com.bangjiat.bjt.module.secretary.communication.presenter.DealBoxPresenter;
import com.bangjiat.bjt.module.secretary.contact.beans.ContactBean;
import com.bangjiat.bjt.module.secretary.service.adapter.ImageAdapter;
import com.githang.statusbar.StatusBarCompat;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BoxDetailActivity extends BaseToolBarActivity implements DealBoxContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_send_name)
    TextView tv_send_name;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_receive_name)
    TextView tv_receiver_name;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.iv_response)
    ImageView iv_response;
    TextView toolbar_title;


    private List<String> photoList;
    private EmailBean bean;
    private int type;
    private DealBoxContract.Presenter presenter;
    private String token;
    private UserInfo userInfo;
    private List<WcbBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
        initData();
    }

    private void initData() {
        mList = new ArrayList<>();
        mList.add(new WcbBean("删除邮件", getResources().getColor(R.color.red)));
        userInfo = UserInfo.first(UserInfo.class);
        token = DataUtil.getToken(mContext);
        presenter = new DealBoxPresenter(this);
        bean = (EmailBean) getIntent().getSerializableExtra("data");
        type = getIntent().getIntExtra("type", 0);
        if (bean != null) {
            photoList = new ArrayList<>();
            String resource = bean.getResource();
            if (resource != null && !resource.isEmpty()) {
                String[] split = resource.split("\\|");
                Logger.d(split[0]);
                photoList = Arrays.asList(split);
                setPhotoAdapter();
            }
            tv_content.setText(bean.getContent());
            tv_send_name.setText(bean.getSender());
            tv_time.setText(TimeUtils.changeToTime(bean.getCtime()));
            tv_title.setText(bean.getTitle());

            type = getIntent().getIntExtra("type", 1);
            if (type == 1) {
                toolbar_title.setText("发件箱");
                tv_receiver_name.setText(bean.getReceiver());
            } else {
                toolbar_title.setText("收件箱");
                iv_response.setVisibility(View.VISIBLE);
                String[] strings = new String[1];
                strings[0] = String.valueOf(bean.getEmailId());
                tv_receiver_name.setText(userInfo.getNickname());

                if (bean.getEmailStatus() == 0)
                    presenter.markBox(DataUtil.getToken(mContext), strings);
            }
        }
    }

    private void setPhotoAdapter() {
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        recyclerView.setHasFixedSize(true);
        ImageAdapter adapter = new ImageAdapter(photoList, mContext);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ImageAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, FullImageActivity.class);
                intent.putExtra("path", photoList.get(position));
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.iv_delete)
    public void clickDelete(View view) {
        showDeleteDialog();
    }

    @OnClick(R.id.iv_share)
    public void clickShare(View view) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, "http://www.bangjiat.com/");
        share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(share, "分享邮件"));
    }

    @OnClick(R.id.iv_response)
    public void clickResponse(View view) {
        ContactBean bean1 = new ContactBean();
        bean1.setSlaveUserId(bean.getSenderId());
        bean1.setSlaveNickname(bean.getSender());
        Intent intent = new Intent(mContext, WriteEmailActivity.class);
        intent.putExtra("data", bean1);
        startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_out_box_detail;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        toolbar_title = toolbar.findViewById(R.id.toolbar_cancel);
        toolbar_title.setVisibility(View.VISIBLE);
        toolbar.setNavigationIcon(R.mipmap.back_black);
        toolbar_title.setTextColor(getResources().getColor(R.color.black));
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void fail(String err) {
        Logger.e(err);
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void deleteOutBoxSuccess() {
        showDia();
    }

    private void showDeleteDialog() {
        final WCBMenu wcbMenu = new WCBMenu(mContext);
        wcbMenu.setTitle("邮件删除后不可恢复？")
                .setCancel("取消")
                .setStringList(mList)
                .setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        wcbMenu.dismiss();

                        String[] strings = new String[1];

                        if (type == 1) {
                            strings[0] = String.valueOf(bean.getEmailId());
                            presenter.deleteOutBox(token, strings);
                        } else {
                            strings[0] = String.valueOf(bean.getRecordId());
                            presenter.deleteInBox(token, strings);
                        }

                    }
                })
                .show();
    }

    private void showDia() {
        new AlertDialog(mContext).builder().setMsg("删除成功").setCancelable(false).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }).show();
    }

    @Override
    public void deleteInBoxSuccess() {
        showDia();
    }

    @Override
    public void markBoxSuccess() {

    }

    @Override
    public void getUnReadCountsSuccess(String s) {

    }
}
