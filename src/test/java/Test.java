import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.xiaoazhai.easywechat.EasyWechatApplication;
import com.xiaoazhai.easywechat.entity.request.BaseCreateCardRequest;
import com.xiaoazhai.easywechat.entity.request.GrouponCardCreateRequest;
import com.xiaoazhai.easywechat.entity.request.SendMessageByOpenIdRequest;
import com.xiaoazhai.easywechat.entity.response.CreateCardResponse;
import com.xiaoazhai.easywechat.entity.response.ErrorResponse;
import com.xiaoazhai.easywechat.entity.response.SendMessageResponse;
import com.xiaoazhai.easywechat.enums.CardCodeTypeEnum;
import com.xiaoazhai.easywechat.enums.CardDateInfoTypeEnum;
import com.xiaoazhai.easywechat.enums.SendMsgTypeEnum;
import com.xiaoazhai.easywechat.util.WxPubUtil;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EasyWechatApplication.class)
public class Test {


    @org.junit.Test
    public void test() {
        List<String> openid = new ArrayList<>();
        openid.add("oQWV3wH0UEXKYLseskIyUmkXe2Yc");
        openid.add("oQWV3wBOsktFAKMEnbf0XNMzwFSs");
        SendMessageResponse res = SendMessageByOpenIdRequest.builder().cardId("pQWV3wGzHVpGIdLRIH_Tr84h8QCU").touser(openid)
                .msgtype(SendMsgTypeEnum.wxcard).build().execute();
        System.out.println(res);

    }
}
