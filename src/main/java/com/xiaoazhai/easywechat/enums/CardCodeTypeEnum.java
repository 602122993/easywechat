package com.xiaoazhai.easywechat.enums;

/**
 * @author zhai
 * @date 2020年5月27日20:02:41
 * 卡券code类型枚举
 */
public enum  CardCodeTypeEnum {
    /**
     * 文本类型
     */
    CODE_TYPE_TEXT,
    /**
     * 一维码
     */
    CODE_TYPE_BARCODE,
    /**
     *  二维码
     */
    CODE_TYPE_QRCODE,
    /**
     * 二维码无code显示
     */
    CODE_TYPE_ONLY_QRCODE,
    /**
     * 不显示code与二维码信息
     */
    CODE_TYPE_NONE
}
