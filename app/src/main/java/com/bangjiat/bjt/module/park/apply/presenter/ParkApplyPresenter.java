package com.bangjiat.bjt.module.park.apply.presenter;

import com.bangjiat.bjt.module.park.apply.beans.DealParkApplyInput;
import com.bangjiat.bjt.module.park.apply.beans.LotResult;
import com.bangjiat.bjt.module.park.apply.beans.ParkApplyHistoryResult;
import com.bangjiat.bjt.module.park.apply.beans.ParkApplyInput;
import com.bangjiat.bjt.module.park.apply.beans.ParkingResult;
import com.bangjiat.bjt.module.park.apply.contract.ParkApplyContract;
import com.bangjiat.bjt.module.park.apply.model.ParkApplyModel;
import com.bangjiat.bjt.module.park.car.beans.CarBean;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/11 0011
 */

public class ParkApplyPresenter implements ParkApplyContract.Presenter {
    private ParkApplyContract.View view;
    private ParkApplyContract.Model model;

    public ParkApplyPresenter(ParkApplyContract.View view) {
        this.view = view;
        model = new ParkApplyModel(this);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);

    }

    @Override
    public void getParkSpaceSuccess(ParkingResult s) {
        view.dismissDialog();
        view.getParkSpaceSuccess(s);
    }

    @Override
    public void getWorkersCarSuccess(List<CarBean> list) {
        view.dismissDialog();
        view.getWorkersCarSuccess(list);
    }

    @Override
    public void parkApplySuccess() {
        view.dismissDialog();
        view.parkApplySuccess();
    }

    @Override
    public void dealParkApplySuccess() {
        view.dismissDialog();
        view.dealParkApplySuccess();
    }

    @Override
    public void getWorkersCar(String token) {
        view.showDialog();
        model.getWorkersCar(token);
    }

    @Override
    public void getParkSpace(String token, int page, int size, String key) {
        view.showDialog();
        model.getParkSpace(token, page, size, key);
    }

    @Override
    public void parkApply(String token, ParkApplyInput input) {

        if (input.getSpaceName() == null) {
            view.error("请选择停车场");
            return;
        }
        if (input.getDetailList() == null) {
            view.error("请选择车辆");
            return;
        }
        if (input.getDetailList().size() == 0) {
            view.error("请选择车辆");
            return;
        }

        view.showDialog();
        model.parkApply(token, input);
    }

    @Override
    public void dealParkApply(String token, DealParkApplyInput input) {
        view.showDialog();
        model.dealParkApply(token, input);
    }

    @Override
    public void getParkApplyHistory(String token, int page, int size, int spaceId) {
        view.showDialog();
        model.getParkApplyHistory(token, page, size, spaceId);
    }

    @Override
    public void getParkApplyHistorySuccess(ParkApplyHistoryResult result) {
        view.dismissDialog();
        view.getParkApplyHistorySuccess(result);
    }

    @Override
    public void getLotList(String token, int spaceId) {
        view.showDialog();
        model.getLotList(token, spaceId);
    }

    @Override
    public void getLotListSuccess(List<LotResult> results) {
        view.dismissDialog();
        view.getLotListSuccess(results);
    }
}
