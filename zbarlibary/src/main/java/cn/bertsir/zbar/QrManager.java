package cn.bertsir.zbar;

/**
 * Created by Bert on 2017/9/22.
 */

public class QrManager {

    private static QrManager instance;
    private QrConfig options;

    public OnScanResultCallback resultCallback;

    public synchronized static QrManager getInstance() {
        if (instance == null)
            instance = new QrManager();
        return instance;
    }

    public OnScanResultCallback getResultCallback() {
        return resultCallback;
    }


    public QrManager init(QrConfig options) {
        this.options = options;
        return this;
    }

    public void startScan(OnScanResultCallback resultCall) {
        if (options == null) {
            options = new QrConfig.Builder().create();
        }
        // 绑定图片接口回调函数事件
        resultCallback = resultCall;
    }


    public interface OnScanResultCallback {
        /**
         * 处理成功
         * 多选
         *
         * @param result
         */
        void onScanSuccess(String result);

    }
}
