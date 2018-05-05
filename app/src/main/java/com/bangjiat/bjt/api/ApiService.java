package com.bangjiat.bjt.api;

import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.module.home.company.beans.CompanyInput;
import com.bangjiat.bjt.module.home.company.beans.IntoCompanyInput;
import com.bangjiat.bjt.module.main.account.beans.LoginInput;
import com.bangjiat.bjt.module.main.account.beans.RegisterInput;
import com.bangjiat.bjt.module.me.bill.beans.PageBillBean;
import com.bangjiat.bjt.module.me.feedback.beans.FeedBackInput;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfoBean;
import com.bangjiat.bjt.module.me.setting.beans.UpdatePasswordInput;
import com.bangjiat.bjt.module.secretary.contact.beans.ContactBean;
import com.bangjiat.bjt.module.secretary.contact.beans.SearchContactResult;
import com.bangjiat.bjt.module.secretary.door.beans.IntoBuildingInput;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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
    @GET("api/sys/getAllNotice")
    Call<BaseResult<String>> getNotice(@Header(Constants.TOKEN_NAME) String token);

    /**
     * 保存反馈
     *
     * @return
     */
    @POST("api/user/saveFeedBack")
    Call<BaseResult<String>> saveFeedBack(@Header(Constants.TOKEN_NAME) String token, @Body FeedBackInput input);


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
    @GET("api/user/getUserInfo")
    Call<BaseResult<UserInfoBean>> getUserInfo(@Header(Constants.TOKEN_NAME) String name);

    /**
     * 修改用户信息
     *
     * @param name
     * @param bean
     * @return
     */
    @PUT("api/user/updateUserInfo")
    Call<BaseResult<UserInfo>> updateUserInfo(@Header(Constants.TOKEN_NAME) String name, @Body UserInfo bean);

    /**
     * 入驻楼宇
     *
     * @param token
     * @param input
     * @return
     */
    @POST("api/company/save/CompanyAdmission")
    Call<BaseResult<String>> intoBuilding(@Header(Constants.TOKEN_NAME) String token, @Body IntoBuildingInput input);

    /**
     * 修改密码
     *
     * @param token
     * @param input
     * @return
     */
    @POST("auth/updatePassword")
    Call<BaseResult<String>> updatePassword(@Header(Constants.TOKEN_NAME) String token, @Body UpdatePasswordInput input);

    /**
     * 上传反馈图片
     *
     * @return
     */
    @Multipart
    @POST("/FileRecord/UploadImgWithId")
    Call<ResponseBody> uploadFile(@Query("FileId") String uid, @PartMap Map<String, RequestBody> params);

    /**
     * 用户查询账单
     *
     * @param token
     * @param page
     * @param size
     * @return
     */
    @GET("api/userBill/select/PageBill")
    Call<BaseResult<PageBillBean>> getPageBill(@Header(Constants.TOKEN_NAME) String token,
                                               @Query("page") int page, @Query("size") int size);

    /**
     * 用户获取通讯录列表
     *
     * @param token
     * @param key
     * @return
     */
    @GET("api/addressList/select/AllAddressList")
    Call<BaseResult<List<ContactBean>>> getAllAddressList(@Header(Constants.TOKEN_NAME) String token, @Query("key") String key);

    /**
     * 用户添加联系人到通讯录
     *
     * @param token
     * @param input
     * @return
     */
    @POST("api/addressList/save/AddressList")
    Call<BaseResult<String>> saveContact(@Header(Constants.TOKEN_NAME) String token, @Body SearchContactResult input);

    /**
     * 通过用户账号获取用户信息
     *
     * @param token
     * @param key
     * @return
     */
    @GET("api/addressList/select/AddressListUser")
    Call<BaseResult<SearchContactResult>> searchContact(@Header(Constants.TOKEN_NAME) String token, @Query("username") String key);

    /**
     * 用户删除联系人
     *
     * @param token
     * @param addressListId
     * @return
     */
    @DELETE("api/addressList/delete/AddressList")
    Call<BaseResult<String>> delelteContact(@Header(Constants.TOKEN_NAME) String token, @Query("addressListId") String addressListId);

    /**
     * 用户修改联系人
     *
     * @param name
     * @param bean
     * @return
     */
    @PUT("api/addressList/update/AddressList")
    Call<BaseResult<String>> updateContact(@Header(Constants.TOKEN_NAME) String name, @Body ContactBean bean);

    /**
     * 用户申请加入公司
     */
    @POST("api/company/save/CompanyUser")
    Call<BaseResult<String>> intoCompany(@Header(Constants.TOKEN_NAME) String token, @Body IntoCompanyInput input);


    /**
     * 上传头像
     *
     * @return
     */
//    @Multipart
//    @POST("/File/AppUploadPhoto")
//    Call<UploadIconResult> uploadIcon(@PartMap Map<String, RequestBody> params);


}
