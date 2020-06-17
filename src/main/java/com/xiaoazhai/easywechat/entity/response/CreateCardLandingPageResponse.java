package com.xiaoazhai.easywechat.entity.response;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年6月17日15:59:46
 * 创建卡券货架返回额
 */
@Data
public class CreateCardLandingPageResponse extends BaseResponse {

    private String url;

    private String pageId;


}
