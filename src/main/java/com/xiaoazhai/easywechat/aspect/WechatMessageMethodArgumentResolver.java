package com.xiaoazhai.easywechat.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.Method;
import com.xiaoazhai.easywechat.annotation.Message;
import com.xiaoazhai.easywechat.config.WxConfig;
import com.xiaoazhai.easywechat.entity.message.AllTypeWechatMessage;
import com.xiaoazhai.easywechat.entity.message.BaseWechatMessage;
import com.xiaoazhai.easywechat.entity.message.respmsg.ReturnWechatMessage;
import com.xiaoazhai.easywechat.entity.request.CheckInfo;
import com.xiaoazhai.easywechat.util.BeanUtil;
import com.xiaoazhai.easywechat.util.SignUtil;
import com.xiaoazhai.easywechat.util.WxMessageUtil;
import com.xiaoazhai.easywechat.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhai
 * @date 2020年3月30日18:47:45
 * 微信消息参数拦截器
 */
@Slf4j
public class WechatMessageMethodArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.getParameterType().isAssignableFrom(AllTypeWechatMessage.class) && parameter.hasParameterAnnotation(Message.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        log.info(request.getMethod());
        if (request.getMethod().equals(Method.GET.toString())) {
            String sign = SecureUtil.sha1(request.getParameter("nonce") + request.getParameter("timestamp") + WxConfig.pubToken);
            log.info("计算签名==================" + sign);
            if (sign.equals(request.getParameter("signature"))) {
                IoUtil.write(response.getOutputStream(), CharsetUtil.UTF_8, true, request.getParameter("echostr"));
                return null;
            }
        }
        String body = IoUtil.read(request.getReader());
        if (WxConfig.aesSecret) {
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String msgSignature = request.getParameter("msg_signature");
            body = XmlUtil.decode(CheckInfo.builder().timestamp(timestamp).postData(body).nonce(nonce).msgSignature(msgSignature).build());
        }
        BaseWechatMessage baseWechat = WxMessageUtil.castToWechatMessage(body, BaseWechatMessage.class);
        ReturnWechatMessage.threadLocal.set(baseWechat);
        AbstractMessageHandler abs = AbstractMessageHandler.registerMap.get(baseWechat.getMsgType().getClazz().getName());
        if (abs != null) {
            Object obj = abs.onMessage(WxMessageUtil.castToWechatMessage(body, baseWechat.getMsgType().getClazz()));
            if (obj instanceof String) {
                if (obj.equals(AbstractMessageHandler.SUCCESS)) {
                    IoUtil.write(response.getOutputStream(), CharsetUtil.UTF_8, true, obj.toString());
                    return null;
                }
                if (obj.equals(AbstractMessageHandler.CONTINUE)) {
                    return WxMessageUtil.castToWechatMessage(body, AllTypeWechatMessage.class);
                }
            }
            if (obj instanceof ReturnWechatMessage) {
                IoUtil.write(response.getOutputStream(), CharsetUtil.UTF_8, true, ((ReturnWechatMessage) obj).getResult());
                return null;
            }

        }
        return WxMessageUtil.castToWechatMessage(body, AllTypeWechatMessage.class);
    }


}
