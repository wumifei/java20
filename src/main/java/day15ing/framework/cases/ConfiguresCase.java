package day15ing.framework.cases;


import day15ing.framework.pojo.RegisterExcelInfo;
import day15ing.framework.utils.Constants;
import day15ing.framework.utils.ExcelUtils;
import day15ing.framework.utils.HttpUtils;
import day15ing.framework.utils.UserDate;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;


public class ConfiguresCase extends BaseCase{
    @Test(dataProvider = "excelDatas")
    public void LoginCase(RegisterExcelInfo caseInfo){

        paramsReplace(caseInfo);

        //获取鉴权头
        HashMap<String, String> headers = getAuthorizationHeaders();
        String result = HttpUtils.call(caseInfo,headers);

        addContent(sheetIndex,caseInfo.getCaseId(), Constants.RESPONSE_CELL_NUM,result);
        boolean responseAssert = responseAssert(caseInfo.getExpectResult(), result);
        String assertResult=responseAssert?"PASSED":"FAILED";
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
