package com.bangjiat.bjt.module.secretary.door.contract;

import com.bangjiat.bjt.module.secretary.door.beans.DoorApplyDetailResult;
import com.bangjiat.bjt.module.secretary.service.beans.ApprovalDoorInput;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public interface DoorApplyDetailContract {
    interface Model {
        void getDetail(String token, String guardMainId);

        void getAdminDetail(String token, int id, String guardMainId);

        void approvalDoor(String token, int id, ApprovalDoorInput input);
    }

    interface View {
        void success(List<DoorApplyDetailResult> results);

        void error(String err);

        void showDialog();

        void dismissDialog();

        void approvalDoorSuccess();

        void getAdminDetailSuccess(List<DoorApplyDetailResult> results);
    }

    interface Presenter {
        void getDetail(String token, String guardMainId);

        void success(List<DoorApplyDetailResult> results);

        void error(String err);

        void approvalDoor(String token, int id, ApprovalDoorInput input);

        void approvalDoorSuccess();

        void getAdminDetail(String token, int id, String guardMainId);

        void getAdminDetailSuccess(List<DoorApplyDetailResult> results);
    }
}
