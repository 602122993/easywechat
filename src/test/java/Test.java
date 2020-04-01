import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;

public class Test {


    public static void main(String[] args) {
        FileUtil.writeFromStream(HttpUtil.createGet("http://images.xiaoazhai.com/blog/timg.jpg").execute().bodyStream(), "C:\\code\\huanapp\\build\\test.jpg");
    }
}
