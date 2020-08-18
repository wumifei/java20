package day14.homework;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

public class HttpUtils1 {
    public static HttpResponse get(String url) throws Exception {
        HttpGet get = new HttpGet(url);
        get.setHeader("X-Lemonban-Media-Type","lemonban.v1");
        //创建一个HttpClient客户端
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(get);
        //printBody(response);
        return response;
    }
    public static HttpResponse post(String url,String param) throws Exception {
        HttpPost post = new HttpPost(url);
        post.setHeader("X-Lemonban-Media-Type","lemonban.v1");
        post.setHeader("Content-Type","application/json");
        StringEntity body = new StringEntity(param, "UTF-8");
        post.setEntity(body);

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(post);
        //printBody(response);
        return response;
    }
    public static HttpResponse patch(String url,String param) throws Exception {
        HttpPatch patch = new HttpPatch(url);
        patch.setHeader("X-Lemonban-Media-Type","lemonban.v1");
        patch.setHeader("Content-Type","application/json");
        StringEntity body = new StringEntity(param, "UTF-8");
        patch.setEntity(body);

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(patch);
        //printBody(response);
        return response;
    }
    public static void printBody(HttpResponse response) throws Exception {

        System.out.println(response.getStatusLine().getStatusCode());
        Header[] headers = response.getAllHeaders();
        System.out.println(Arrays.toString(headers));
        //得到响应体，并且格式化
        HttpEntity entity = response.getEntity();
        System.out.println(EntityUtils.toString(entity));

    }
}
