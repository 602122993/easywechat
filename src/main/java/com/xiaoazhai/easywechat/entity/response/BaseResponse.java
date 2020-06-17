package com.xiaoazhai.easywechat.entity.response;

import com.xiaoazhai.easywechat.exception.WxPayException;
import com.xiaoazhai.easywechat.exception.WxPubException;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author zhai
 * @date 2020年3月5日11:15:04
 * 统一返回结果
 */
@Data
public class BaseResponse {

    private String errcode;

    private String errmsg;

    private String resultCode;

    private String errCodeDes;


    public void setErrcode(String errcode) {
        this.errcode = errcode;
        if ((!StringUtils.isEmpty(this.errmsg)) && !"0".equals(errcode)) {
            throw new WxPubException(this.errmsg);
        }
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
        if ((!StringUtils.isEmpty(this.errcode)) && !"0".equals(errcode)) {
            throw new WxPubException(this.errmsg);
        }
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
        if ((!StringUtils.isEmpty(errCodeDes)) && "FAIL".equals(resultCode)) {
            throw new WxPayException(this.errCodeDes);
        }
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
        if ((!StringUtils.isEmpty(this.resultCode)) && "FAIL".equals(this.resultCode)) {
            throw new WxPayException(this.errCodeDes);
        }
    }
}
