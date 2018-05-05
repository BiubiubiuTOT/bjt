package com.bangjiat.bjt.common;

import java.io.UnsupportedEncodingException;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/5 0005
 */

public class DesUtil {
    /**
     * 加密
     *
     * @param srcStr
     * @return
     */
    public static String encrypt(String srcStr) {
        byte[] src = new byte[0];
        try {
            src = srcStr.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] buf = DES.encrypt(src);
        return DES.parseByte2HexStr(buf);
    }

    /**
     * 解密
     *
     * @param hexStr
     * @return
     * @throws Exception
     */
    public static String decrypt(String hexStr) throws Exception {
        byte[] src = DES.parseHexStr2Byte(hexStr);
        byte[] buf = DES.decrypt(src);
        return new String(buf, "utf-8");
    }

}
