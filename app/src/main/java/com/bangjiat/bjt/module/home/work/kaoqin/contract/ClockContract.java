package com.bangjiat.bjt.module.home.work.kaoqin.contract;

import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaHistoryResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaTotalResult;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/16 0016
 */

public interface ClockContract {
    interface Model {
        void getAllClockList(String token, long begin, long end);

        void getClockList(String token, long begin, long end);

        void getUserClockList(String token, long begin, long end, String userId);

        void getClockTotal(String token, long begin, long end);

        void getUserClockTotal(String token, long begin, long end, String userId);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void error(String err);

        void getAllClockListSuccess(List<DakaHistoryResult> results);

        void getClockListSuccess(List<DakaHistoryResult> results);

        void getUserClockListSuccess(List<DakaHistoryResult> results);

        void getClockTotalSuccess(DakaTotalResult result);

        void getUserClockTotalSuccess(DakaTotalResult result);
    }

    interface Presenter {
        void getAllClockList(String token, long begin, long end);

        void getClockList(String token, long begin, long end);

        void getUserClockList(String token, long begin, long end, String userId);

        void getClockTotal(String token, long begin, long end);

        void getUserClockTotal(String token, long begin, long end, String userId);

        void error(String err);

        void getAllClockListSuccess(List<DakaHistoryResult> results);

        void getClockListSuccess(List<DakaHistoryResult> results);

        void getUserClockListSuccess(List<DakaHistoryResult> results);

        void getClockTotalSuccess(DakaTotalResult result);

        void getUserClockTotalSuccess(DakaTotalResult result);
    }
}
