package day14;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

public class PostDemo {
    /*
    发送get请求
    1.创建请求对象
    2.设置请求方法
    3。设置接口URL地址
    4.设置请求头
    5.设置请求体（接口参数）
    6.点击发送
    7.获取响应
    8.格式化响应对象(状态码，头，体)
     */
    public static void main(String[] args) throws IOException {
        HttpPost post = new HttpPost("http://api.lemonban.com/futureloan/member/recharge");
        post.setHeader("X-Lemonban-Media-Type","lemonban.v1");
        post.setHeader("Content-Type","application/json");
        StringEntity body = new StringEntity("{" + "\"member_id\":2060110," + "\"amount\":6000" + "}", "UTF-8");
        post.setEntity(body);

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(post);
        System.out.println(response.getStatusLine().getStatusCode());
        Header[] headers = response.getAllHeaders();
        System.out.println(Arrays.toString(headers));
        //得到响应体，并且格式化
        HttpEntity entity = response.getEntity();
        System.out.println(EntityUtils.toString(entity));


    }
}
