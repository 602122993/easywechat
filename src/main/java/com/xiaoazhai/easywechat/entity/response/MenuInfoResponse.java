package com.xiaoazhai.easywechat.entity.response;

import com.xiaoazhai.easywechat.entity.request.MatchRule;
import com.xiaoazhai.easywechat.entity.request.MenuInfo;
import lombok.Data;

import java.util.List;

/**
 * @author zhai
 * @date 2020年5月6日20:50:49
 * 菜单信息返回结果
 */
@Data
public class MenuInfoResponse {

    /**
     * 菜单列表
     */
    private List<MenuInfo> menuList;
    /**
     * 匹配规则
     */
    private MatchRule matchRule;
}
