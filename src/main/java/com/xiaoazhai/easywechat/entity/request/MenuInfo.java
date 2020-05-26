package com.xiaoazhai.easywechat.entity.request;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xiaoazhai.easywechat.enums.MenuTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * @author zhai
 * @date 2020年5月5日20:04:11
 * <p>
 * 微信自定义菜单类
 * </p>
 */
@Data
public class MenuInfo {
    /**
     * 菜单类型
     */
    private MenuTypeEnum type;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 点击事件中的key值
     */
    private String key;
    /**
     * 跳转URL
     */
    private String url;
    /**
     * 小程序APPid
     */
    private String appid;
    /**
     * 页面地址
     */
    private String pagepath;
    /**
     * 子菜单
     */
    private List<MenuInfo> sub_button;



}
