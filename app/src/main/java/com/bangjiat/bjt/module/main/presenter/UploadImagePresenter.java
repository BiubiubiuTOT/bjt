package com.bangjiat.bjt.module.main.presenter;

import com.bangjiat.bjt.module.main.contract.UploadImageContract;
import com.bangjiat.bjt.module.main.model.UploadImageModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/11 0011
 */

public class UploadImagePresenter implements UploadImageContract.Presenter {
    UploadImageContract.View view;
    UploadImageContract.Model model;

    public UploadImagePresenter(UploadImageContract.View view) {
        this.view = view;
        model=new UploadImageModel(this);
    }

    @Override
    public void uploadImage(String photo) {
        model.uploadImage(photo);
    }

    @Override
    public void success(String url) {
        view.success(url);
    }

    @Override
    public void fail(String err) {
        view.fail(err);
    }
}
