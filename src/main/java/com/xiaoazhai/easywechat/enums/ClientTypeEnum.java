package com.xiaoazhai.easywechat.enums;

/**
 * @author zhai
 * @date 2020年5月6日20:25:43
 * 客户端类型
 */
public enum ClientTypeEnum {

    IOS(1), ANDROID(2), OTHERS(3);

    private Integer code;


    ClientTypeEnum(int code) {
        this.code = code;
    }

}
