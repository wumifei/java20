package day15ing.framework.cases;

import com.alibaba.fastjson.JSONPath;
import com.mysql.jdbc.StringUtils;
import day15ing.framework.pojo.RegisterExcelInfo;
import day15ing.framework.pojo.WriteBackExcel;
import day15ing.framework.utils.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class RechargeCase extends BaseCase{


    @Test(dataProvider = "excelDatas")
    public void LoginCase(RegisterExcelInfo caseInfo){

        paramsReplace(caseInfo);
        String sql = caseInfo.getSql();
        BigDecimal before = (BigDecimal)SqlUtils.selectToSinger(sql);
        //获取鉴权头
        HashMap<String, String> headers = getAuthorizationHeaders();
        String result = HttpUtils.call(caseInfo, headers);
        addContent(sheetIndex,caseInfo.getCaseId(), Constants.RESPONSE_CELL_NUM,result);
        boolean responseAssert = responseAssert(caseInfo.getExpectResult(), result);

        BigDecimal after = (BigDecimal)SqlUtils.selectToSinger(sql);

        if(!StringUtils.isNullOrEmpty(sql)){
            String amountStr = JSONPath.read(caseInfo.getParams(), "$.amount").toString();
            if(org.apache.commons.lang3.StringUtils.isNumeric(amountStr)){
                BigDecimal amount = new BigDecimal(amountStr);
                System.out.println("before = " + before);
                System.out.println("after = " + after);
                System.out.println("amount = " + amount);
                if((after.subtract(before)).compareTo(amount)==0){
                    System.out.println("断言成功，测试通过");
                }else {
                    System.out.println("断言失败，测试不通过");
                }
            }
        }
        //回写断言结果到excel
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
