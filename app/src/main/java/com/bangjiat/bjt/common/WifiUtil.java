package com.bangjiat.bjt.common;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.bangjiat.bjt.module.home.work.kaoqin.beans.WifiBean;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.WIFI_SERVICE;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/2 0002
 */

public class WifiUtil {
    private List<ScanResult> scanResults;
    private final WifiInfo wi;
    private final WifiManager wm;
    private Context context;

    public WifiUtil(Context context) {
        this.context = context;

        wm = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
        wi = wm.getConnectionInfo();
    }

    /**
     * 开始扫描
     */
    private void startScan() {
        if (wm.startScan()) {
            scanResults = wm.getScanResults();
        }
    }

    public List<WifiBean> getWifiList() {
        startScan();
        List<WifiBean> list = new ArrayList<>();
        WifiBean wifiBean;
        String ssid = wi.getSSID();
        ssid = ssid.substring(1, ssid.length() - 1);
        for (ScanResult result : scanResults) {
            wifiBean = new WifiBean(result.SSID);
            if (result.SSID.equals(ssid)) {
                wifiBean.setConnected(true);
                list.add(0, wifiBean);
            } else
                list.add(wifiBean);
        }

        Logger.d(ssid);
        return list;
    }
}
