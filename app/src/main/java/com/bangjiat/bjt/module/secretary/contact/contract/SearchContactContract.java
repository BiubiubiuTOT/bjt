package com.bangjiat.bjt.module.secretary.contact.contract;

import com.bangjiat.bjt.module.secretary.contact.beans.ScanUser;
import com.bangjiat.bjt.module.secretary.contact.beans.SearchContactResult;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/4 0004
 */

public interface SearchContactContract {
    interface Model {
        void searchContact(String token, String key);

        void getContactByScan(String token,String username);
    }

    interface View {
        void fail(String err);

        void success(SearchContactResult bean);

        void getContactByScanSuccess(ScanUser user);
    }

    interface Presenter {
        void searchContact(String token, String key);

        void fail(String err);

        void success(SearchContactResult bean);

        void getContactByScan(String token,String username);

        void getContactByScanSuccess(ScanUser user);

    }
}
