package com.bangjiat.bjt.module.secretary.contact.beans;

import java.io.Serializable;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/5 0005
 */

public class SearchContactResult implements Serializable {
    /**
     * slaveNickname : 恍恍惚惚
     * slaveUserId : 115f6598290846baacd0c827b0070095
     * slaveUsername : 17685302679
     */

    private String slaveNickname;
    private String slaveUserId;
    private String slaveUsername;

    public String getSlaveNickname() {
        return slaveNickname;
    }

    public void setSlaveNickname(String slaveNickname) {
        this.slaveNickname = slaveNickname;
    }

    public String getSlaveUserId() {
        return slaveUserId;
    }

    public void setSlaveUserId(String slaveUserId) {
        this.slaveUserId = slaveUserId;
    }

    public String getSlaveUsername() {
        return slaveUsername;
    }

    public void setSlaveUsername(String slaveUsername) {
        this.slaveUsername = slaveUsername;
    }

    public SearchContactResult(String slaveNickname, String slaveUserId, String slaveUsername) {
        this.slaveNickname = slaveNickname;
        this.slaveUserId = slaveUserId;
        this.slaveUsername = slaveUsername;
    }
}
