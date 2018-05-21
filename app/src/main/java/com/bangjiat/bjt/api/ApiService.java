package com.bangjiat.bjt.api;

import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.module.home.company.beans.CompanyDetailResult;
import com.bangjiat.bjt.module.home.company.beans.CompanyInput;
import com.bangjiat.bjt.module.home.company.beans.IntoCompanyInput;
import com.bangjiat.bjt.module.home.notice.beans.NoticeBean;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaHistoryResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaTotalResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.InDakaInput;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.OutDakaInput;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.RuleInput;
import com.bangjiat.bjt.module.home.work.leave.beans.CompanyLeaveResult;
import com.bangjiat.bjt.module.home.work.leave.beans.DealLeaveInput;
import com.bangjiat.bjt.module.home.work.leave.beans.LeaveBean;
import com.bangjiat.bjt.module.home.work.permission.beans.UpdateAdminInput;
import com.bangjiat.bjt.module.main.account.beans.LoginInput;
import com.bangjiat.bjt.module.main.account.beans.RecoveredPasswordInput;
import com.bangjiat.bjt.module.main.account.beans.RegisterInput;
import com.bangjiat.bjt.module.main.account.beans.ValidateCodeInput;
import com.bangjiat.bjt.module.me.bill.beans.PageBillBean;
import com.bangjiat.bjt.module.me.feedback.beans.FeedBackInput;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfoBean;
import com.bangjiat.bjt.module.me.setting.beans.UpdatePasswordInput;
import com.bangjiat.bjt.module.park.apply.beans.DealParkApplyInput;
import com.bangjiat.bjt.module.park.apply.beans.ParkApplyHistoryResult;
import com.bangjiat.bjt.module.park.apply.beans.ParkApplyInput;
import com.bangjiat.bjt.module.park.apply.beans.ParkingResult;
import com.bangjiat.bjt.module.park.car.beans.CarBean;
import com.bangjiat.bjt.module.park.pay.beans.ParkingDetail;
import com.bangjiat.bjt.module.park.pay.beans.PayBean;
import com.bangjiat.bjt.module.park.pay.beans.PayInput;
import com.bangjiat.bjt.module.park.pay.beans.PayListResult;
import com.bangjiat.bjt.module.secretary.communication.beans.EmailBean;
import com.bangjiat.bjt.module.secretary.communication.beans.EmailResult;
import com.bangjiat.bjt.module.secretary.contact.beans.BuildUserInfo;
import com.bangjiat.bjt.module.secretary.contact.beans.ContactBean;
import com.bangjiat.bjt.module.secretary.contact.beans.ScanUser;
import com.bangjiat.bjt.module.secretary.contact.beans.SearchContactResult;
import com.bangjiat.bjt.module.secretary.door.beans.ApplyHistoryBean;
import com.bangjiat.bjt.module.secretary.door.beans.ApprovalServiceInput;
import com.bangjiat.bjt.module.secretary.door.beans.DoorApplyDetailResult;
import com.bangjiat.bjt.module.secretary.door.beans.IntoBuildingInput;
import com.bangjiat.bjt.module.secretary.door.beans.IsIntoBuildingResult;
import com.bangjiat.bjt.module.secretary.service.beans.ApprovalDoorInput;
import com.bangjiat.bjt.module.secretary.service.beans.BuildingAdminListResult;
import com.bangjiat.bjt.module.secretary.service.beans.NewApplyInput;
import com.bangjiat.bjt.module.secretary.service.beans.ServiceApplyHistoryResult;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Ligh on 2017/8/22 17:37
 * 邮箱:1256144200@qq.com
 * 打开电脑我们如此接近,关上电脑我们那么遥远
 * 200 成功请求
 * http://192.168.0.118:1004/swagger-ui.html#/
 * www.bangjiat.com
 * www.bangjiat.com/oss/
 */

public interface ApiService {
    /**
     * 1
     * 注册
     *
     * @param
     * @return
     */
    @POST("auth/register")
    Call<BaseResult<String>> register(@Body RegisterInput input);

    /**
     * 2
     * 获取验证码
     *
     * @return
     */
    @GET("auth/getRegisterCode")
    Call<BaseResult<String>> getRegisterCode(@Query("phone") String phone,
                                             @Query("codeType") int type);

    /**
     * 3
     * 登录
     *
     * @param
     * @return
     */
    @POST("auth/login")
    Call<BaseResult<String>> login(@Body LoginInput loginInput);

    /**
     * 4
     * 获取公告
     *
     * @param token
     * @return
     */
    @GET("api/sys/getAllNotice")
    Call<BaseResult<NoticeBean>> getNotice(@Header(Constants.TOKEN_NAME) String token);

    /**
     * 5
     * 保存反馈
     *
     * @return
     */
    @POST("api/user/saveFeedBack")
    Call<BaseResult<String>> saveFeedBack(@Header(Constants.TOKEN_NAME) String token,
                                          @Body FeedBackInput input);


    /**
     * 6
     * 新建公司
     *
     * @param token
     * @param input
     * @return
     */
    @POST("api/company/add/Company")
    Call<BaseResult<String>> addCompany(@Header(Constants.TOKEN_NAME) String token,
                                        @Body CompanyInput input);

    /**
     * 7
     * 获取用户信息
     *
     * @param name
     * @return
     */
    @GET("api/user/getUserInfo")
    Call<BaseResult<UserInfoBean>> getUserInfo(@Header(Constants.TOKEN_NAME) String name);

    /**
     * 8
     * 修改用户信息
     *
     * @param name
     * @param bean
     * @return
     */
    @PUT("api/user/updateUserInfo")
    Call<BaseResult<UserInfo>> updateUserInfo(@Header(Constants.TOKEN_NAME) String name,
                                              @Body UserInfo bean);

    /**
     * 9
     * 入驻楼宇
     *
     * @param token
     * @param input
     * @return
     */
    @POST("api/company/save/CompanyAdmission")
    Call<BaseResult<String>> intoBuilding(@Header(Constants.TOKEN_NAME) String token,
                                          @Body IntoBuildingInput input);

    /**
     * 10
     * 修改密码
     *
     * @param token
     * @param input
     * @return
     */
    @POST("auth/updatePassword")
    Call<BaseResult<String>> updatePassword(@Header(Constants.TOKEN_NAME) String token,
                                            @Body UpdatePasswordInput input);


    /**
     * 11
     * 用户查询账单
     *
     * @param token
     * @param page
     * @param size
     * @return
     */
    @GET("api/userBill/select/PageBill")
    Call<BaseResult<PageBillBean>> getPageBill(@Header(Constants.TOKEN_NAME) String token,
                                               @Query("page") int page,
                                               @Query("size") int size);

    /**
     * 12
     * 用户获取通讯录列表
     *
     * @param token
     * @param key
     * @return
     */
    @GET("api/addressList/select/AllAddressList")
    Call<BaseResult<List<ContactBean>>> getAllAddressList(@Header(Constants.TOKEN_NAME) String token,
                                                          @Query("key") String key);

    /**
     * 13
     * 用户添加联系人到通讯录
     *
     * @param token
     * @param input
     * @return
     */
    @POST("api/addressList/save/AddressList")
    Call<BaseResult<String>> saveContact(@Header(Constants.TOKEN_NAME) String token,
                                         @Body SearchContactResult input);

    /**
     * 14
     * 通过用户账号获取用户信息
     *
     * @param token
     * @param key
     * @return
     */
    @GET("api/addressList/select/AddressListUser")
    Call<BaseResult<SearchContactResult>> searchContact(@Header(Constants.TOKEN_NAME) String token,
                                                        @Query("username") String key);

    /**
     * 15
     * 用户删除联系人
     *
     * @param token
     * @param addressListId
     * @return
     */
    @DELETE("api/addressList/delete/AddressList")
    Call<BaseResult<String>> delelteContact(@Header(Constants.TOKEN_NAME) String token,
                                            @Query("addressListId") String addressListId);

    /**
     * 16
     * 用户修改联系人
     *
     * @param name
     * @param bean
     * @return
     */
    @PUT("api/addressList/update/AddressList")
    Call<BaseResult<String>> updateContact(@Header(Constants.TOKEN_NAME) String name,
                                           @Body ContactBean bean);

    /**
     * 17
     * 用户申请加入公司
     */
    @POST("api/company/save/CompanyUser")
    Call<BaseResult<String>> intoCompany(@Header(Constants.TOKEN_NAME) String token,
                                         @Body IntoCompanyInput input);

    /**
     * 18
     * 添加打卡规则
     */
    @POST("api/companyClockRule/save/CompanyClockRule")
    Call<BaseResult<String>> saveCompanyCLockRule(@Header(Constants.TOKEN_NAME) String token,
                                                  @Body RuleInput input);

    /**
     * 19
     * 修改打卡规则
     */
    @PUT("api/companyClockRule/update/CompanyClockRule")
    Call<BaseResult<String>> updateCompanyCLockRule(@Header(Constants.TOKEN_NAME) String token,
                                                    @Body RuleInput input);

    /**
     * 20
     * 查询打卡规则
     */
    @GET("api/companyClockRule/select/CompanyClockRule")
    Call<BaseResult<RuleInput>> selectCompanyClockRule(@Header(Constants.TOKEN_NAME) String token);

    /**
     * 21
     * 上班打卡
     */
    @POST("api/companyClock/save/CompanyClock")
    Call<BaseResult<String>> saveInDaka(@Header(Constants.TOKEN_NAME) String token,
                                        @Body InDakaInput inDakaInput);


    /**
     * 22
     * 下班打卡
     */
    @PUT("api/companyClock/update/CompanyClock")
    Call<BaseResult<String>> saveOutDaka(@Header(Constants.TOKEN_NAME) String token,
                                         @Body OutDakaInput input);

    /**
     * 23
     * 获取个人打卡记录
     */
    @GET("api/companyClock/select/CompanyClockList")
    Call<BaseResult<List<DakaHistoryResult>>> getDaka(@Header(Constants.TOKEN_NAME) String token,
                                                      @Query("beginTime") String begin,
                                                      @Query("endTime") String end);

    /**
     * 24
     * 获取门禁申请记录
     */
    @GET("api/guard/select/GuardMainPage")
    Call<BaseResult<ApplyHistoryBean>> getDoorApplyHistory(@Header(Constants.TOKEN_NAME) String token,
                                                           @Query("page") int page,
                                                           @Query("size") int size);

    /**
     * 25
     * 公司管理员提交门禁申请
     */
    @POST("api/guard/save/GuardMain")
    Call<BaseResult<String>> addDoorApply(@Header(Constants.TOKEN_NAME) String token,
                                          @Body String[] strings);

    /**
     * 26
     * 查询员工列表
     * 查询类型：1表示查询所有员工、2表示没有开门权限的员工列表、3表示该公司的所有管理员、4、该公司所有非管理员
     */
    @GET("api/company/select/PageCompanyUser")
    Call<BaseResult<WorkersResult>> getCompanyUser(@Header(Constants.TOKEN_NAME) String token, @Query("page") int page,
                                                   @Query("size") int size,
                                                   @Query("type") int type);

    /**
     * 27
     * 获取访客记录
     */
    @GET("api/visitor/select/BuildVisitorPage")
    Call<BaseResult> getVisitorHistory(@Header(Constants.TOKEN_NAME) String token,
                                       @Query("page") int page,
                                       @Query("size") int size);

    /**
     * 28
     * 处理访客申请
     */
    @PUT("api/visitor/update/BuildVisitor")
    Call<BaseResult> dealVisitorApply(@Header(Constants.TOKEN_NAME) String token);

    /**
     * 29
     * 删除公司员工
     */
    @DELETE("api/company/delete/CompanyUser")
    Call<BaseResult<String>> deleteCompanyUser(@Header(Constants.TOKEN_NAME) String token,
                                               @Query("userId") String userId);

    /**
     * 30
     * 修改员工信息
     */
    @PUT("api/company/update/CompanyUser")
    Call<BaseResult<String>> updateCompanyUser(@Header(Constants.TOKEN_NAME) String token,
                                               @Body WorkersResult.RecordsBean bean);

    /**
     * 31
     * 公司管理员添加员工
     */
    @POST("api/company/save/CompanyUserByAdmin")
    Call<BaseResult> addCompanyUser(@Header(Constants.TOKEN_NAME) String token,
                                    @Body WorkersResult.RecordsBean bean);

    /**
     * 32
     * 用户提交服务申请
     */
    @POST("api/buildApproval/save/BuildApproval")
    Call<BaseResult> addNewServiceApply(@Header(Constants.TOKEN_NAME) String token,
                                        @Body NewApplyInput input);

    /**
     * 33
     * 获取服务申请记录
     */
    @GET("api/buildApproval/select/BuildApprovalPage")
    Call<BaseResult<ServiceApplyHistoryResult>> getServiceApplyHistory(@Header(Constants.TOKEN_NAME) String token,
                                                                       @Query("page") int page,
                                                                       @Query("size") int size);

    /**
     * 34
     * 查询公司入驻楼宇信息
     */
    @GET("api/company/select/CompanyAdmission")
    Call<BaseResult<IsIntoBuildingResult>> isCompanyIntoBuilding(@Header(Constants.TOKEN_NAME) String token);

    /**
     * 35
     * 获取楼宇管理员列表
     */
    @GET("api/buildApproval/select/BuildUserList")
    Call<BaseResult<List<BuildingAdminListResult>>> getBuildingAdminList(@Header(Constants.TOKEN_NAME) String token);

    /**
     * 36
     * 上传头像
     *
     * @return
     */
    @Multipart
    @POST("oss/upImg")
    Call<BaseResult<String>> uploadImage(@Part MultipartBody.Part partList);

    /**
     * 37
     * 获取门禁申请明细
     */
    @GET("api/guard/select/GuardList")
    Call<BaseResult<List<DoorApplyDetailResult>>> getDoorApplyDetail(@Header(Constants.TOKEN_NAME) String token,
                                                                     @Query("guardMainId") String guardMainId);

    /**
     * 38
     * 找回密码验证
     */
    @POST("auth/validateCode")
    Call<BaseResult<String>> validateCode(@Body ValidateCodeInput input);

    /**
     * 39
     * 找回密码
     */
    @POST("auth/getBackPassword")
    Call<BaseResult<String>> updatePassword(@Body RecoveredPasswordInput input);

    /**
     * 40
     * 查询个人车辆信息列表
     */
    @GET("api/carparkCar/select/CarparkCarList")
    Call<BaseResult<List<CarBean>>> getCarList(@Header(Constants.TOKEN_NAME) String token);

    /**
     * 41
     * 新增车辆
     */
    @POST("api/carparkCar/save/CarparkCar")
    Call<BaseResult> addCar(@Header(Constants.TOKEN_NAME) String token, @Body CarBean bean);

    /**
     * 42
     * 查询员工车辆信息列表
     */
    @GET("api/carparkApply/select/CarparkCarList")
    Call<BaseResult<List<CarBean>>> getWorkersCarList(@Header(Constants.TOKEN_NAME) String token);

    /**
     * 43
     * 公司管理员提交停车申请
     */
    @POST("api/carparkApply/save/CarparkApply")
    Call<BaseResult<String>> addParkApply(@Header(Constants.TOKEN_NAME) String token,
                                          @Body ParkApplyInput input);

    /**
     * 44
     * 获取停车场列表(停车申请时调用)
     */
    @GET("api/carparkApply/select/CarparkSpacePage")
    Call<BaseResult<ParkingResult>> getParkSpace(@Header(Constants.TOKEN_NAME) String token,
                                                 @Query("page") int page,
                                                 @Query("size") int size, @Query("key") String key);

    /**
     * 45
     * 根据ID查询公司信息
     */
    @GET("api/company/select/Company")
    Call<BaseResult<CompanyDetailResult>> getCompanyDetail(@Header(Constants.TOKEN_NAME) String token,
                                                           @Query("companyId") String companyId);

    /**
     * 46
     * 用户发邮件
     */
    @POST("api/emailBox/save/SendEmailBox")
    Call<BaseResult<String>> sendEmail(@Header(Constants.TOKEN_NAME) String token, @Body EmailBean bean);

    /**
     * 47
     * 搜索发件箱邮件列表
     */
    @GET("api/emailBox/select/PageEmailBox")
    Call<BaseResult<EmailResult>> getOutBoxList(@Header(Constants.TOKEN_NAME) String token,
                                                @Query("key") String key, @Query("page") int page, @Query("size") int size);

    /**
     * 48
     * 搜索收件箱邮件列表
     */
    @GET("api/emailBox/select/PageEmailBoxRecord")
    Call<BaseResult<EmailResult>> getInBoxList(@Header(Constants.TOKEN_NAME) String token,
                                               @Query("key") String key, @Query("page") int page, @Query("size") int size);

    /**
     * 49
     * 删除发件箱邮件
     */
    @HTTP(method = "DELETE", path = "api/emailBox/delete/EmailBox", hasBody = true)
    Call<BaseResult<String>> deleteOutBox(@Header(Constants.TOKEN_NAME) String token, @Body String[] strings);

    /**
     * 50
     * 删除收件箱邮件
     */
    @HTTP(method = "DELETE", path = "api/emailBox/delete/EmailBoxRecord", hasBody = true)
    Call<BaseResult<String>> deleteInbox(@Header(Constants.TOKEN_NAME) String token,
                                         @Body String[] strings);

    /**
     * 51
     * 查看发件箱邮件详情
     */
    @GET("api/emailBox/select/EmailBox")
    Call<BaseResult<EmailBean>> getOutBoxDetail(@Header(Constants.TOKEN_NAME) String token,
                                                @Query("emailId") String id);

    /**
     * 52
     * 查看收件箱邮件详情
     */
    @GET("api/emailBox/select/EmailBoxRecord")
    Call<BaseResult<EmailBean>> getInBoxDetail(@Header(Constants.TOKEN_NAME) String token,
                                               @Query("emailId") String id);

    /**
     * 53
     * 标记邮件
     */
    @PUT("api/emailBox/update/EmailBoxRecordList")
    Call<BaseResult<String>> markEmails(@Header(Constants.TOKEN_NAME) String token,
                                        @Body String[] strings);

    /**
     * 54
     * 获取收件箱未读邮件数量
     */
    @GET("api/emailBox/select/EmailBoxRecordCount")
    Call<BaseResult<String>> getUnReadCount(@Header(Constants.TOKEN_NAME) String token);

    /**
     * 55
     * 停车缴费信息添加
     */
    @POST("api/carparkPayment/save/CarparkPayment")
    Call<BaseResult<String>> addPayInfo(@Header(Constants.TOKEN_NAME) String token,
                                        @Body PayBean bean);

    /**
     * 56
     * 停车缴费支付接口
     */
    @POST("api/carparkPayment/pay/CarparkPayment")
    Call<BaseResult<String>> pay(@Header(Constants.TOKEN_NAME) String token, @Body PayInput input);

    /**
     * 57
     * 获取停车缴费列表
     */
    @GET("api/carparkApplyDetail/select/ApplyDetailList")
    Call<BaseResult<List<PayListResult>>> getPayList(@Header(Constants.TOKEN_NAME) String token);

    /**
     * 58
     * 获取停车场详情
     */
    @GET("api/carparkPayment/carpark/select/CarparkSpace")
    Call<BaseResult<ParkingDetail>> getParkingDetail(@Header(Constants.TOKEN_NAME) String token,
                                                     @Query("spaceId") int spaceId);

    /**
     * 59
     * 停车场管理员审批停车申请
     */
    @PUT("api/carparkApply/update/CarparkApply")
    Call<BaseResult<String>> dealParkApply(@Header(Constants.TOKEN_NAME) String token,
                                           @Body DealParkApplyInput input);

    /**
     * 60
     * 提交请假申请
     */
    @POST("api/companyLeave/save/CompanyLeave")
    Call<BaseResult> addCompanyLeave(@Header(Constants.TOKEN_NAME) String token,
                                     @Body LeaveBean bean);

    /**
     * 61
     * 待审批事项和审批记录（status为1则是待审批事项，否则查询所有记录）
     */
    @GET("api/companyLeave/select/CompanyLeavePage")
//1、待审批事项 2、查询所有记录
    Call<BaseResult<CompanyLeaveResult>> getCompanyLeave(@Header(Constants.TOKEN_NAME) String token,
                                                         @Query("status") int status,
                                                         @Query("page") int page,
                                                         @Query("size") int size);

    /**
     * 62
     * 用户查询申请记录
     */
    @GET("api/companyLeave/select/LeaveSelfPage")
//可不传：1、待审批、2、通过、3、未通过
    Call<BaseResult<CompanyLeaveResult>> getSelefLeve(@Header(Constants.TOKEN_NAME) String token,
                                                      @Query("status") int status,
                                                      @Query("page") int page,
                                                      @Query("size") int size);

    /**
     * 63
     * 申请审批:1表示同意，2表示拒绝，3表示转批
     */
    @PUT("api/companyLeave/update/CompanyLeave")
    Call<BaseResult<String>> dealLeave(@Header(Constants.TOKEN_NAME) String token,
                                       @Body DealLeaveInput input);

    /**
     * 64
     * 查询停车申请记录列表
     * ,@Query("spaceId") int spaceId
     */
    @GET("api/carparkApply/select/CarparkApplyPage")
    Call<BaseResult<ParkApplyHistoryResult>> getParkApplyHistory(@Header(Constants.TOKEN_NAME) String token,
                                                                 @Query("page") int page,
                                                                 @Query("size") int size);

    /**
     * 65
     * 给员工添加管理员权限
     */
    @POST("api/company/save/CompanyAdminUser")
    Call<BaseResult<String>> addAdmin(@Header(Constants.TOKEN_NAME) String token,
                                      @Body String[] strings);

    /**
     * 66
     * 删除公司管理员权限
     */
    @HTTP(method = "DELETE", path = "api/company/delete/CompanyAdminUser", hasBody = true)
    Call<BaseResult<String>> deleteAdmin(@Header(Constants.TOKEN_NAME) String token,
                                         @Body String[] strings);

    /**
     * 67
     * 工作台管理员权限转交
     */
    @PUT("api/company/update/CompanyAdminUser")
    Call<BaseResult<String>> updateAdmin(@Header(Constants.TOKEN_NAME) String token,
                                         @Body UpdateAdminInput userId);

    /**
     * 68
     * 获取所有员工打卡记录
     */
    @GET("api/companyClock/select/CompanyClockAllList")
    Call<BaseResult<List<DakaHistoryResult>>> getAllColckList(@Header(Constants.TOKEN_NAME) String token,
                                                              @Query("beginTime") long bg,
                                                              @Query("endTime") long end);

    /**
     * 69
     * 获取个人打卡记录
     */
    @GET("api/companyClock/select/CompanyClockList")
    Call<BaseResult<List<DakaHistoryResult>>> getClockList(@Header(Constants.TOKEN_NAME) String token,
                                                           @Query("beginTime") long bg, @Query("endTime") long end);

    /**
     * 70
     * 获取单个员工的打卡记录
     */
    @GET("api/companyClock/select/CompanyUserClockList")
    Call<BaseResult<List<DakaHistoryResult>>> getUserClockList(@Header(Constants.TOKEN_NAME) String token,
                                                               @Query("beginTime") long bg,
                                                               @Query("endTime") long end,
                                                               @Query("userId") String userId);

    /**
     * 71
     * 获取打卡记录统计信息
     */
    @GET("api/companyClock/select/selectCompanyClockTotal")
    Call<BaseResult<DakaTotalResult>> getClockTotal(@Header(Constants.TOKEN_NAME) String token,
                                                    @Query("beginTime") long bg,
                                                    @Query("endTime") long end);

    /**
     * 72
     * 获取员工自己的打卡统计信息
     */
    @GET("api/companyClock/select/UserClockTotalList")
    Call<BaseResult<DakaTotalResult>> getUserClockTotal(@Header(Constants.TOKEN_NAME) String token,
                                                        @Query("beginTime") long bg,
                                                        @Query("endTime") long end,
                                                        @Query("userId") String userId);

    /**
     * 73
     * 员工申请退出公司
     */
    @DELETE("api/company/delete/CompanyUserSelf")
    Call<BaseResult<String>> exitCompany(@Header(Constants.TOKEN_NAME) String token);

    /**
     * 74
     * 修改公司信息
     */
    @PUT("api/company/update/Company")
    Call<BaseResult<String>> updateCpompany(@Header(Constants.TOKEN_NAME) String token,
                                            @Body CompanyDetailResult result);

    /**
     * 75
     * <p>
     * 服务申请审批
     */
    @PUT("admin/buildApproval/update/BuildApproval")
    Call<BaseResult<String>> approvalService(@Header(Constants.TOKEN_NAME) String token,
                                             @Header(Constants.TOKEN_NAME_SPECIAL) int id,
                                             @Body ApprovalServiceInput input);

    /**
     * 76
     * <p>
     * 门禁申请审批
     */
    @PUT("admin/guard/update/GuardMain")
    Call<BaseResult<String>> approvalDoor(@Header(Constants.TOKEN_NAME) String token,
                                          @Header(Constants.TOKEN_NAME_SPECIAL) int id,
                                          @Body ApprovalDoorInput bean);

    /**
     * 77
     * <p>
     * 楼宇管理员获取门禁申请记录
     */
    @GET("admin/guard/select/GuardMainPage")
    Call<BaseResult<ApplyHistoryBean>> getAdminDoorApplyHistory(@Header(Constants.TOKEN_NAME) String token,
                                                                @Header(Constants.TOKEN_NAME_SPECIAL) int id,
                                                                @Query("page") int page,
                                                                @Query("size") int size);

    /**
     * 78
     * <p>
     * 楼宇管理员获取服务申请记录
     */
    @GET("admin/buildApproval/select/BuildApprovalPage")
    Call<BaseResult<ServiceApplyHistoryResult>> getAdminServiceApplyHistory(@Header(Constants.TOKEN_NAME) String token,
                                                                            @Header(Constants.TOKEN_NAME_SPECIAL) int id,
                                                                            @Query("page") int page,
                                                                            @Query("size") int size,
                                                                            @Query("status") int status);

    /**
     * 79
     * <p>
     * 楼宇管理员获取门禁申请明细
     */
    @GET("admin/guard/select/GuardList")
    Call<BaseResult<List<DoorApplyDetailResult>>> getAdminDoorApplyDetail(@Header(Constants.TOKEN_NAME) String token,
                                                                          @Header(Constants.TOKEN_NAME_SPECIAL) int id,
                                                                          @Query("guardMainId") String guardMainId);

    /**
     * 80
     * <p>
     * 公司管理员获取楼宇管理者联系方式
     */
    @GET("api/company/select/BuildUserInfo")
    Call<BaseResult<BuildUserInfo>> getBuildUserInfo(@Header(Constants.TOKEN_NAME) String token);

    /**
     * 81
     * <p>
     * 扫码添加员工时获取用户信息
     */
    @GET("api/company/select/UserUser")
    Call<BaseResult<ScanUser>> getContactByScan(@Header(Constants.TOKEN_NAME) String token, @Query("username") String username);
}
