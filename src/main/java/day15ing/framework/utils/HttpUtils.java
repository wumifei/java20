package day15ing.framework.utils;

import com.alibaba.fastjson.JSONObject;
import day15ing.framework.pojo.RegisterExcelInfo;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
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
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpUtils {
    private static Logger logger=Logger.getLogger(HttpUtils.class);
    /**
     * 处理get请求
     * @param url
     * @return
     * @throws Exception
     */
    public static String get(String url,Map<String,String> headers) throws Exception {
        //创建一个get请求
        HttpGet get = new HttpGet(url);
        //设置请求头
        setHeaders(headers,get);
        //get.setHeader("X-Lemonban-Media-Type","lemonban.v1");
        //创建一个HttpClient客户端
        HttpClient client = HttpClients.createDefault();
        //发送请求
        HttpResponse response = client.execute(get);
        return printBody(response);
        //return EntityUtils.toString(response.getEntity());
    }

    /**
     * 处理post请求
     * @param url
     * @param param
     * @param headers
     * @return
     * @throws Exception
     */
    public static String post(String url, String param, Map<String,String> headers) throws Exception {
        HttpPost post = new HttpPost(url);
//        post.setHeader("X-Lemonban-Media-Type","lemonban.v1");
//        post.setHeader("Content-Type","application/json");
        //设置请求头
        setHeaders(headers,post);
        //设置请求体
        StringEntity body = new StringEntity(param, "UTF-8");
        post.setEntity(body);
        //创建发送http请求的客户端
        CloseableHttpClient client = HttpClients.createDefault();
        //发送请求，并且返回结果
        CloseableHttpResponse response = client.execute(post);
        return printBody(response);
        //return EntityUtils.toString(response.getEntity());
    }

    /**
     * 处理patch请求
     * @param url
     * @param param
     * @param headers
     * @return
     * @throws Exception
     */
    public static String patch(String url,String param,Map<String,String> headers) throws Exception {
        HttpPatch patch = new HttpPatch(url);
//        patch.setHeader("X-Lemonban-Media-Type","lemonban.v1");
//        patch.setHeader("Content-Type","application/json");
        //设置请求头
        setHeaders(headers,patch);
        //设置请求体
        StringEntity body = new StringEntity(param, "UTF-8");
        patch.setEntity(body);

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(patch);
        return printBody(response);
        //return EntityUtils.toString(response.getEntity());
    }

    /**
     * 打印返回结果
     * @param response
     * @throws Exception
     */
    public static String printBody(HttpResponse response) throws Exception {
        //System.out.println(response.getStatusLine().getStatusCode());
        logger.info(response.getStatusLine().getStatusCode());
        Header[] headers = response.getAllHeaders();
        //System.out.println(Arrays.toString(headers));
        logger.info(Arrays.toString(headers));
        //得到响应体，并且格式化
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        //System.out.println(body);
        logger.info(body);
        return body;
    }

    /**
     * 设置请求头
     * @param headers
     * @param request
     */
    public static void setHeaders(Map<String,String> headers, HttpRequest request){
        Set<String> keySet = headers.keySet();
        for (String key : keySet) {
            request.setHeader(key,headers.get(key));
        }
    }

    /**
     * 分配请求
     * @param caseInfo
     */
    public static String call(RegisterExcelInfo caseInfo,Map<String,String> headers){
        String responseBody="";
        try {
            String params=caseInfo.getParams();
            if("post".equals(caseInfo.getType())){
                if("json".equals(caseInfo.getContent_type())){
                }else if("form".equals(caseInfo.getContent_type())){
                    headers.put("Content-Type","application/x-www-form-urlencoded");
                    //json先转map，再拼接成字符串
                    params = jsonToString(caseInfo);
                }
                responseBody = post(caseInfo.getUrl(), params,headers);

            }else if("get".equals(caseInfo.getType())){
                responseBody = get(caseInfo.getUrl(), headers);

            }else if("patch".equals(caseInfo.getType())){
                if("json".equals(caseInfo.getContent_type())){
                }else if("form".equals(caseInfo.getContent_type())){
                    headers.put("Content-Type","application/x-www-form-urlencoded");
                    //json转字符串
                    params=jsonToString(caseInfo);
                }
                responseBody=patch(caseInfo.getUrl(),params,headers);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseBody;
    }

    /**
     * json先转map，再拼接成key=value&字符串
     * @param caseInfo
     * @return
     */
    public static String jsonToString(RegisterExcelInfo caseInfo){
        String formParams="";
        Map<String,String> map = JSONObject.parseObject(caseInfo.getParams(), Map.class);
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            //拼接参数
            formParams=formParams+key+"="+map.get(key)+"&";
        }
        //去掉最后一个&
        return formParams.substring(0,formParams.length()-1);
        //System.out.println(params);
    }


}
