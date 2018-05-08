package com.bangjiat.bjt.module.home.work.kaoqin.presenter;

import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaHistoryResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.InDakaInput;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.OutDakaInput;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.DakaContract;
import com.bangjiat.bjt.module.home.work.kaoqin.model.DakaModel;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public class DakaPresenter implements DakaContract.Presenter {
    private DakaContract.View view;
    private DakaContract.Model model;

    public DakaPresenter(DakaContract.View view) {
        this.view = view;
        model = new DakaModel(this);
    }

    @Override
    public void inDaka(String token, InDakaInput input) {
        view.showDialog();
        model.inDaka(token, input);
    }

    @Override
    public void outDaka(String token, OutDakaInput input) {
        view.showDialog();
        model.outDaka(token, input);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }

    @Override
    public void inDakaSuccess() {
        view.dismissDialog();
        view.inDakaSuccess();
    }

    @Override
    public void outDakaSuccess() {
        view.dismissDialog();
        view.outDakaSuccess();
    }

    @Override
    public void getDaka(String token, String begin, String end) {
        view.showDialog();
        model.getDaka(token, begin, end);
    }

    @Override
    public void getDakaSuccess(List<DakaHistoryResult> result) {
        view.dismissDialog();
        view.getDakaSuccess(result);
    }
}
