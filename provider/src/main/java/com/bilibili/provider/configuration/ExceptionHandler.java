package com.bilibili.provider.configuration;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: TODO 类描述
 * @Author guanqing
 * @Date 2021/6/7 14:44
 */

public class ExceptionHandler implements UrlBlockHandler {
    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
        httpServletResponse.setContentType("text/html;charset=utf-8");
        String msg = null;
        if (e instanceof FlowException){
            msg = "限流";
        }else if (e instanceof DegradeException){
            msg = "降级";
        }
        httpServletResponse.getWriter().write(msg);
    }
}
