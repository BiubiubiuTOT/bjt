package com.bangjiat.bjt.api;

import com.bangjiat.bjt.common.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ligh on 2017/8/25 14:05
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 */

public class ApiFactory {
    private static ApiService apiService;

    static OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
//            .addInterceptor(new HttpLoggingInterceptor())
            .build();

    private static Retrofit stringRetrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_IP)
            .client(client)//设置读写连接超时
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiService getService() {
        if (apiService == null)
            apiService = stringRetrofit.create(ApiService.class);
        return apiService;
    }
}
