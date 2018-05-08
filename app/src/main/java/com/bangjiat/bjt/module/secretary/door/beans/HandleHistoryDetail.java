package com.bangjiat.bjt.module.secretary.door.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public class HandleHistoryDetail {
    /**
     * applyRealname : string 帮助申请人真实姓名
     * applyUserId : string 帮助申请人ID
     * applyUsername : string 帮助申请人账号
     * buildId : 0 楼宇ID
     * buildName : string  楼宇名称
     * companyId : 0 公司编号
     * companyName : string 公司名称
     * ctime : 0
     * doorPlate : string 门牌号
     * guardId : 0 门禁申请主表编号
     * guardMainId : 0
     * idNumber : string
     * remake : string
     * type : 0 权限 1.正常 0.申请中 -1.禁止
     * userId : string
     * userRealname : string
     * username : string
     */

    private String applyRealname;
    private String applyUserId;
    private String applyUsername;
    private int buildId;
    private String buildName;
    private int companyId;
    private String companyName;
    private int ctime;
    private String doorPlate;
    private int guardId;
    private int guardMainId;
    private String idNumber;
    private String remake;
    private int type;
    private String userId;
    private String userRealname;
    private String username;

    public String getApplyRealname() {
        return applyRealname;
    }

    public void setApplyRealname(String applyRealname) {
        this.applyRealname = applyRealname;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyUsername() {
        return applyUsername;
    }

    public void setApplyUsername(String applyUsername) {
        this.applyUsername = applyUsername;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCtime() {
        return ctime;
    }

    public void setCtime(int ctime) {
        this.ctime = ctime;
    }

    public String getDoorPlate() {
        return doorPlate;
    }

    public void setDoorPlate(String doorPlate) {
        this.doorPlate = doorPlate;
    }

    public int getGuardId() {
        return guardId;
    }

    public void setGuardId(int guardId) {
        this.guardId = guardId;
    }

    public int getGuardMainId() {
        return guardMainId;
    }

    public void setGuardMainId(int guardMainId) {
        this.guardMainId = guardMainId;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
