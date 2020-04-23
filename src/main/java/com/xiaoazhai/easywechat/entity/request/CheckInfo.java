package com.xiaoazhai.easywechat.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhai
 * @date 2020年4月13日21:36:10
 * 校验信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckInfo {

    public String postData;

    public String timestamp;

    public String nonce;


    public String msgSignature;


}
