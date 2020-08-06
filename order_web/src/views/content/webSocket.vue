<template>
</template>
<script>
  export default {
    name: 'ChatRoom',
    data () {
      return {
      }
    },

    created() {
      this.webSocket(); // 连接WebSocket
    },
    destroyed :function() {
      this.websocketclose();
    },
    methods: {
      say() {
        if (this.websock == null) {
          alert("连接异常：null");
          return 'null'
        }
        if(this.userInput===""){
          this.tips('请输入聊天内容');
        }else{
          this.tips('');
        }
        this.websocketsend(this.userInput);
        this.userInput = '';
      },
      webSocket :function() {
        // 建立socket连接
        if ('WebSocket' in window) {//判断当前浏览器是否支持webSocket
          this.websock = new WebSocket("ws://192.168.18.58:9988/order_boot/websocket");//与后端服务建立连接
        } else {
          alert('你的浏览器暂不支持websocket :(');
        }
        console.log(this.websock);
        this.websock.onopen = this.websocketonopen;
        this.websock.onerror = this.websocketonerror;
        this.websock.onmessage = this.websocketonmessage;
        this.websock.onclose = this.websocketclose;
      },
      websocketonopen :function() {
        console.log("WebSocket连接成功");
        // let data = {
        //   code: 0,
        //   msg: '这是client：初次连接'
        // }
        var data = '这是'+'user'+'，加入了连接';
        this.websocketsend(data)
      },
      websocketonerror :function(e) {
        this.tips("WebSocket连接发生错误");
        // console.log("WebSocket连接发生错误");
      },
      websocketonmessage :function(e) { // 数据接收
        // 显示消息内容
        this.setMessageInnerHTML(e.data);
      },
      websocketsend :function (data) { // 数据发送
        // this.tips("数据发送中...");
        this.websock.send(this.niming+": "+data)
      },
      websocketclose :function(e) {
        this.websock.close();
        this.tips("WebSocket连接关闭");
        console.log("connection closed (" + e + ")");// e.code
      },
      //将消息显示在网页上
      setMessageInnerHTML(msg) {
        document.getElementById('message').innerHTML += msg + '<br/>';
      },
      //提示信息显示在网页上
      tips (msg) {
        document.getElementById('ts').innerHTML = msg ;
      }
    }
  }
</script>

<style scoped>
</style>
