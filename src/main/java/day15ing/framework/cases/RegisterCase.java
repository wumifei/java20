package day15ing.framework.cases;

import day15ing.framework.pojo.RegisterExcelInfo;
import day15ing.framework.pojo.WriteBackExcel;
import day15ing.framework.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;


public class RegisterCase extends BaseCase{

        @Test(dataProvider = "excelDatas")
        public void registerCase(RegisterExcelInfo caseInfo){
                //参数化
                paramsReplace(caseInfo);
                String sql = caseInfo.getSql();
                Long before = (Long)SqlUtils.selectToSinger(sql);

                String result = HttpUtils.call(caseInfo, UserDate.default_header);
                addContent(sheetIndex,caseInfo.getCaseId(), Constants.RESPONSE_CELL_NUM,result);
                boolean responseAssert = responseAssert(caseInfo.getExpectResult(), result);

                Long after = (Long)SqlUtils.selectToSinger(sql);
                if(StringUtils.isNotBlank(sql)){
                        if(before==0&&after==1){
                                System.out.println("断言正确，测试通过");
                        }else{
                                System.out.println("断言失败，测试不通过");
                        }
                }
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
