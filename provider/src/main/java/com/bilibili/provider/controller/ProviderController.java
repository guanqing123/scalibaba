package com.bilibili.provider.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.bilibili.provider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

    // spring-cloud-starter-alibaba-sentinel 导致port无法读取
    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    private String port(){
        return port;
    }

    @GetMapping("/list")
    private String list(){
        return "list";
    }

    @Autowired
    private ProviderService providerService;

    @GetMapping("/test1")
    public String test1(){
        this.providerService.test();
        return "test1";
    }

    @GetMapping("/test2")
    public String test2(){
        this.providerService.test();
        return "test2";
    }

    @GetMapping("/hot")
    @SentinelResource("hot")
    public String hot(
            @RequestParam(value = "num1", required = false) Integer num1,
            @RequestParam(value = "num2", required = false) Integer num2){
        return num1+"-"+num2;
    }
}
