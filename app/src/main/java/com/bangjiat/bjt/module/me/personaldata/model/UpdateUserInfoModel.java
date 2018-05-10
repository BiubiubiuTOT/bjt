package com.bangjiat.bjt.module.me.personaldata.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.ApiService;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.me.personaldata.contract.UpdateUserInfoContract;
import com.orhanobut.logger.Logger;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public class UpdateUserInfoModel implements UpdateUserInfoContract.Model {
    private UpdateUserInfoContract.Presenter presenter;

    public UpdateUserInfoModel(UpdateUserInfoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void updateUserInfo(String token, final UserInfo bean) {
        ApiFactory.getService().updateUserInfo(token, bean).enqueue(new MyCallBack<BaseResult<UserInfo>>() {
            @Override
            public void onSuc(Response<BaseResult<UserInfo>> response) {
                BaseResult<UserInfo> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.updateUserInfoSuccess(body.getData());
                } else {
                    presenter.updateUserInfoFail(body.getMessage());
                }
            }

            @Override
            public void onFail(String message) {
                Logger.e(message);
                presenter.updateUserInfoFail(message);
            }
        });
    }

    @Override
    public void uploadUserHead(String photo) {
        File file = new File(photo);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.UPLOAD_IMAGE_IP)
                .build();
        retrofit.create(ApiService.class).uploadImage(body).
                enqueue(new MyCallBack<BaseResult<String>>() {
                    @Override
                    public void onSuc(Response<BaseResult<String>> response) {
                        BaseResult<String> body = response.body();
                        if (body.getStatus() == 200) {
                            String data = body.getData();
                            Logger.d(data);
                            presenter.uploadUserHeadSuccess(data);
                        } else presenter.uploadUserHeadFail(body.getMessage());
                    }

                    @Override
                    public void onFail(String message) {
                        Logger.e(message);
                        presenter.uploadUserHeadFail(message);
                    }
                });
    }
}
