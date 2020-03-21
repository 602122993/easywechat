package com.xiaoazhai.easywechat.entity.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zhai
 * @date 2020年2月23日19:24:09
 * <p>
 * 微信用户信息
 * </p>
 */
@Data
public class WxUserInfoResponse {

    /**
     * 微信开放id
     */
    private String openid;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private String sex;
    /**
     * 用户个人资料填写的省份
     */
    private String province;
    /**
     * 普通用户个人资料填写的城市
     */
    private String city;
    /**
     * 国家，如中国为CN
     */
    private String country;
    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效
     */
    private String headimgurl;
    /**
     * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     */
    private List<String> privilege;
    /**
     * 用户全局id
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    private String unionid;
    /**
     * 是否关注
     */
    private Integer subscribe;
    /**
     * 订阅时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date subscribeTime;

    private String remark;

    private String groupid;

    private List tagidList;

    private String subscribeScene;

    private String qrScene;

    private String qrSceneStr;

    private List<WxUserInfoResponse> userInfoList;

}
