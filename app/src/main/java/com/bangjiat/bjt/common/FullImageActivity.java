package com.bangjiat.bjt.common;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Rance on 2016/12/15 15:56
 * 邮箱：rance935@163.com
 */
public class FullImageActivity extends BaseActivity {
    @BindView(R.id.full_image)
    ImageView fullImage;
    String path;
    private List<WcbBean> mList;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        initData();
    }

    // 加载并显示图片
    public void loadImage(String url) {
        Glide.with(this).load(url).asBitmap().placeholder(R.mipmap.loading)
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        fullImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        fullImage.setImageResource(R.mipmap.error);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        bitmap = resource;
                        fullImage.setImageBitmap(resource);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }

    private void initData() {
        mList = new ArrayList<>();
        mList.add(new WcbBean("保存图片"));
        path = getIntent().getStringExtra("path");

        Logger.d(path);
        loadImage(path);
        fullImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showDeleteDialog();
                return true;
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_full_image;
    }

    private void showDeleteDialog() {
        final WCBMenu wcbMenu = new WCBMenu(mContext);
        wcbMenu.setCancel("取消")
                .setStringList(mList)
                .setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        wcbMenu.dismiss();
                        download(path);
                    }
                })
                .show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @OnClick(R.id.full_image)
    public void onClick() {
        finish();
    }

    // 保存图片到手机
    @SuppressLint("StaticFieldLeak")
    public void download(final String url) {
        new AsyncTask<Void, Integer, File>() {

            @Override
            protected File doInBackground(Void... params) {
                // 首先保存图片
                File pictureFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();

                File appDir = new File(pictureFolder, "bjt");
                if (!appDir.exists()) {
                    appDir.mkdirs();
                }
                String fileName = System.currentTimeMillis() + ".jpg";
                File currentFile = new File(appDir, fileName);

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(currentFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                // 最后通知图库更新
                mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.fromFile(new File(currentFile.getPath()))));
                return null;
            }

            @Override
            protected void onPostExecute(File file) {
                Toast.makeText(mContext, "图片已保存在 Pictures/bjt 文件夹下", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }
        }.execute();
    }
}
