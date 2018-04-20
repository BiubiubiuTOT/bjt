package com.bangjiat.bangjiaapp.module.home.scan.ui;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseToolBarActivity;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bertsir.zbar.CameraPreview;
import cn.bertsir.zbar.QRUtils;
import cn.bertsir.zbar.QrManager;
import cn.bertsir.zbar.ScanCallback;
import cn.bertsir.zbar.view.ScanView;

public class ScanActivity extends BaseToolBarActivity {
    @BindView(R.id.cp)
    CameraPreview cp;
    @BindView(R.id.sv)
    ScanView sv;
    @BindView(R.id.tv_des)
    TextView tv_des;

    private MediaPlayer mediaPlayer;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean playBeep;
    private boolean vibrate;
    private static final long VIBRATE_DURATION = 200L;
    public static final String SCAN_TYPE = "scan_type";
    private static final int REQUEST_IMAGE = 1;
    public static final int TYPE_FRIENDS = 1;
    public static final int TYPE_COMPANY = 2;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate");
        initView();
    }

    private void initView() {
        sv.setCornerColor(getResources().getColor(R.color.mine_bg));
        sv.setLineColor(getResources().getColor(R.color.mine_bg));
        sv.startScan();

        type = getIntent().getIntExtra(SCAN_TYPE, 1);

        if (type == 2)
            tv_des.setText("扫描对方公司二维码\n加入该公司");
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (cp != null) {
            cp.setScanCallback(resultCallback);
            cp.start();
        }
        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
        sv.onResume();
    }

    private ScanCallback resultCallback = new ScanCallback() {
        @Override
        public void onScanResult(String result) {
            playBeepSoundAndVibrate();
            if (cp != null) {
                cp.setFlash(false);
            }
            QrManager.getInstance().getResultCallback().onScanSuccess(result);
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cp != null) {
            cp.setFlash(false);
            cp.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (cp != null) {
            cp.stop();
        }
        sv.onPause();
    }

    /**
     * 从相册选择
     */
    private void fromAlbum() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_scan;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText("扫一扫");
        TextView textView1 = findViewById(R.id.toolbar_other);
        textView1.setText("相册");
        toolbar.setNavigationIcon(R.mipmap.back_white);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromAlbum();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            final Uri uri = data.getData();
            final ContentResolver cr = this.getContentResolver();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Bitmap Qrbitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                        final String qrcontent = QRUtils.getInstance().decodeQRcode(Qrbitmap);
                        Qrbitmap.recycle();
                        Qrbitmap = null;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!TextUtils.isEmpty(qrcontent)) {
                                    QrManager.getInstance().getResultCallback().onScanSuccess(qrcontent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "识别失败！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (Exception e) {
                        Log.e("Exception", e.getMessage(), e);
                    }
                }
            }).start();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @OnClick(R.id.tv_my_code)
    public void clickMyCode(View view) {
        startActivity(new Intent(mContext, MyCodeActivity.class));
    }

}
