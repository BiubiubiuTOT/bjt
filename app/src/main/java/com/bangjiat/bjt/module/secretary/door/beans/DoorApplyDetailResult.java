package com.bangjiat.bjt.module.secretary.door.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public class DoorApplyDetailResult {
    /**
     * applyRealname : string
     * applyUserId : string
     * applyUsername : string
     * buildId : 0
     * buildName : string
     * companyId : 0
     * companyName : string
     * ctime : 0
     * doorPlate : string
     * guardId : 0
     * guardMainId : 0
     * idNumber : string
     * remake : string
     * type : 0
     * userId : string
     * userRealname : string
     * username : string
     */

    private String applyRealname;
    private String applyUserId;
    private String applyUsername;
    private String buildId;
    private String buildName;
    private String companyId;
    private String companyName;
    private String ctime;
    private String doorPlate;
    private String guardId;
    private String guardMainId;
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

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getDoorPlate() {
        return doorPlate;
    }

    public void setDoorPlate(String doorPlate) {
        this.doorPlate = doorPlate;
    }

    public String getGuardId() {
        return guardId;
    }

    public void setGuardId(String guardId) {
        this.guardId = guardId;
    }

    public String getGuardMainId() {
        return guardMainId;
    }

    public void setGuardMainId(String guardMainId) {
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

    @Override
    public String toString() {
        return "DoorApplyDetailResult{" +
                "applyRealname='" + applyRealname + '\'' +
                ", applyUserId='" + applyUserId + '\'' +
                ", applyUsername='" + applyUsername + '\'' +
                ", buildId='" + buildId + '\'' +
                ", buildName='" + buildName + '\'' +
                ", companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", ctime='" + ctime + '\'' +
                ", doorPlate='" + doorPlate + '\'' +
                ", guardId='" + guardId + '\'' +
                ", guardMainId='" + guardMainId + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", remake='" + remake + '\'' +
                ", type=" + type +
                ", userId='" + userId + '\'' +
                ", userRealname='" + userRealname + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
