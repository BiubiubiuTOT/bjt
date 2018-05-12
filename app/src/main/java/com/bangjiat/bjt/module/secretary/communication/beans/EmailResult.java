package com.bangjiat.bjt.module.secretary.communication.beans;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public class EmailResult {

    /**
     * total : 0
     * size : 10
     * pages : 0
     * current : 1
     * records : [{"emailId":52,"senderId":"6c1503d6f55942648a1184bb4f4f5612","receiver":"王瑞","receiverId":"6c1503d6f55942648a1184bb4f4f5612","title":"哈哈哈","content":"嘟嘟嘟","sendType":0,"sendDate":1526112420505,"readDate":0,"boxType":2,"ctime":1526112420505,"resource":"nullhttp://j4cf.oss-cn-shenzhen.aliyuncs.com/img/1526112419635_Rk5ssgdkm6nR_Wi7XnWE-zx_egk.cnt.gif|http://j4cf.oss-cn-shenzhen.aliyuncs.com/img/1526112419645_xItdrh3onkjxMik_IAU4IbVX6qA.cnt.gif|http://j4cf.oss-cn-shenzhen.aliyuncs.com/img/1526112419635_c3yki-Cqim5wTk7dq9VOzx--lYo.cnt.gif"},{"emailId":51,"senderId":"6c1503d6f55942648a1184bb4f4f5612","receiver":"王瑞","receiverId":"6c1503d6f55942648a1184bb4f4f5612","title":"哈哈哈","content":"嘟嘟嘟","sendType":0,"sendDate":1526112420059,"readDate":0,"boxType":2,"ctime":1526112420059,"resource":"nullhttp://j4cf.oss-cn-shenzhen.aliyuncs.com/img/1526112419635_Rk5ssgdkm6nR_Wi7XnWE-zx_egk.cnt.gif|http://j4cf.oss-cn-shenzhen.aliyuncs.com/img/1526112419645_xItdrh3onkjxMik_IAU4IbVX6qA.cnt.gif|http://j4cf.oss-cn-shenzhen.aliyuncs.com/img/1526112419635_c3yki-Cqim5wTk7dq9VOzx--lYo.cnt.gif"}]
     */

    private int total;
    private int size;
    private int pages;
    private int current;
    private List<EmailBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public List<EmailBean> getRecords() {
        return records;
    }

    public void setRecords(List<EmailBean> records) {
        this.records = records;
    }

}
