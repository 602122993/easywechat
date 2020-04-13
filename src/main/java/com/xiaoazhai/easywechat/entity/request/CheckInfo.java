package com.xiaoazhai.easywechat.entity.request;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年4月13日21:36:10
 * 校验信息
 */
@Data
public class CheckInfo {

    public String token;

    public String timestamp;

    public String nonce;


    public String msgSignature;



}
