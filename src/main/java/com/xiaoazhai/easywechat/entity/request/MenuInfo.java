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


    public static void main(String[] args) {
        String json = " {\n" +
                "            \"button\": [\n" +
                "                {\n" +
                "                    \"type\": \"click\", \n" +
                "                    \"name\": \"今日歌曲\", \n" +
                "                    \"key\": \"V1001_TODAY_MUSIC\", \n" +
                "                    \"sub_button\": [ ]\n" +
                "                }, \n" +
                "                {\n" +
                "                    \"name\": \"菜单\", \n" +
                "                    \"sub_button\": [\n" +
                "                        {\n" +
                "                            \"type\": \"view\", \n" +
                "                            \"name\": \"搜索\", \n" +
                "                            \"url\": \"http://www.soso.com/\", \n" +
                "                            \"sub_button\": [ ]\n" +
                "                        }, \n" +
                "                        {\n" +
                "                            \"type\": \"view\", \n" +
                "                            \"name\": \"视频\", \n" +
                "                            \"url\": \"http://v.qq.com/\", \n" +
                "                            \"sub_button\": [ ]\n" +
                "                        }, \n" +
                "                        {\n" +
                "                            \"type\": \"click\", \n" +
                "                            \"name\": \"赞一下我们\", \n" +
                "                            \"key\": \"V1001_GOOD\", \n" +
                "                            \"sub_button\": [ ]\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ], \n" +
                "            \"matchrule\": {\n" +
                "                \"group_id\": 2, \n" +
                "                \"sex\": 1, \n" +
                "                \"country\": \"中国\", \n" +
                "                \"province\": \"广东\", \n" +
                "                \"city\": \"广州\", \n" +
                "                \"client_platform_type\": 2\n" +
                "            }, \n" +
                "            \"menuid\": 208396993\n" +
                "        }";

        JSONObject jsonObj = JSONUtil.parseObj(json);
        List<JSONObject> arr = jsonObj.getJSONArray("button").toList(JSONObject.class);
        arr.forEach(jsonObject -> System.out.println(JSONUtil.toJsonStr(jsonObject)));
    }
}
