package com.bangjiat.bjt.module.secretary.contact.util;

import com.bangjiat.bjt.module.secretary.contact.beans.ContactBean;
import com.github.promeg.pinyinhelper.Pinyin;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 联系人工具类
 * Created by yunzhao.liu on 2017/11/11
 */

public class ContactsUtils {
    /**
     * 异步返回联系人列表
     */
    public static List<ContactBean> getContactList(List<ContactBean> list) {
        for (ContactBean bean : list) {
            String letter = String.valueOf(Pinyin.toPinyin(bean.getSlaveNickname().charAt(0)).toUpperCase().charAt(0));
            //非字母开头的统一设置成 "#"
            if (isLetter(letter)) {
                bean.setLetter(letter);
            } else {
                bean.setLetter("#");
            }
        }
        compare(list);
        return list;
    }

    /**
     * 把联系人按照a b c升序排列
     */
    private static List<ContactBean> compare(List<ContactBean> contactInfos) {
        Collections.sort(contactInfos, new Comparator<ContactBean>() {
            @Override
            public int compare(ContactBean o1, ContactBean o2) {
                //升序排列
                if (o1.getLetter().equals("@")
                        || o2.getLetter().equals("#")) {
                    return -1;
                } else if (o1.getLetter().equals("#")
                        || o2.getLetter().equals("@")) {
                    return 1;
                }
                return o1.getLetter().compareTo(o2.getLetter());
            }
        });
        return contactInfos;
    }


    /**
     * 判断字符是否是字母
     */

    public static boolean isLetter(String s) {
        char c = s.charAt(0);
        int i = (int) c;
        if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 模糊搜索（按中文，数字，字母搜索）
     */
    public static boolean searchContact(String searchStr, ContactBean info) {
        return info.getSlaveNickname().contains(searchStr) || info.getSlaveUsername().contains(searchStr)
                || searchLowerByAlphabet(searchStr, info) || searchUpperByAlphabet(searchStr, info)
                || Pinyin.toPinyin(info.getSlaveNickname(), "").toLowerCase().contains(searchStr)
                || Pinyin.toPinyin(info.getSlaveUsername(), "").toUpperCase().contains(searchStr);
    }

    /**
     * 按中文首字母
     * 如“中国人”可以搜 “zgr”
     */
    private static boolean searchLowerByAlphabet(String searchStr, ContactBean info) {
        String[] temp = Pinyin.toPinyin(info.getSlaveNickname(), ",").toLowerCase().split(",");
        StringBuilder builder = new StringBuilder();
        for (String str : temp) {
            builder.append(str.charAt(0));
        }
        if (builder.toString().contains(searchStr)) {
            return true;
        }
        return false;
    }

    /**
     * 按中文首字母
     * 如“中国人”可以搜 “ZGR”
     */
    private static boolean searchUpperByAlphabet(String searchStr, ContactBean info) {
        String[] temp = Pinyin.toPinyin(info.getSlaveNickname(), ",").toUpperCase().split(",");
        StringBuilder builder = new StringBuilder();
        for (String str : temp) {
            builder.append(str.charAt(0));
        }
        if (builder.toString().contains(searchStr)) {
            return true;
        }
        return false;
    }
}
