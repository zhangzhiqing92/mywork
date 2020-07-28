package com.work.order.login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import com.work.order.response.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @RequestMapping("/login")
    public ResponseBody login() {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("userName","admin");
        resultMap.put("passWord","123456");
        ResponseBody rb = new ResponseBody();
        rb.setData(resultMap);
        return rb;
    }

}