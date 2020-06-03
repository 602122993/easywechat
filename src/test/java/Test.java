import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.xiaoazhai.easywechat.EasyWechatApplication;
import com.xiaoazhai.easywechat.entity.request.BaseCreateCardRequest;
import com.xiaoazhai.easywechat.entity.request.GrouponCardCreateRequest;
import com.xiaoazhai.easywechat.entity.response.CreateCardResponse;
import com.xiaoazhai.easywechat.enums.CardCodeTypeEnum;
import com.xiaoazhai.easywechat.enums.CardDateInfoTypeEnum;
import com.xiaoazhai.easywechat.util.WxPubUtil;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EasyWechatApplication.class)
public class Test {


    @org.junit.Test
    public void test() {
        CreateCardResponse res = BaseCreateCardRequest.builder().logoUrl("http://test.hybhz.com.cn/activity/img/gold_container_af896c6.png")
                .codeType(CardCodeTypeEnum.CODE_TYPE_QRCODE)
                .brandName("杭银营销平台")
                .title("兰博基尼抵换券")
                .color("Color010")
                .notice("換就完事了")
                .description("拿著来公司换")
                .quantity(0)
                .type(CardDateInfoTypeEnum.DATE_TYPE_FIX_TERM)
                .fixedTerm(15)
                .fixedBeginTerm(0)
                .useCustomCode(true)
                .getCustomCodeMode("GET_CUSTOM_CODE_MODE_DEPOSIT")
                .bindOpenid(true)
                .useAllLocations(true)
//                .centerTitle("中央title")
//                .centerSubTitle("centersubtitle")
//                .centerUrl("www.baidu.com")
//                .customUrlName("自定义跳转外链")
//                .customUrl("www.qq.com")
//                .customUrlSubTitle("自定义subtitle")
//                .promotionUrlName("营销场景url")
//                .promotionUrl("map.weixin.qq.com")
//                .promotionUrlSubTitle("营销场景sub")
                .getLimit(1)
                .useLimit(1)
                .canShare(true)
                .canGiveFriend(true)
                .acceptCategory("可用商品类目")
                .rejectCategory("不可用商品类目")
                .detail("团购券莫参数回复").build().execute();

        System.out.println(JSONUtil.toJsonStr(res));



    }
}
