package com.bangjiat.bjt.module.home.work.kaoqin.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaHistoryResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaTotalResult;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.ClockContract;

import java.util.List;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/16 0016
 */

public class ClockModel implements ClockContract.Model {
    private ClockContract.Presenter presenter;

    public ClockModel(ClockContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getAllClockList(String token, long begin, long end) {
        ApiFactory.getService().getAllColckList(token, begin, end).enqueue(new MyCallBack<BaseResult<List<DakaHistoryResult>>>() {
            @Override
            public void onSuc(Response<BaseResult<List<DakaHistoryResult>>> response) {
                BaseResult<List<DakaHistoryResult>> body = response.body();
                if (body.getStatus() == 200)
                    presenter.getAllClockListSuccess(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void getClockList(String token, final long begin, long end) {
        ApiFactory.getService().getClockList(token, begin, end).enqueue(new MyCallBack<BaseResult<List<DakaHistoryResult>>>() {
            @Override
            public void onSuc(Response<BaseResult<List<DakaHistoryResult>>> response) {
                BaseResult<List<DakaHistoryResult>> body = response.body();
                if (body.getStatus() == 200)
                    presenter.getClockListSuccess(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void getUserClockList(String token, long begin, long end, String userId) {
        ApiFactory.getService().getUserClockList(token, begin, end, userId).enqueue(new MyCallBack<BaseResult<List<DakaHistoryResult>>>() {
            @Override
            public void onSuc(Response<BaseResult<List<DakaHistoryResult>>> response) {
                BaseResult<List<DakaHistoryResult>> body = response.body();
                if (body.getStatus() == 200)
                    presenter.getUserClockListSuccess(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void getClockTotal(String token, long begin, long end) {
        ApiFactory.getService().getClockTotal(token, begin, end).enqueue(new MyCallBack<BaseResult<DakaTotalResult>>() {
            @Override
            public void onSuc(Response<BaseResult<DakaTotalResult>> response) {
                BaseResult<DakaTotalResult> body = response.body();
                if (body.getStatus() == 200)
                    presenter.getClockTotalSuccess(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void getUserClockTotal(String token, long begin, long end, String userId) {
        ApiFactory.getService().getUserClockTotal(token, begin, end, userId).enqueue(new MyCallBack<BaseResult<DakaTotalResult>>() {
            @Override
            public void onSuc(Response<BaseResult<DakaTotalResult>> response) {
                BaseResult<DakaTotalResult> body = response.body();
                if (body.getStatus() == 200)
                    presenter.getUserClockTotalSuccess(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
