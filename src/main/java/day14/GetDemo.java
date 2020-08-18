package day14;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

public class GetDemo {
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
    public static void main(String[] args) throws Exception {
        HttpGet get = new HttpGet("http://api.lemonban.com/futureloan/loans?pageIndex=1&pageSize=1");
        get.setHeader("X-Lemonban-Media-Type","lemonban.v1");

        //创建一个HttpClient客户端
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(get);
        int code = response.getStatusLine().getStatusCode();
        System.out.println(code);
        Header[] headers = response.getAllHeaders();
        System.out.println(Arrays.toString(headers));

        HttpEntity entity = response.getEntity();
        System.out.println(EntityUtils.toString(entity));

    }
}
