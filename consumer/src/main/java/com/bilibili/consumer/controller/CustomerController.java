package com.bilibili.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/6/6 22:31
 **/
@RestController
@Slf4j
public class CustomerController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/instances")
    public List<ServiceInstance> instances(){
        List<ServiceInstance> instances = this.discoveryClient.getInstances("provider");
        return instances;
    }

    @GetMapping("/appName")
    public String appName(){
        //1、找到provider实例
        List<ServiceInstance> instances = this.discoveryClient.getInstances("provider");
        int index = ThreadLocalRandom.current().nextInt(instances.size());
        ServiceInstance serviceInstance = instances.get(index);
        String url = serviceInstance.getUri() + "/echo/"+appName;

        //2.调用
        log.info("bilibili path = {}", url);
        return this.restTemplate.getForObject(url, String.class);
    }

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/echo/appName")
    public String echoAppName(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
        String path = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
        log.info("path = {}", path);
        return restTemplate.getForObject(path, String.class);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/ribbon/port")
    public String ribbonAppName(){
        String port = this.restTemplate.getForObject("http://provider/port", String.class);
        return port;
    }
}
