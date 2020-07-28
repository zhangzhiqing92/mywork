package com.work.order.response;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ResponseBody
 * @Description TODO 统一API接口返回数据结构
 * @Author guozishen
 * @Date 2018/5/16 14:19
 * @Version 1.0
 **/
public class ResponseBody<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**时间**/
    private Date timeStamp = new Date();
    /**
     * Status的内容与HTTP状态码内容相同，这个字段的存在使得错误信息自包含，
     * 客户端只需要解析HTTP响应的body部分，就可以获取所有跟这次出错相关的信息
     **/
    private int status = 200;
    /**接口返回的数据**/
    private T data;
    /**数字签名**/
    private String digitalSignature = "";

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
