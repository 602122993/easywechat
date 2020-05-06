package com.xiaoazhai.easywechat.enums;

/**
 * @author zhai
 * @date 2020年5月6日20:27:40
 * 性别枚举
 */
public enum SexEnum {

    MAN(1), WOMAN(2), ;

    private Integer code;


    SexEnum(int code) {
        this.code = code;
    }
}
