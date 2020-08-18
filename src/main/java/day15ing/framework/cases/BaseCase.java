package day15ing.framework.cases;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import day15ing.framework.pojo.RegisterExcelInfo;
import day15ing.framework.pojo.WriteBackExcel;
import day15ing.framework.utils.ExcelUtils;
import day15ing.framework.utils.UserDate;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BaseCase {

    public int sheetIndex;

    @BeforeClass
    @Parameters({"sheetIndex"})
    public void beforeClass(int sheetIndex){
        this.sheetIndex=sheetIndex;
    }

    @AfterSuite
    public void finish(){
        System.out.println("======finish======");
        //执行一次回写操作
        ExcelUtils.writeBackExcel();
    }

    /**
     * 添加要回写的内容到list
     * @param sheetIndex
     * @param rowNum
     * @param cellNum
     * @param result
     */
    public void addContent(int sheetIndex,int rowNum,int cellNum,String result) {
        WriteBackExcel backExcel = new WriteBackExcel(sheetIndex,rowNum,cellNum,result);
        //添加对象到list集合
        ExcelUtils.list.add(backExcel);
    }

    /**
     * 从响应体中通过jsonpath取出对应参数，存入到UserData.vars中
     * @param result
     * @param jsonPathExpression
     * @param userDataKey
     */
    public void getParamsInUserData(String result,String jsonPathExpression,String userDataKey) {
        Object token = JSONPath.read(result, jsonPathExpression);
        //把取出的token，put到map里面去
        if(token!=null){
            UserDate.vars.put(userDataKey,token);
        }
    }

    /**
     * 得到鉴权头
     * @return
     */
    public HashMap<String, String> getAuthorizationHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        //取出登录put到map的token
        Object token = UserDate.vars.get("token");
        //把token，put到头里面
        //headers.put("Authorization","Bearer "+token);
        headers.put("Authorization","JWT "+token);
        //把默认头put到头里面去
        headers.putAll(UserDate.default_header);
        return headers;
    }

    /**
     * 断言结果
     * @param expectResult
     * @param result
     */
    public boolean responseAssert(String expectResult, String result) {
        boolean actualResultFlag=true;
        //expectResult转成map
        Map<String,Object> map = JSONObject.parseObject(expectResult, Map.class);
        //遍历map
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            //获取期望值
            String expectValue = map.get(key).toString();
            //获取实际值
            String actualValue=null;
            if(null!=JSONPath.read(result, key)){
                actualValue =JSONPath.read(result, key).toString();
            }
            //断言
            System.out.println("实际值"+actualValue);
            System.out.println("期望值"+expectValue);

            if(!expectValue.equals(actualValue)){
                actualResultFlag=false;
                break;
            }
        }
        System.out.println("接口响应断言结果："+actualResultFlag);
        return actualResultFlag;
    }

    /**
     * 参数化替换
     * @param caseInfo
     */
    public void paramsReplace(RegisterExcelInfo caseInfo) {
        String sql = caseInfo.getSql();
        String expectResult = caseInfo.getExpectResult();
        String params = caseInfo.getParams();
        String url=caseInfo.getUrl();
        Set<String> keySet = UserDate.vars.keySet();
        for (String key : keySet) {
            //取出实际值
            String value = UserDate.vars.get(key).toString();
            if(StringUtils.isNotEmpty(params)){
                params=params.replace(key, value);
                caseInfo.setParams(params);
            }
            if(StringUtils.isNotEmpty(sql)){
                sql=sql.replace(key,value);
                caseInfo.setSql(sql);
            }
            if(StringUtils.isNotEmpty(expectResult)){
                expectResult=expectResult.replace(key,value);
                caseInfo.setExpectResult(expectResult);
            }
            if(StringUtils.isNotEmpty(url)){
                url=url.replace(key,value);
                caseInfo.setUrl(url);
            }
        }
    }
}
