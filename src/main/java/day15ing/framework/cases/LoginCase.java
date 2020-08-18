package day15ing.framework.cases;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import day15ing.framework.pojo.RegisterExcelInfo;
import day15ing.framework.pojo.WriteBackExcel;
import day15ing.framework.utils.Constants;
import day15ing.framework.utils.ExcelUtils;
import day15ing.framework.utils.HttpUtils;
import day15ing.framework.utils.UserDate;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class LoginCase extends BaseCase{
//    public static void main(String[] args) {
//        Set<String> keySet = UserDate.vars.keySet();
//        for (String key : keySet) {
//            System.out.println(key+":"+UserDate.vars.get(key));
//        }
//    }


    @Test(dataProvider = "excelDatas")
    public void LoginCase(RegisterExcelInfo caseInfo){
        //参数化
        paramsReplace(caseInfo);

        String result = HttpUtils.call(caseInfo,UserDate.default_header);
        //取出token
        getParamsInUserData(result,"$.token","token");
        //getParamsInUserData(result,"$.data.id","${member_id}");
        //回写数据到excel
        addContent(sheetIndex,caseInfo.getCaseId(), Constants.RESPONSE_CELL_NUM,result);
        //获取断言结果
        boolean responseAssert = responseAssert(caseInfo.getExpectResult(), result);
        String assertResult=responseAssert?"PASSED":"FAILED";
        //回写数据到excel
        addContent(sheetIndex,caseInfo.getCaseId(),Constants.ASSERT_CELL_NUM,assertResult);
        //报表断言
        Assert.assertEquals(assertResult,"PASSED");
    }




    @DataProvider
    public Object[] excelDatas(){
        List list = ExcelUtils.readExcel(sheetIndex, 1, RegisterExcelInfo.class);
        return list.toArray();
    }

}
