package com.xiaoazhai.easywechat.aspect;

import com.xiaoazhai.easywechat.annotation.MessageHandler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
/**
 * @author zhai
 * @date 2020年3月30日18:58:00
 * 消息处理拦截器
 */
public class MessageRequestAspect {


    /**
     * 定义切入点
     */
    @Pointcut("@annotation(com.xiaoazhai.easywechat.annotation.MessageRequest)")
    public void message() {
    }


    /**
     * 请求前触发
     *
     * @param point
     */
    @Before("message()&& @annotation(mh)")
    public void doBefore(JoinPoint point, MessageHandler mh) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

    }
}
