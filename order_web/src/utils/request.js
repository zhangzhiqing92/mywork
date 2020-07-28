import axios from 'axios'
import md5 from 'js-md5'
import { JSEncrypt } from 'jsencrypt'
import {
  Base64
} from 'js-base64';
import {
  Message,
  Notification
} from 'element-ui'

// 创建axios实例
let protocol = window.location.protocol; //协议
let host = window.location.host; //主机
let url = process.env.BASE_API
if (process.env.NODE_ENV == "production") {
  url = protocol + "//" + host + "/koflms_sev/"
}
let urls= "http://192.168.18.58:9988/order_boot/"
const service = axios.create({
  baseURL: urls, // api的base_url
  timeout: 1200000, // 请求超时时间 5s
})
// service.defaults.retry = 3;
// service.defaults.retryDelay = 1000;
// request拦截器
service.interceptors.request.use(config => {
  //console.log(config)
  let data = config.data;
  if (!data) data = {};
  //console.log("data:");
  //console.log(JSON.stringify(data));
  let dataSting = JSON.stringify(data);
  let dataMd5 = md5(dataSting);
  //console.log("dataMd5:");
  //console.log(dataMd5);
  const encrypt = new window.JSEncrypt();
  encrypt.setPublicKey(`-----BEGIN PUBLIC KEY-----
MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCDbl0K9X56HAierTyfXQ6fvzg
g4bzDTZ5yig+2M1WMLvd+uEv25XZZFR3tlsL7yF31sDeATRebBMQMbJLcO64UEpD
eHWphBSM19rqf/rRVqZr0hlXmFzFpV1vjN0WruIDMxEZ4y+OVR2OcjHBYOkvSymG
M0n2A2zCMjJ5tQzARwIDAQAB
-----END PUBLIC KEY-----`);
  const encrypted = encrypt.encrypt(dataMd5);
  //console.log("encrypted:");
  //console.log(encrypted);
  //var afterBase64 = window.btoa(encrypted);
  var afterBase64 = Base64.encode(encrypted);
  // console.log("afterBase64:");
  // console.log(afterBase64);
  config.headers['sign'] = afterBase64
  //console.log(config);

  if (config.method === 'post') {
    // config.headers['Content-Type'] = 'multipart/form-data'
    //  config.headers['Content-Type'] = 'application/x-www-form-urlencoded'

    // 将数据处理掉特殊字符后再post出去
    /*    let data = config.data;
        if (data != null) {
          for (var i in data) {
            var value = data[i]
            if (typeof value == 'string') {
              value = value.replace(/[\'\"\\\/\b\f\n\r\t]/g, '');// 去掉转义字符
              value = value.replace(/[\|\`\#\$\%\^\*\{\}\<\>]/g, '');// 去掉特殊字符
              data[i] = value
            }
          }
        }*/
    // 将数据 中null值处理成空字符串 后再post出去
    /*let data = config.data;
    if (data != null) {
      for (var i in data) {
        var value = data[i];
        if (value == null) {
          value = "";
        }
      }
    }*/
  }
  // 如果有token 放到头里
  // console.log(store.getters.token)
  return config
}, error => {
  console.log(error)
  Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(response => {
    const cs = []
    const res = response.data;
    if (res.status == 403) {
      Notification({
        title: "服务器已修改,请重新登录",
        message: res.message,
        type: type,
        dangerouslyUseHTMLString: true,
        // offset:70,
        duration: 8000,
        position: 'right-bottom',
        customClass: 'errorBox'
      });
      setTimeout(() => {
        window.location.reload();
      }, 1500);
    }

    if (res.info.code != 0) {
      var title,
        message,
        type,
        customClass
      if (res.info.code == 20083) {
        title = '登录失败'
        message = '用户名或密码错误，请查证后再试！'
        type = 'warning'
        customClass = 'warmBox'
      }else if (res.info.code == 20063) {
        title = '登录失败'
        message = '用户密码已过期，请联系系统管理员！'
        type = 'warning'
        customClass = 'warmBox'
      } else if (res.info.code == 404) {
        title = '接口异常'
        message = '数据请求失败，请联系管理员！（找不到指定接口）'
        type = 'error'
        customClass = 'errorBox'
      } else if (res.info.code == 500) {
        title = '服务器错误'
        message = res.info.message + '(' + res.info.code + ')'
        type = 'error'
        customClass = 'errorBox'
      } else if (res.info.code == 'WRITE_OFF_FAILED') {
        title = '核销失败'
        message = res.info.message + '(' + res.info.code + ')'
        type = 'warning'
        customClass = 'warmBox'
      } else {
        title = '操作失败'
        message = res.info.message + '(' + res.info.code + ')'
        type = 'warning'
        customClass = 'warmBox'
      }
      // 提示消息
      Notification({
        title: title,
        message: message,
        type: type,
        dangerouslyUseHTMLString: true,
        // offset:70,
        duration: 8000,
        position: 'right-bottom',
        customClass: customClass
      });
    }
    // }
    return response
  },
  error => {

    // console.log(error.config)


    // 超时统一处理
    if (error.message.split(" ").pop() == 'exceeded') {
      var config = error.config;
      // console.log(config)
      // If config does not exist or the retry option is not set, reject
      if (!config || !config.retry) {
        return Promise.reject(error);
      }

      // Set the variable for keeping track of the retry count
      config.__retryCount = config.__retryCount || 0;

      // Check if we've maxed out the total number of retries
      if (config.__retryCount >= config.retry) {
        // Reject with the error
        // service.CancelToken.source()

        // 提示消息
        Notification({
          title: '连接超时',
          message: '请检查您的网络是否正常！如需再试，请刷新页面',
          type: 'warning',
          dangerouslyUseHTMLString: true,
          // offset:70,
          duration: 8000,
          position: 'right-bottom',
          customClass: 'warmBox'
        });
        return Promise.reject(error);
      }

      // Increase the retry count
      config.__retryCount += 1;

      // Create new promise to handle exponential backoff
      var backoff = new Promise(function (resolve) {
        setTimeout(function () {
          resolve();
        }, config.retryDelay || 1);
      });

      // Return the promise in which recalls axios to retry the request
      return backoff.then(function () {
        // console.log(config)
        return service(config);
      });
    }
    // 超时统一处理

    var title,
      message,
      type,
      customClass

    let errCode = error.message.split(" ").pop()
    // if (errCode == 404) {
    //   title = '接口异常'
    //   message = '数据请求失败，请联系管理员！（找不到指定接口）'
    //   type = 'warning'
    //   customClass = 'warmBox'
    // }
    if (errCode == 504) {
      title = '服务器错误'
      message = '服务器没有启动或发生错误，请联系管理员！'
      type = 'error'
      customClass = 'errorBox'
    }
    // 提示消息
    Notification({
      title: title,
      message: message,
      type: type,
      dangerouslyUseHTMLString: true,
      // offset:70,
      duration: 8000,
      position: 'right-bottom',
      customClass: customClass
    });
    return Promise.reject(error)
  })


var encrypt = function () {

}

export default service
