import com.xiaoazhai.easywechat.EasyWechatApplication;
import com.xiaoazhai.easywechat.entity.request.*;
import com.xiaoazhai.easywechat.entity.response.CreateCardLandingPageResponse;
import com.xiaoazhai.easywechat.entity.response.CreateCardQrCodeResponse;
import com.xiaoazhai.easywechat.entity.response.SendMessageResponse;
import com.xiaoazhai.easywechat.enums.*;
import com.xiaoazhai.easywechat.util.WxPubUtil;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EasyWechatApplication.class)
public class Test {


    @org.junit.Test
    public void test() {
        List<CreateCardLandingPageRequest.CardInfo> list = new ArrayList<>();
        CreateCardLandingPageRequest.CardInfo cardInfo = new CreateCardLandingPageRequest.CardInfo();
        cardInfo.setCardId("pQWV3wFWj5LJegG1ghbFdEz0Ih-Q");
        cardInfo.setThumbUrl("http://47.96.89.225/test/M00/00/04/rBEAAV4dfKGARFFlAAAgwFOF66U729.jpg");
        list.add(cardInfo);
        cardInfo = new CreateCardLandingPageRequest.CardInfo();
        cardInfo.setCardId("pQWV3wCK5VeErRXnHg4IhDk_NzLU");
        cardInfo.setThumbUrl("http://47.96.89.225/test/M00/00/04/rBEAAV4dfKGARFFlAAAgwFOF66U729.jpg");
        list.add(cardInfo);
        CreateCardLandingPageRequest.builder().banner("http://47.96.89.225/test/M00/00/04/rBEAAV4dfKGARFFlAAAgwFOF66U729.jpg")
                .canShare(false).scene(LandingPageSceneEnum.SCENE_QRCODE)
                .pageTitle("title").cardList(list).build().execute();

    }

    @org.junit.Test
    public void createCard() {

        //pQWV3wOD69R-5OA57wCQeDGj40G0
        //p4MlaxOzmhKdxwVaa7dO5-qe-m0w
        BaseCreateCardRequest.builder().logoUrl("http://file.hybhz.com.cn/test/M00/00/08/rBEAAV7e_DOAKiiqAABLMn2lRP8200.jpg")
                .brandName("杭银营销平台")
                .codeType(CardCodeTypeEnum.CODE_TYPE_QRCODE)
                .title("奖品名称")
                .color("Color010")
                .cardType(CardTypeEnum.CASH)
                .leastCost(20)
                .reduceCost(200)
                .notice("使用提醒")
                .description("奖品描述")
                .type(CardDateInfoTypeEnum.DATE_TYPE_FIX_TERM)
                .useCustomCode(true)
                .quantity(10)
                .fixedTerm(1)
                .fixedBeginTerm(3)
                .Abstract("奖品abstract")
                .iconUrlList(Arrays.asList("http://file.hybhz.com.cn/test/M00/00/08/rBEAAV7e_DOAKiiqAABLMn2lRP8200.jpg"))
                .build().execute();
    }

    @org.junit.Test
    public void importCode() {
        KfAccountRequest.builder().kfAccount("kfdgddf@gh_ee5f47ec6dd9").kfId("dfs").kfNick("xiaoazhai")
                .nickname("nickname").password("asdfqwer").build().add();
    }
}
