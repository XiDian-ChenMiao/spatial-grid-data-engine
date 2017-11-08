package com.xidian.spatial.tools;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 文件描述：基本测试类
 * 创建作者：陈苗
 * 创建时间：2017/11/7 17:26
 */
public class BaseTest {
    @Test
    public void testSqlite() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:src/test/resources/test.sqlite");
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE TABLE user(name VARCHAR(60), age INT );");
            statement.executeUpdate("INSERT INTO user(name, age) VALUES ('daqinzhidi', 24);");
            statement.executeUpdate("INSERT INTO user(name, age) VALUES ('XiDian-ChenMiao', 24);");
            ResultSet rs = statement.executeQuery("SELECT * FROM user;");
            while (rs.next()) {
                System.out.println("name -> " + rs.getString("name") + ", age -> " + rs.getInt("age"));
            }
            rs.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}