package com.xiaoazhai.easywechat.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhai
 * @date 2020年6月17日21:28:39
 * 获取图文消息返回值
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardGetMpNewResponse extends BaseResponse {

    private String content;

}
