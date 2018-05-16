package com.bangjiat.bjt.module.home.work.kaoqin.presenter;

import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaHistoryResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaTotalResult;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.ClockContract;
import com.bangjiat.bjt.module.home.work.kaoqin.model.ClockModel;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/16 0016
 */

public class ClockPresenter implements ClockContract.Presenter {
    private ClockContract.Model model;
    private ClockContract.View view;

    public ClockPresenter(ClockContract.View view) {
        this.view = view;
        model = new ClockModel(this);
    }

    @Override
    public void getAllClockList(String token, long begin, long end) {
        view.showDialog();
        model.getAllClockList(token, begin, end);
    }

    @Override
    public void getClockList(String token, long begin, long end) {
        view.showDialog();
        model.getClockList(token, begin, end);
    }

    @Override
    public void getUserClockList(String token, long begin, long end, String userId) {
        view.showDialog();
        model.getUserClockList(token, begin, end, userId);
    }

    @Override
    public void getClockTotal(String token, long begin, long end) {
        view.showDialog();
        model.getClockTotal(token, begin, end);
    }

    @Override
    public void getUserClockTotal(String token, long begin, long end, String userId) {
        view.showDialog();
        model.getUserClockTotal(token, begin, end, userId);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }

    @Override
    public void getAllClockListSuccess(List<DakaHistoryResult> results) {
        view.dismissDialog();
        view.getAllClockListSuccess(results);
    }

    @Override
    public void getClockListSuccess(List<DakaHistoryResult> results) {
        view.dismissDialog();
        view.getClockListSuccess(results);
    }

    @Override
    public void getUserClockListSuccess(List<DakaHistoryResult> results) {
        view.dismissDialog();
        view.getUserClockListSuccess(results);
    }

    @Override
    public void getClockTotalSuccess(DakaTotalResult result) {
        view.dismissDialog();
        view.getClockTotalSuccess(result);
    }

    @Override
    public void getUserClockTotalSuccess(DakaTotalResult result) {
        view.dismissDialog();
        view.getUserClockTotalSuccess(result);
    }
}
