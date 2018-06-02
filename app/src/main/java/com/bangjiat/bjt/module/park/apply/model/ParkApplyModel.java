package com.bangjiat.bjt.module.park.apply.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.module.park.apply.beans.DealParkApplyInput;
import com.bangjiat.bjt.module.park.apply.beans.LotResult;
import com.bangjiat.bjt.module.park.apply.beans.ParkApplyHistoryResult;
import com.bangjiat.bjt.module.park.apply.beans.ParkApplyInput;
import com.bangjiat.bjt.module.park.apply.beans.ParkingResult;
import com.bangjiat.bjt.module.park.apply.contract.ParkApplyContract;
import com.bangjiat.bjt.module.park.car.beans.CarBean;

import java.util.List;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/11 0011
 */

public class ParkApplyModel implements ParkApplyContract.Model {
    private ParkApplyContract.Presenter presenter;

    public ParkApplyModel(ParkApplyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getWorkersCar(String token) {
        ApiFactory.getService().getWorkersCarList(token).enqueue(new MyCallBack<BaseResult<List<CarBean>>>() {
            @Override
            public void onSuc(Response<BaseResult<List<CarBean>>> response) {
                BaseResult<List<CarBean>> body = response.body();
                if (body.getStatus() == 200)
                    presenter.getWorkersCarSuccess(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void getParkSpace(String token, int page, int size, String key) {
        ApiFactory.getService().getParkSpace(token, page, Constants.SIZE, key).enqueue(new MyCallBack<BaseResult<ParkingResult>>() {
            @Override
            public void onSuc(Response<BaseResult<ParkingResult>> response) {
                BaseResult<ParkingResult> body = response.body();
                if (body.getStatus() == 200)
                    presenter.getParkSpaceSuccess(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void parkApply(String token, ParkApplyInput input) {
        ApiFactory.getService().addParkApply(token, input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.parkApplySuccess();
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void dealParkApply(String token, DealParkApplyInput input) {
        ApiFactory.getService().dealParkApply(token, input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.dealParkApplySuccess();
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void getParkApplyHistory(String token, int page, int size, int spaceId) {
        if (spaceId == 0) {
            ApiFactory.getService().getParkApplyHistory(token, page, Constants.SIZE).enqueue(new MyCallBack<BaseResult<ParkApplyHistoryResult>>() {
                @Override
                public void onSuc(Response<BaseResult<ParkApplyHistoryResult>> response) {
                    BaseResult<ParkApplyHistoryResult> body = response.body();
                    if (body.getStatus() == 200) {
                        presenter.getParkApplyHistorySuccess(body.getData());
                    } else presenter.error(body.getMessage());
                }

                @Override
                public void onFail(String message) {
                    presenter.error(message);
                }
            });
        } else {
            ApiFactory.getService().getParkApplyHistory(token, page, Constants.SIZE, spaceId).enqueue(new MyCallBack<BaseResult<ParkApplyHistoryResult>>() {
                @Override
                public void onSuc(Response<BaseResult<ParkApplyHistoryResult>> response) {
                    BaseResult<ParkApplyHistoryResult> body = response.body();
                    if (body.getStatus() == 200) {
                        presenter.getParkApplyHistorySuccess(body.getData());
                    } else presenter.error(body.getMessage());
                }

                @Override
                public void onFail(String message) {
                    presenter.error(message);
                }
            });
        }
    }

    @Override
    public void getLotList(String token, int spaceId) {
        ApiFactory.getService().getLotList(token, spaceId).enqueue(new MyCallBack<BaseResult<List<LotResult>>>() {
            @Override
            public void onSuc(Response<BaseResult<List<LotResult>>> response) {
                BaseResult<List<LotResult>> body = response.body();
                if (body.getStatus() == 200)
                    presenter.getLotListSuccess(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
