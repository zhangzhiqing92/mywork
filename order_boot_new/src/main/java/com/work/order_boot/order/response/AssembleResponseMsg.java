package com.work.order_boot.order.response;

import org.springframework.http.HttpStatus;

/**
 * @ClassName AssembleResponseMsg
 * @Description TODO 封装ResponseBody消息内容
 * @Author guozishen
 * @Date 2018/5/16 14:59
 * @Version 1.0
 **/
public class AssembleResponseMsg {
    /**
    * @Author guozishen
    * @Description 接口执行成功的返回内容
    * @Date 2018/5/16 15:09
    * @Param [obj, digitalSignature]
    * @retrun
    **/
    public <T>ResponseBody success(T obj,String digitalSignature){
        ResponseBody rb = new ResponseBody();
        InfoMsg info = new InfoMsg();
        rb.setStatus(HttpStatus.OK.value());
        rb.setDigitalSignature(digitalSignature);
        rb.setData(obj);
        rb.setInfo(info);
        return rb;
    }
    /**
    * @Author guozishen
    * @Description 失败/异常消息
    * @Date 2018/5/16 15:16
    * @Param [status, message, requestId, developMessage]
    * @retrun
    **/
    public <T>ResponseBody failure(int status,String errorCode,String message,String requestId,String developMessage){
        ResponseBody<T> rb = new ResponseBody<T>();
        InfoMsg info = new InfoMsg();
        rb.setStatus(status);

        info.setCode(errorCode);
        info.setMessage(message);
        if(null != requestId && "" != requestId){
            info.setRequestId(requestId);
        }
        info.setDevelopMessage(developMessage);
        rb.setInfo(info);
        return rb;
    }
}
