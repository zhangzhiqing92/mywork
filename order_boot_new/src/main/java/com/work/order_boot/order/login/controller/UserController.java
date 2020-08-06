package com.work.order_boot.order.login.controller;

import com.work.order_boot.order.response.AssembleResponseMsg;
import org.springframework.web.bind.annotation.RequestMapping;
import com.work.order_boot.order.response.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @RequestMapping("/login")
    public ResponseBody login() {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("userName","admin");
        resultMap.put("passWord","123456");
        return new AssembleResponseMsg().success(resultMap, null);
        }
}