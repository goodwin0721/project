package pers.goodwin.shopSystem.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import pers.goodwin.shopSystem.dao.Impl.UserDaoImpl;

public class UserDaoImplTest {

	private JdbcTemplate jdbcTemplate;
    private ApplicationContext context = null;
    


	
	@Test
	public void addUserTest() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		// 定义SQL
		String sql = "insert into t_user(username,password,birthdate,gender) value(?,?,?,?)";
		// 定义数组来存放SQL语句中的参数
		//Object[] obj = new Object[] { username,password,birthdate,gender};
		// 执行添加操作，返回受SQL语句影响的条数
		jdbcTemplate.update(sql,"goodwin","123456","1996-07-21",0);
		
	}
	
	@Test
	public void addUserTest2() {
		if(new UserDaoImpl().addUser("Donald Trump","idiot","1946-06-14",0))
			System.out.println("yeah");
		else System.out.println("shit");
	}
	
	@Test
	public void addManagerTest() {
		if(new UserDaoImpl().addManager("goodwin"))
			System.out.println("yeah");
		else System.out.println("shit");
	}
	
	@Test
	public void isExistTest() {
		if(new UserDaoImpl().isExist("goodwin2"))
			System.out.println("yeah");
		else System.out.println("shit");
		if(new UserDaoImpl().isExist("goodwin23"))
			System.out.println("yeah");
		else System.out.println("shit");
	}
	
	@Test
	public void getUserByPasswordTest() {
		System.out.println(new UserDaoImpl().getUserByPassword("goodwin", "123456"));
		System.out.println(new UserDaoImpl().getUserByPassword("goodwin", "12345"));
		System.out.println(new UserDaoImpl().getUserByPassword("goodwinnn", "123456"));
	}
	
	@Test
	public void isManagerTest() {
		System.out.println(new UserDaoImpl().isManager("goodwin"));
		System.out.println(new UserDaoImpl().isManager("goodwinnnn"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}