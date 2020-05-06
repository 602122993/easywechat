package com.xiaoazhai.easywechat.entity.request;

import com.xiaoazhai.easywechat.enums.ClientTypeEnum;
import com.xiaoazhai.easywechat.enums.LanguageEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhai
 * @date 2020年5月6日20:22:41
 * 匹配规则
 */
@Data
public class MatchRule {
    /**
     * 标签id
     */
    private String tag_id;
    /**
     * 性别
     */
    private String sex;
    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;

    /**
     * 客户端版本
     */
    private ClientTypeEnum clientType;
    /**
     * 客户端版本实际值
     */
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Integer client_platform_type;
    /**
     * 语言
     */
    private LanguageEnum language;


}
