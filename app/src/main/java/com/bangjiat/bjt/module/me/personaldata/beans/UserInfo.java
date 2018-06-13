package com.bangjiat.bjt.module.me.personaldata.beans;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

import java.io.Serializable;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/5 0005
 */

public class UserInfo extends SugarRecord implements Serializable {
    @Unique
    private String userId;
    private String username;
    @Ignore
    private String password;
    @Ignore
    private String salt;//
    private String phone;
    @Ignore
    private int locked;//状态(0:正常,1:锁定)
    @Ignore
    private long ctime;//创建时间
    private String avatar;//头像json字符串
    private String birthday;//生日
    private int sex;//性别（1、男；2、女）
    private String remark;//备注
    private String realname;//姓名
    @Ignore
    private String openId;//openId,第三方登录使用
    private String nickname;//昵称
    private String idNumber;//昵称
    private String job;//昵称
    private String park;

    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
    }

    public UserInfo(String nickname, int sex, String birthday, String phone) {
        this.phone = phone;
        this.birthday = birthday;
        this.sex = sex;
        this.nickname = nickname;
    }

    public UserInfo() {
    }

    public String getAvatar() {
        return avatar;
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

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", phone='" + phone + '\'' +
                ", locked=" + locked +
                ", ctime=" + ctime +
                ", avatar='" + avatar + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex=" + sex +
                ", remark='" + remark + '\'' +
                ", realname='" + realname + '\'' +
                ", openId='" + openId + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
