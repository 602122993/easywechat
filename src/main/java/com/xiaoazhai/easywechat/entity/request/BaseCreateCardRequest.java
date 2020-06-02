package com.xiaoazhai.easywechat.entity.request;

import com.xiaoazhai.easywechat.entity.message.InnerMessageClass;
import com.xiaoazhai.easywechat.entity.message.respmsg.NeedRecursionInterface;
import com.xiaoazhai.easywechat.entity.message.respmsg.ReturnMessageInterface;
import com.xiaoazhai.easywechat.enums.BusinessService;
import com.xiaoazhai.easywechat.enums.CardCodeTypeEnum;
import com.xiaoazhai.easywechat.enums.CardDateInfoTypeEnum;
import com.xiaoazhai.easywechat.util.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhai
 * @date 2020年5月26日21:38:03
 * 创建微信卡卷请求
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseCreateCardRequest {

    /**
     * 商户logo
     */
    private String logoUrl;
    /**
     * 码类型
     */
    private CardCodeTypeEnum codeType;
    /**
     * 商户名称
     */
    private String brandName;
    /**
     * 卡卷名
     */
    private String title;
    /**
     * 卡卷颜色
     */
    private String color;
    /**
     * 卡券使用提醒
     */
    private String notice;
    /**
     * 卡券描述
     */
    private String description;
    /**
     * 库存
     */
    private Integer quantity;
    /**
     * 使用时间的类型
     */
    private CardDateInfoTypeEnum type;
    /**
     * 开始时间
     */
    private String beginTimestamp;
    /**
     * 结束时间
     */
    private String endTimestamp;
    /**
     * 表示领取多少天内有效
     */
    private Integer fixedTerm;
    /**
     * 表示领取后多少天开始生效
     */
    private Integer fixedBeginTerm;
    /**
     * 是否自定义Code码
     */
    private Boolean useCustomCode;
    /**
     * 填入 GET_CUSTOM_CODE_MODE_DEPOSIT 表示该卡券为预存code模式卡券
     */
    private String getCustomCodeMode;
    /**
     * 是否指定用户领取
     */
    private Boolean bindOpenid;
    /**
     * 客服电话
     */
    private String servicePhone;
    /**
     * 门店位置poiid
     */
    private List<String> locationIdList;
    /**
     * 设置本卡券支持全部门店
     */
    private Boolean useAllLocations;
    /**
     * 中心标题
     */
    private String centerTitle;
    /**
     * 显示在入口下方的提示语
     */
    private String centerSubTitle;
    /**
     * 顶部居中的url
     */
    private String centerUrl;

    /**
     * 卡券跳转的小程序的user_name，仅可跳转该 公众号绑定的小程序 。
     */
    private String centerAppBrandUserName;
    /**
     * 卡券跳转的小程序的路径
     */
    private String centerAppBrandPass;

    /**
     * 显示在入口右侧的提示语
     */
    private String customUrlSubTitle;
    /**
     * 自定义跳转外链入口名字
     */
    private String customUrlName;
    /**
     * 自定义跳转外链的url
     */
    private String customUrl;
    /**
     * 卡券跳转的小程序的user_name
     */
    private String customAppBrandUserName;
    /**
     * 卡券跳转的小程序的path
     */
    private String customAppBrandPass;
    /**
     * 营销场景的自定义入口名称
     */
    private String promotionUrlName;
    /**
     * 入口跳转外链的地址链接。
     */
    private String promotionUrl;
    /**
     * 显示在营销入口右侧
     */
    private String promotionUrlSubTitle;
    /**
     * 跳转的小程序名称(已经第三了 我人都傻了
     */
    private String promotionAppBrandUserName;
    /**
     * 跳转的小程序的path
     */
    private String promotionAppBrandPass;
    /**
     * 每人可领券的数量限制
     */
    private Integer getLimit;
    /**
     * 每人可核销的数量限制
     */
    private Integer useLimit;
    /**
     * 领取页面是否可分享
     */
    private Boolean canShare;
    /**
     * 卡券是否可转增
     */
    private Boolean canGiveFriend;
    /**
     * 指定可用的商品类目
     */
    private String acceptCategory;
    /**
     * 指定不可用的商品类目
     */
    private String rejectCategory;
    /**
     * 满减门槛字段
     */
    private String leastCost;
    /**
     * 购买xx可用类型门槛，仅用于兑换 ，填入后自动拼写购买xxx可用
     */
    private String objectUseFor;
    /**
     * 不可以与其他类型共享门槛 ，填写false时系统将在使用须知里 拼写“不可与其他优惠共享”
     */
    private Boolean canUseWithOtherDiscount;
    /**
     * 封面摘要简介
     */
    private String Abstract;
    /**
     * 封面图片列表
     */
    private String iconUrlList;

    /**
     * 图文地址
     */
    private List<TextImage> textImageList;
    /**
     * 时效 限制
     */
    private List<TimeLimit> timeLimit;
    /**
     * 商家服务类型
     */
    private List<BusinessService> businessService;
    /**
     * 团购专用,团购详情
     */
    private String dealDetail;
    /**
     * 折扣力度
     */
    private Integer discount;
    /**
     * 兑换礼物名称
     */
    private String gift;
    /**
     * 优惠券专用,优惠券详情
     */
    private String defaultDetail;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class DateInfo implements NeedRecursionInterface {
        private CardDateInfoTypeEnum type;

        private String beginTimestamp;

        private String endTimestamp;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class SKU implements NeedRecursionInterface {
        private Integer quantity;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class BaseInfo implements NeedRecursionInterface {
        /**
         * 商户logo
         */
        private String logoUrl;
        /**
         * 码类型
         */
        private CardCodeTypeEnum codeType;
        /**
         * 商户名称
         */
        private String brandName;
        /**
         * 卡卷名
         */
        private String title;
        /**
         * 卡卷颜色
         */
        private String color;
        /**
         * 卡券使用提醒
         */
        private String notice;
        /**
         * 卡券描述
         */
        private String description;
        private SKU sku;
        private DateInfo dateInfo;
        /**
         * 表示领取多少天内有效
         */
        private Integer fixedTerm;
        /**
         * 表示领取后多少天开始生效
         */
        private Integer fixedBeginTerm;
        /**
         * 是否自定义Code码
         */
        private Boolean useCustomCode;
        /**
         * 填入 GET_CUSTOM_CODE_MODE_DEPOSIT 表示该卡券为预存code模式卡券
         */
        private String getCustomCodeMode;
        /**
         * 是否指定用户领取
         */
        private Boolean bindOpenid;
        /**
         * 客服电话
         */
        private String servicePhone;
        /**
         * 门店位置poiid
         */
        private List<String> locationIdList;
        /**
         * 设置本卡券支持全部门店
         */
        private Boolean useAllLocations;
        /**
         * 中心标题
         */
        private String centerTitle;
        /**
         * 显示在入口下方的提示语
         */
        private String centerSubTitle;
        /**
         * 顶部居中的url
         */
        private String centerUrl;

        /**
         * 卡券跳转的小程序的user_name，仅可跳转该 公众号绑定的小程序 。
         */
        private String centerAppBrandUserName;
        /**
         * 卡券跳转的小程序的路径
         */
        private String centerAppBrandPass;

        /**
         * 显示在入口右侧的提示语
         */
        private String customUrlSubTitle;
        /**
         * 自定义跳转外链入口名字
         */
        private String customUrlName;
        /**
         * 自定义跳转外链的url
         */
        private String customUrl;
        /**
         * 卡券跳转的小程序的user_name
         */
        private String customAppBrandUserName;
        /**
         * 卡券跳转的小程序的path
         */
        private String customAppBrandPass;
        /**
         * 营销场景的自定义入口名称
         */
        private String promotionUrlName;
        /**
         * 入口跳转外链的地址链接。
         */
        private String promotionUrl;
        /**
         * 显示在营销入口右侧
         */
        private String promotionUrlSubTitle;
        /**
         * 跳转的小程序名称(已经第三了 我人都傻了
         */
        private String promotionAppBrandUserName;
        /**
         * 跳转的小程序的path
         */
        private String promotionAppBrandPass;
        /**
         * 每人可领券的数量限制
         */
        private Integer getLimit;
        /**
         * 每人可核销的数量限制
         */
        private Integer useLimit;
        /**
         * 领取页面是否可分享
         */
        private Boolean canShare;
        /**
         * 卡券是否可转增
         */
        private Boolean canGiveFriend;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class AdvancedInfo implements NeedRecursionInterface {
        private UseCondition useCondition;

        private Abstract Abstract;

        private List<TextImage> textImageList;

        private List<TimeLimit> timeLimit;

        private List<BusinessService> businessService;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class UseCondition implements NeedRecursionInterface {
        private String acceptCateGory;
        private String rejectCategory;
        private Boolean canUseWithOtherDiscount;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Abstract implements NeedRecursionInterface {
        private String Abstract;

        private List<String> iconUrlList;


    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TextImage  implements NeedRecursionInterface {
        private String imageUrl;

        private String text;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TimeLimit implements NeedRecursionInterface {
        private String type;

        private Integer beginHour;

        private Integer endHour;

        private Integer beginMinute;

        private Integer endMinute;

    }

    public void execute() {
        Map<String, Object> card = new HashMap<>();
        Map<String, Object> groupon = new HashMap<>();
        BaseInfo baseInfo = BeanUtil.copyIgnoreNullPropertiesGeneric(this, new BaseInfo());
        DateInfo dateInfo = BeanUtil.copyIgnoreNullPropertiesGeneric(this, new DateInfo());
        SKU sku = BeanUtil.copyIgnoreNullPropertiesGeneric(this, new SKU());
        baseInfo.setDateInfo(dateInfo);
        baseInfo.setSku(sku);
        AdvancedInfo advancedInfo = new AdvancedInfo();
        UseCondition useCondition = BeanUtil.copyIgnoreNullPropertiesGeneric(this, new UseCondition());
        com.xiaoazhai.easywechat.entity.request.BaseCreateCardRequest.Abstract abs = BeanUtil.copyIgnoreNullPropertiesGeneric(this, new Abstract());
        advancedInfo.setUseCondition(useCondition);
        advancedInfo.setAbstract(abs);
        advancedInfo.setTextImageList(this.textImageList);
        advancedInfo.setTimeLimit(this.timeLimit);
        advancedInfo.setBusinessService(this.businessService);
        card=BeanUtil.beanToMapRecursion(baseInfo,true,true);
        groupon.put("base_info", BeanUtil.beanToMap(baseInfo, true, true));

    }

    public static void main(String[] args) {
       BaseCreateCardRequest.builder().Abstract("asdf").centerUrl("asdf").quantity(50000).brandName("asdf").build().execute();
    }


}