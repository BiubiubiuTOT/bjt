package com.bangjiat.bjt.module.secretary.communication.beans;

import java.io.Serializable;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public class EmailBean implements Serializable {
    /**
     * boxType : 0
     * content : string
     * copyer : string
     * copyerId : string
     * ctime : 0
     * emailId : 0
     * readDate : 0
     * receiver : string
     * receiverAvatar : string
     * receiverId : string
     * remark : string
     * resource : string
     * sendDate : 0
     * sendType : 0
     * sender : string
     * senderAvatar : string
     * senderId : string
     * senderUsername : string
     * title : string
     */

    private int boxType; //发件人邮箱类型：1：草稿箱、2：发件箱、3：垃圾箱
    private String content;
    private String copyer;//抄送人
    private String copyerId;//抄送人编号
    private long ctime;
    private int emailId;//邮件编号
    private int recordId;//邮件编号
    private long readDate;
    private String receiver;//收件人
    private String receiverAvatar;//收件人头像
    private String receiverId;//收件人编号(Json字符串)
    private String remark;
    private String resource;
    private long sendDate;
    private int sendType;//发送类型：0：普通、1：急件
    private String sender;//发件人
    private String senderAvatar;//发件人头像
    private String senderId;//发件人编号
    private String senderUsername;//发件人账号
    private String title;//主题
    private int emailStatus;//邮件状态：0：未读、1：已读、2：回复、3：转发、4：全部转发

    public int getBoxType() {
        return boxType;
    }

    public void setBoxType(int boxType) {
        this.boxType = boxType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCopyer() {
        return copyer;
    }

    public void setCopyer(String copyer) {
        this.copyer = copyer;
    }

    public String getCopyerId() {
        return copyerId;
    }

    public void setCopyerId(String copyerId) {
        this.copyerId = copyerId;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public long getReadDate() {
        return readDate;
    }

    public void setReadDate(long readDate) {
        this.readDate = readDate;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverAvatar() {
        return receiverAvatar;
    }

    public void setReceiverAvatar(String receiverAvatar) {
        this.receiverAvatar = receiverAvatar;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public long getSendDate() {
        return sendDate;
    }

    public void setSendDate(long sendDate) {
        this.sendDate = sendDate;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderAvatar() {
        return senderAvatar;
    }

    public void setSenderAvatar(String senderAvatar) {
        this.senderAvatar = senderAvatar;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(int emailStatus) {
        this.emailStatus = emailStatus;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    @Override
    public String toString() {
        return "EmailBean{" +
                "boxType=" + boxType +
                ", content='" + content + '\'' +
                ", copyer='" + copyer + '\'' +
                ", copyerId='" + copyerId + '\'' +
                ", ctime=" + ctime +
                ", emailId=" + emailId +
                ", readDate=" + readDate +
                ", receiver='" + receiver + '\'' +
                ", receiverAvatar='" + receiverAvatar + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", remark='" + remark + '\'' +
                ", resource='" + resource + '\'' +
                ", sendDate=" + sendDate +
                ", sendType=" + sendType +
                ", sender='" + sender + '\'' +
                ", senderAvatar='" + senderAvatar + '\'' +
                ", senderId='" + senderId + '\'' +
                ", senderUsername='" + senderUsername + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
