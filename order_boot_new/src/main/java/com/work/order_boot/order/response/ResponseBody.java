package com.work.order_boot.order.response;

import java.io.Serializable;
import java.util.Date;

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
    /**错误消息内容**/
    //private ErrorMsg error;
    /**返回消息内容**/
    private InfoMsg info;

    public InfoMsg getInfo() {
        return info;
    }

    public void setInfo(InfoMsg info) {
        this.info = info;
    }

    /*public ErrorMsg getError() {
        return error;
    }*/

   /* public void setError(ErrorMsg error) {
        this.error = error;
    }*/

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

    public String getDigitalSignature() {
        return digitalSignature;
    }

    public void setDigitalSignature(String digitalSignature) {
        this.digitalSignature = digitalSignature;
    }
}
