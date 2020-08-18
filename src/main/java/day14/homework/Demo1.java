package day14.homework;

import org.apache.http.HttpResponse;

public class Demo1 {
    public static void main(String[] args) throws Exception {
        //用户信息
        HttpResponse result1 = HttpUtils1.get("http://api.lemonban.com/futureloan/member/2060110/info");
        HttpUtils1.printBody(result1);
        //提现
        HttpResponse result2 = HttpUtils1.post("http://api.lemonban.com/futureloan/member/withdraw", "{\"member_id\":2060110,\"amount\":500}");
        HttpUtils1.printBody(result2);
        //更新昵称
        HttpResponse result3 = HttpUtils1.patch("http://api.lemonban.com/futureloan/member/update", "{\"member_id\":2060110,\"reg_name\":\"张三\"}");
        HttpUtils1.printBody(result3);

    }
}
