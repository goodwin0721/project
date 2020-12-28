package pers.goodwin.shopSystem.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCTest {
	private JdbcTemplate jdbcTemplate;
    private ApplicationContext context = null;

    //初始化连接池
    {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
    }

    //测试是否连接数据库
    @Test
    public void testIsConnect() throws SQLException {
        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println("连接成功"+dataSource.getConnection());
    }
}
