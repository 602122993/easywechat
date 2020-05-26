import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.xiaoazhai.easywechat.EasyWechatApplication;
import com.xiaoazhai.easywechat.util.WxPubUtil;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EasyWechatApplication.class)
public class Test {


    @org.junit.Test
    public void test() {
        WxPubUtil.uploadImage("https://image-xiaoazhai.oss-cn-hangzhou.aliyuncs.com/blog/11bdb7b4-e001-4116-85e0-a8878f510b13.jpg");
    }
}
