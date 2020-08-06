package com.work.order_boot.order.response;

import java.io.Serializable;
import java.util.UUID;

/**
 * @ClassName ErrorMsgBody
 * @Description TODO 接口发生异常后的错误信息消息体
 * @Author guozishen
 * @Date 2018/5/18 16:18
 * @Version 1.0
 **/
public class ErrorMsg implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 自定义错误码。自定义错误码的长度和个数都可以自己定义，这样就突破了HTTP状态码的个数限制。
     * 例子中的错误码是40483，其中404代表了请求的资源不存在，而83则制定了这次出错，具体是哪一种资源不存在。
     **/
    private String code;
    /**
     * 用户可理解的错误信息，应当根据用户的locale信息返回对应语言的版本。这个信息意在返回给使用客户端的用户阅读，
     * 不应该包含任何技术信息。有了这个字段，客户端的开发者在出错时，能够展示恰当的信息给最终用户。
     **/
    private String message;
    /**
     * 该出错的详细技术信息，提供给客户端的开发者阅读。可以包含Exception的信息、StackTrace，或者其它有用的技术信息。
     **/
    private String developMessage;
    /**
     * 给出一个URL，客户端开发站访问这个URL可以看到更详细的关于该种出错信息的描述。
     * 在该URL展示的网页中，可以包含该出错信息的定义，产生原因，解决办法等等。
     **/
    private String moreInfo;
    /**
     * 请求ID，服务为每一个请求唯一生成一个请求ID，当客户端开发者无法自助解决问题时，可以联络服务开发者，同时提供该请求ID。
     * 一个好的服务，服务开发者应当可以根据此ID，定位到该次请求的所有相关log，进而定位问题，解决问题。
     **/
    private String requestId = UUID.randomUUID().toString().replaceAll("-", "");

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDevelopMessage() {
        return developMessage;
    }

    public void setDevelopMessage(String developMessage) {
        this.developMessage = developMessage;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
