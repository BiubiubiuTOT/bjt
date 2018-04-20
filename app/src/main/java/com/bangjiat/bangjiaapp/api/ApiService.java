package com.bangjiat.bangjiaapp.api;

import com.bangjiat.bangjiaapp.common.BaseResult;
import com.bangjiat.bangjiaapp.common.Constants;
import com.bangjiat.bangjiaapp.module.main.account.beans.LoginInput;
import com.bangjiat.bangjiaapp.module.main.account.beans.RegisterInput;
import com.bangjiat.bangjiaapp.module.home.company.beans.CompanyInput;
import com.bangjiat.bangjiaapp.module.me.personaldata.beans.UserInfoBean;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by Ligh on 2017/8/22 17:37
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 * 200 成功请求
 * http://192.168.0.118:1004/swagger-ui.html#/
 * 102
 */

public interface ApiService {
    /**
     * 注册
     *
     * @param
     * @return
     */
    @POST("auth/register")
    Call<BaseResult<String>> register(@Body RegisterInput input);

    /**
     * 获取验证码
     *
     * @return
     */
    @GET("auth/getRegisterCode")
    Call<BaseResult<String>> getRegisterCode(@Query("phone") String phone, @Query("codeType") int type);

    /**
     * 登录
     *
     * @param
     * @return
     */
    @POST("auth/login")
    Call<BaseResult<String>> login(@Body LoginInput loginInput);

    /**
     * 获取公告
     *
     * @param token
     * @return
     */
    @GET("sys/getSysNotice")
    Call<BaseResult<String>> getNotice(@Header(Constants.TOKEN_NAME) String token);

    /**
     * 保存反馈
     *
     * @return
     */
    @POST("user/saveFeedBack")
    Call<BaseResult<String>> saveFeedBack();


    /**
     * 新建公司
     *
     * @param token
     * @param input
     * @return
     */
    @POST("api/company/add/Company")
    Call<BaseResult<String>> addCompany(@Header(Constants.TOKEN_NAME) String token, @Body CompanyInput input);

    /**
     * 获取用户信息
     *
     * @param name
     * @return
     */
    @GET("user/getUserInfo")
    Call<BaseResult<UserInfoBean>> getUserInfo(@Header(Constants.TOKEN_NAME) String name);

    /**
     * 修改用户信息
     *
     * @param name
     * @param bean
     * @return
     */
    @PUT("user/updataUserInfo")
    Call<BaseResult<String>> updateUserInfo(@Header(Constants.TOKEN_NAME) String name, @Body UserInfoBean bean);

    /**
     * 上传反馈图片
     *
     * @return
     */
    @Multipart
    @POST("/FileRecord/UploadImgWithId")
    Call<ResponseBody> uploadFile(@Query("FileId") String uid,
                                  @PartMap Map<String, RequestBody> params);

    /**
     * 反馈
     *
     * @param token
     * @param bean
     * @return
     */
//    @POST("feedback/CreateAsync")
//    Call<ResponseBody> sendFeedBack(@Header("Authorization") String token, @Body FeedBackBean bean);


    /**
     * 修改头像数据
     */
//    @POST("user/ChangeProfilePic")
//    Call<ResponseBody> updateIconData(@Header("Authorization") String token, @Body IconInput input);

    /**
     * 上传头像
     *
     * @return
     */
//    @Multipart
//    @POST("/File/AppUploadPhoto")
//    Call<UploadIconResult> uploadIcon(@PartMap Map<String, RequestBody> params);


}
