package day15ing.framework.utils;

import org.apache.log4j.Logger;

public class Test {
    private static Logger logger=Logger.getLogger(Test.class);

    public static void main(String[] args) {
        logger.info(123);
        System.out.println("修改一点东西");
        System.out.println("公司修改一点东西");
    }


}
