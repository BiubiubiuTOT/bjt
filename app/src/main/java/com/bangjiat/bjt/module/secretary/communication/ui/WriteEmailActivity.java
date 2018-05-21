package com.bangjiat.bjt.module.secretary.communication.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.FullImageActivity;
import com.bangjiat.bjt.common.GlideImageLoader;
import com.bangjiat.bjt.common.PickActivity;
import com.bangjiat.bjt.common.WCBMenu;
import com.bangjiat.bjt.common.WcbBean;
import com.bangjiat.bjt.module.main.contract.UploadImageContract;
import com.bangjiat.bjt.module.main.presenter.UploadImagePresenter;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.secretary.communication.beans.EmailBean;
import com.bangjiat.bjt.module.secretary.communication.contract.SendEmailContract;
import com.bangjiat.bjt.module.secretary.communication.presenter.SendEmailPresenter;
import com.bangjiat.bjt.module.secretary.contact.beans.ContactBean;
import com.bangjiat.bjt.module.secretary.service.adapter.ImageAdapter;
import com.dou361.dialogui.DialogUIUtils;
import com.githang.statusbar.StatusBarCompat;
import com.orhanobut.logger.Logger;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WriteEmailActivity extends BaseToolBarActivity implements UploadImageContract.View, SendEmailContract.View {
    private static final int SELECT_CONTACTS = 2;
    @BindView(R.id.ll_accessory)
    LinearLayout ll_accessory;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.tv_receive_name)
    TextView tv_receive_name;
    @BindView(R.id.tv_send_name)
    TextView tv_send_name;
    @BindView(R.id.et_title)
    TextView et_title;
    @BindView(R.id.tv_select_accessory)
    TextView tv_select_accessory;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    boolean isClick;
    InputMethodManager im;
    float lastHight;
    private GalleryConfig galleryConfig;
    private List<String> path;
    private List<WcbBean> mList1;
    private UploadImageContract.Presenter upImgPresenter;
    private SendEmailContract.Presenter presenter;
    private List<ContactBean> selectList;
    private Dialog dialog;
    private int size;
    private String receiver;
    private String receiverId;
    private String strImage = "";
    private UserInfo info;
    private List<WcbBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));

        initView();
    }

    private void initView() {
        mList = new ArrayList<>();
        mList.add(new WcbBean("确定", getResources().getColor(R.color.red)));
        info = UserInfo.first(UserInfo.class);
        tv_send_name.setText(info.getNickname());
        upImgPresenter = new UploadImagePresenter(this);
        presenter = new SendEmailPresenter(this);
        path = new ArrayList<>();
        mList1 = new ArrayList<>();
        mList1.add(new WcbBean("查看原图"));
        mList1.add(new WcbBean("删除"));
        im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                float mHeight = (float) scrollView.getHeight();
                Logger.d("scrollView :" + mHeight);
                if (mHeight == lastHight) return;

                if (mHeight > lastHight) {
//                    toTop();
                } else {
//                    toBottom();
                    if (isClick) {
                        dismissView(tv_select_accessory);
                    }
                }

                lastHight = mHeight;
            }
        });

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

        initConfig();
    }

    private void initConfig() {
        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .provider("com.bangjiat.bjt.fileprovider")  // provider (必填)
                .pathList(path)                         // 记录已选的图片
                .multiSelect(true)                      // 是否多选   默认：false
                .crop(false)                             // 快捷开启裁剪功能，仅当单选 或直接开启相机时有效
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath("/Gallery/Pictures")          // 图片存放路径
                .build();
    }

    private ImageAdapter mAdapter;
    IHandlerCallBack iHandlerCallBack = new IHandlerCallBack() {
        @Override
        public void onStart() {
            Logger.d("onStart: 开启");
        }

        @Override
        public void onSuccess(List<String> photoList) {
            Logger.d("onSuccess: 返回数据");

            mAdapter.setLists(photoList);
            mAdapter.notifyDataSetChanged();

            tv_select_accessory.setText(photoList.size() + "");
            path.clear();
            path.addAll(photoList);
            size = path.size();
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

    private void toBottom() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    private void toTop() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
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
                        }
                    }
                })
                .show();
    }

    @OnClick(R.id.tv_select_accessory)
    public void clickSelectAccessory(View view) {
        if (isClick) {
            dismissView(view);
        } else {
            showView(view);
        }

    }

    private void showView(View view) {
        isClick = true;
        TextView textView = (TextView) view;
        Drawable drawable = getResources().getDrawable(R.mipmap.email_white);

        ll_accessory.setVisibility(View.VISIBLE);
        textView.setBackgroundResource(R.drawable.login_btn_bg);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        textView.setCompoundDrawables(drawable, null, null, null);

        im.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void dismissView(View view) {
        isClick = false;
        TextView textView = (TextView) view;
        Drawable drawable = getResources().getDrawable(R.mipmap.email_black);

        ll_accessory.setVisibility(View.GONE);
        textView.setBackgroundResource(R.drawable.accessory_bg);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    @OnClick(R.id.iv_add)
    public void clickAdd(View view) {
        GalleryPick.getInstance().setGalleryConfig(galleryConfig);

        Intent intent = new Intent(mContext, PickActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.iv_add_contacts)
    public void clickAddContacts(View view) {
        startActivityForResult(new Intent(mContext, SelectContactsActivity.class), SELECT_CONTACTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_CONTACTS) {
                selectList = (List<ContactBean>) data.getSerializableExtra("data");
                if (selectList != null && selectList.size() > 0) {
                    receiver = getReceiverName(selectList);
                    tv_receive_name.setText(receiver);
                }
            }
        }
    }

    private String getReceiverName(List<ContactBean> selectList) {
        String string = "";
        receiverId = "";
        for (ContactBean bean : selectList) {
            string += bean.getSlaveNickname() + ",";
            receiverId += bean.getSlaveUserId() + ",";
        }
        receiverId = receiverId.substring(0, receiverId.length() - 1);
        string = string.substring(0, string.length() - 1);
        return string;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_write_email;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        toolbar.setTitle("");

        TextView tv_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        TextView tv_send = toolbar.findViewById(R.id.toolbar_other);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);

        tv_cancel.setText("取消");
        tv_send.setText("发送");
        tv_title.setText("写邮件");

        tv_cancel.setTextColor(getResources().getColor(R.color.black));
        tv_send.setTextColor(getResources().getColor(R.color.mine_bg));
        tv_title.setTextColor(getResources().getColor(R.color.black));

        tv_cancel.setVisibility(View.VISIBLE);
        tv_send.setVisibility(View.VISIBLE);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCancelSendDialog();
            }
        });
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (path.size() > 0) {
                    showDialog();
                    for (String p : path) {
                        upImgPresenter.uploadImage(p);
                    }
                } else sendEmail();
            }
        });
    }

    private void sendEmail() {
        EmailBean bean = new EmailBean();
        bean.setContent(et_content.getText().toString());
        bean.setTitle(et_title.getText().toString());
        bean.setReceiver(receiver);
        bean.setSender(info.getNickname());
        bean.setSenderId(info.getUserId());
        bean.setSendDate(System.currentTimeMillis());
        bean.setReceiverId(receiverId);
        bean.setResource(strImage);

        Logger.d(bean.toString());
        presenter.sendEmail(DataUtil.getToken(mContext), bean);
    }

    private void showCancelSendDialog() {
        final WCBMenu wcbMenu = new WCBMenu(mContext);
        wcbMenu.setTitle("您确定取消发送么？")
                .setCancel("取消")
                .setStringList(mList)
                .setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        wcbMenu.dismiss();
                        finish();
                    }
                })
                .show();

    }

    @Override
    public void success(String data) {
        size--;
        if (size > 0) strImage += data + "|";
        else strImage += data;

        if (size == 0) {
            dismissDialog();
            Logger.d(strImage);
            sendEmail();
        }
    }

    @Override
    public void showDialog() {
        if (dialog != null) {
            if (!dialog.isShowing())
                dialog.show();
        } else dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null) dialog.dismiss();
    }

    @Override
    public void success() {
        showSuccessExitDialog();
    }

    public void showSuccessExitDialog() {
        new AlertDialog(mContext).builder().setMsg("邮件发送成功").setCancelable(false).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }).show();
    }

    @Override
    public void fail(String err) {
        dismissDialog();
        Logger.e(err);
    }
}
