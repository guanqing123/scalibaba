package com.bilibili.provider.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/6/7 11:11
 **/
@Service
@Slf4j
public class ProviderService {

    @SentinelResource("test")
    public void test(){
        log.info("service test");
    }

}
