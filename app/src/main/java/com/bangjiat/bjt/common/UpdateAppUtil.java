package com.bangjiat.bjt.common;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.NotificationBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomDownloadFailedListener;
import com.allenliu.versionchecklib.v2.callback.CustomDownloadingDialogListener;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.bangjiat.bjt.BuildConfig;
import com.bangjiat.bjt.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/18 0018
 * http://www.bangjiat.com/app/auth/select/SystemValue?keyId=android_desc  //描述
 */

public class UpdateAppUtil {
    private Context mContext;

    public UpdateAppUtil(Context mContext) {
        this.mContext = mContext;
    }

    public void checkVersion() {
        final DownloadBuilder builder = AllenVersionChecker
                .getInstance()
                .requestVersion()
                .setRequestUrl(Constants.BASE_IP + "auth/select/SystemValue?keyId=android_app")
                .request(new RequestVersionListener() {
                    @Nullable
                    @Override
                    public UIData onRequestVersionSuccess(String result) {
                        BaseResult<KeyValueBean> beanBaseResult = new Gson().fromJson(result, new TypeToken<BaseResult<KeyValueBean>>() {
                        }.getType());
                        if (beanBaseResult != null) {
                            if (beanBaseResult.getStatus() == 200) {
                                KeyValueBean data = beanBaseResult.getData();
                                String version = data.getValue();
                                Logger.d(version);
                                if (!version.contains("V"))
                                    return null;

                                int i = version.lastIndexOf("V");
                                int j = version.lastIndexOf(".");
                                String s = version.substring(i, j);
                                Logger.d(s);

                                String localVersion = Constants.getVersion(mContext);
                                if (!s.equals(localVersion))
                                    return crateUIData();
                                else return null;
                            } else return null;
                        } else return null;

                    }

                    @Override
                    public void onRequestVersionFailure(String s) {
                        Logger.e(s);
                    }
                });

        /**
         * 提示app更新界面
         */
        builder.setCustomVersionDialogListener(new CustomVersionDialogListener() {
            @Override
            public Dialog getCustomVersionDialog(Context context, UIData uiData) {
                BaseDialog baseDialog = new BaseDialog(context, R.layout.custom_dialog_one_layout);
                return baseDialog;
            }
        });

        /**
         * App更新界面
         */
        builder.setCustomDownloadingDialogListener(new CustomDownloadingDialogListener() {
            @Override
            public Dialog getCustomDownloadingDialog(Context context, int progress, UIData versionBundle) {
                final BaseDialog baseDialog = new BaseDialog(context, R.layout.custom_download_layout);
                Button btn_cancel = baseDialog.findViewById(R.id.btn_submit);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        builder.setShowDownloadingDialog(false);
                        baseDialog.setCancelable(true);
                    }
                });
                return baseDialog;
            }

            //下载中会不断回调updateUI方法
            @Override
            public void updateUI(Dialog dialog, int progress, UIData versionBundle) {
                TextView tvProgress = dialog.findViewById(R.id.tv_progress);
                ProgressBar progressBar = dialog.findViewById(R.id.pb);
                progressBar.setProgress(progress);
                tvProgress.setText(mContext.getString(R.string.versionchecklib_progress, progress));
            }
        });
        /**
         * 通知栏
         */
        builder.setNotificationBuilder(
                NotificationBuilder.create()
                        .setRingtone(true)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTicker("下载")
                        .setContentTitle("正在下载新版本")
                        .setContentText(mContext.getString(R.string.custom_content_text))
        );

        /**
         * 下载失败
         */
        builder.setCustomDownloadFailedListener(new CustomDownloadFailedListener() {
            @Override
            public Dialog getCustomDownloadFailed(Context context, UIData uiData) {
                BaseDialog baseDialog = new BaseDialog(context, R.layout.custom_download_failed_dialog);
                return baseDialog;
            }
        });

        builder.setDownloadAPKPath("storage/emulated/0/bjt/");
        builder.setShowDownloadingDialog(false);

        builder.excuteMission(mContext);
    }

    /**
     * @return
     * @important 使用请求版本功能，可以在这里设置downloadUrl
     * 这里可以构造UI需要显示的数据
     * UIData 内部是一个Bundle
     */
    private UIData crateUIData() {
        UIData uiData = UIData.create();
        uiData.setDownloadUrl(Constants.BASE_IP + "auth/select/SystemDownload?keyId=android_app");
        return uiData;
    }

    /**
     * 安装apk
     *
     * @param file 安装apk
     */

    private void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".fileprovider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        mContext.startActivity(intent);
    }
}
