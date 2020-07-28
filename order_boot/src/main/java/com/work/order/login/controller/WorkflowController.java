package com.work.order.login.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkflowController{

    @RequestMapping("/login")
    public String helloSpringBoot() {
        return "Hello SpringBoot Project.";
    }

}