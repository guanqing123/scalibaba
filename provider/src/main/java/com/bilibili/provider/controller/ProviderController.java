package com.bilibili.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/6/6 22:50
 **/
@RestController
public class ProviderController {

    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string){
        return "Hello Nacos Discovery " + string;
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    private String port(){
        return port;
    }
}
