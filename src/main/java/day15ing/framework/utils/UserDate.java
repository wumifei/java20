package day15ing.framework.utils;

import cn.binarywang.tools.generator.ChineseMobileNumberGenerator;
import cn.binarywang.tools.generator.ChineseNameGenerator;

import java.util.HashMap;
import java.util.Map;

public class UserDate {

    public static Map<String,Object> vars=new HashMap<String, Object>();

    public static Map<String,String> default_header=new HashMap<String,String>();

    static{
        default_header.put("X-Lemonban-Media-Type","lemonban.v2");
        default_header.put("Content-Type","application/json");

        //随机手机号码
        vars.put("${register_mb}", ChineseMobileNumberGenerator.getInstance().generate());
        vars.put("${username}", ChineseMobileNumberGenerator.getInstance().generate());
        vars.put("${email}", ChineseMobileNumberGenerator.getInstance().generate());

        vars.put("${projectName}",ChineseNameGenerator.getInstance().generate());


    }

}
