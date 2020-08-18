package day15ing.framework.utils;

import day15ing.framework.pojo.Member;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SqlUtils {

    public static void main(String[] args) {



    }

    public static Object selectToSinger(String sql) {
        if(StringUtils.isNotBlank(sql)) {
            QueryRunner runner = new QueryRunner();
            Connection conn = DBUtils.getConnection();
            ScalarHandler<Object> handler = new ScalarHandler<Object>();
            try {
                Object query = runner.query(conn, sql, handler);
//                System.out.println(query);
                conn.close();
                return query;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void selectToBeanList() {
        QueryRunner runner = new QueryRunner();
        String sql="SELECT * FROM member limit 5;";
        Connection conn = DBUtils.getConnection();
        BeanListHandler<Member> handler = new BeanListHandler(Member.class);
        try {
            List<Member> members = runner.query(conn, sql, handler);
            for (Member member : members) {
                System.out.println(member);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectToBean() {
        QueryRunner runner = new QueryRunner();
        String sql="SELECT * FROM member WHERE mobile_phone='18912983726';";
        Connection conn = DBUtils.getConnection();
        BeanHandler<Member> handler = new BeanHandler(Member.class);
        try {
            Member member = runner.query(conn, sql, handler);
            System.out.println(member);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectToMap() {
        QueryRunner runner = new QueryRunner();
        String sql="SELECT * FROM member WHERE mobile_phone='18912983726';";
        Connection conn = DBUtils.getConnection();
        MapHandler handler = new MapHandler();
        try {
            Map<String, Object> map = runner.query(conn, sql, handler);
            System.out.println(map);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData() {
        QueryRunner runner = new QueryRunner();
        String sql="INSERT INTO member VALUES (NULL,'yanwei','123123','18912983726',1,100,NOW());";
        Connection conn = DBUtils.getConnection();
        try {
            int i = runner.update(conn,sql);
            System.out.println(i);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateData() {
        QueryRunner runner = new QueryRunner();
        String sql="UPDATE member SET reg_name=1234567893 WHERE id=2073737;";
        Connection conn = DBUtils.getConnection();
        try {
            int i = runner.update(conn,sql);
            System.out.println(i);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
