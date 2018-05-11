package com.bangjiat.bjt.module.main.model;

import com.bangjiat.bjt.api.ApiService;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.module.main.contract.UploadImageContract;
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
 * @date 2018/5/11 0011
 */

public class UploadImageModel implements UploadImageContract.Model {
    private UploadImageContract.Presenter presenter;

    public UploadImageModel(UploadImageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void uploadImage(String photo) {
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
                            presenter.success(data);
                        } else presenter.fail(body.getMessage());
                    }

                    @Override
                    public void onFail(String message) {
                        Logger.e(message);
                        presenter.fail(message);
                    }
                });
    }
}
