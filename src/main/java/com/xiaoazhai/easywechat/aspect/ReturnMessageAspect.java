package com.xiaoazhai.easywechat.aspect;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.extra.servlet.ServletUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhai
 * @date 2020年4月4日22:20:02
 * 返回消息切面
 */
@Aspect
@Component
public class ReturnMessageAspect {


    /**
     * 请求前触发
     *
     * @param point
     */
    @Before("(@annotation(org.springframework.web.bind.annotation.RequestMapping)||@annotation(org.springframework.web.bind.annotation.PostMapping))&&args(com.xiaoazhai.easywechat.entity.message.AllTypeWechatMessage)")
    public void doBefore(JoinPoint point) throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if (response.getOutputStream() != null) {
            IoUtil.write(response.getOutputStream(), CharsetUtil.UTF_8, true, "success");
        }
    }


}
