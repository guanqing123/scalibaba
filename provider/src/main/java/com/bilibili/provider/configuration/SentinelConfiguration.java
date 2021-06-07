package com.bilibili.provider.configuration;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/6/7 14:36
 **/

@Configuration
public class SentinelConfiguration {

    @PostConstruct
    public void init(){
//        WebCallbackManager.setRequestOriginParser(new RequestOriginParserDefinition());
        WebCallbackManager.setUrlBlockHandler(new ExceptionHandler());
    }
}
