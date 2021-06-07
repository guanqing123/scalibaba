package com.bilibili.provider.test;

import org.springframework.web.client.RestTemplate;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/6/7 11:01
 **/
public class Test {

    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 100; i++){
            restTemplate.getForObject("http://127.0.0.1:8081/port", String.class);
            Thread.sleep(10);
        }
    }
}
