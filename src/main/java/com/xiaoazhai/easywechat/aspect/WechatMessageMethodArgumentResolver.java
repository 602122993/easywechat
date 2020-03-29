package com.xiaoazhai.easywechat.aspect;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpUtil;
import com.xiaoazhai.easywechat.annotation.Message;
import com.xiaoazhai.easywechat.entity.request.WechatMessage;
import com.xiaoazhai.easywechat.util.WxMessageUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class WechatMessageMethodArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.getParameterType().isAssignableFrom(WechatMessage.class) && parameter.hasParameterAnnotation(Message.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String body = IoUtil.read(request.getReader());
        return WxMessageUtil.castToWechatMessage(body);
    }


}
