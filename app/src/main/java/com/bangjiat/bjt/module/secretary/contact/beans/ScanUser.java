package com.bangjiat.bjt.module.secretary.contact.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/21 0021
 */

public class ScanUser {

    /**
     * avatar : string
     * birthday : string
     * company : string
     * ctime : 0
     * email : string
     * idNumber : string
     * job : string
     * locked : 0
     * nickname : string
     * openId : string
     * password : string
     * phone : string
     * realname : string
     * remark : string
     * salt : string
     * sex : 0
     * userId : string
     * username : string
     */

    private String avatar;
    private String birthday;
    private String company;
    private long ctime;
    private String email;
    private String idNumber;
    private String job;
    private int locked;
    private String nickname;
    private String openId;
    private String password;
    private String phone;
    private String realname;
    private String remark;
    private String salt;
    private int sex;
    private String userId;
    private String username;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ScanUser{" +
                "avatar='" + avatar + '\'' +
                ", birthday='" + birthday + '\'' +
                ", company='" + company + '\'' +
                ", ctime=" + ctime +
                ", email='" + email + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", job='" + job + '\'' +
                ", locked=" + locked +
                ", nickname='" + nickname + '\'' +
                ", openId='" + openId + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", realname='" + realname + '\'' +
                ", remark='" + remark + '\'' +
                ", salt='" + salt + '\'' +
                ", sex=" + sex +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
