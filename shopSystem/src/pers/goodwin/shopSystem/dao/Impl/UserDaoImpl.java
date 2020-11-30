package pers.goodwin.shopSystem.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import pers.goodwin.shopSystem.dao.UserDao;
import pers.goodwin.shopSystem.model.Manager;
import pers.goodwin.shopSystem.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	// 声明一个JdbcTmplate属性及其Setter方法
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public User getUserByPassword(String username, String password) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String getUserSql = "select * from t_user where username = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		User user;
		((ClassPathXmlApplicationContext)context).close();
		try {
			user = jdbcTemplate.queryForObject(getUserSql, rowMapper, username);//获得该用户
		}catch(Exception exception) {
			return null;
		}
		return user.getPassword().equals(password) ? user : null;
	}
	
	@Override
	// 添加用户
	public boolean addUser(String username, String password, String birthdate, int gender) {
		// 定义SQL
		String sql = "insert into t_user(username,password,birthdate,gender) value(?,?,?,?)";
		// 定义数组来存放SQL语句中的参数
		// Object[] obj = new Object[] { username,password,birthdate,gender};
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		// 执行添加操作，返回受SQL语句影响的条数
		int num = jdbcTemplate.update(sql, username, password, birthdate, gender);
		((ClassPathXmlApplicationContext)context).close();
		if (num == 0)
			return false;
		return true;
	}
	
	//添加管理员
	@Override
	public boolean addManager(String username) {
		if(!isExist(username))
			return false;
		// 定义数组来存放SQL语句中的参数
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String getUserSql = "select * from t_user where username = ?";
		/*
		 *从数据库中获取一条记录，实际是得到对应的一个对象 
		 *RowMapper:行的映射
		 *Spring 2.5 提供了一个便利的RowMapper实现-----BeanPropertyRowMapper
		 *它可自动将一行数据映射到指定类的实例中 它首先将这个类实例化，然后通过名称匹配的方式，映射到属性中去。
		 *字段                                  bean属性
		 *USER_NAME --> userName
		 *USER_ID   --> userId
		 */
		// 创建一个新的BeanPropertyRowMapper对象
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		User user = jdbcTemplate.queryForObject(getUserSql, rowMapper, username);//获得该用户
		int userId = user.getId();
		// 定义SQL
		String sql = "insert into t_manager(userId) value(?)";
		// 执行添加操作，返回受SQL语句影响的条数
		int num = jdbcTemplate.update(sql, userId);
		((ClassPathXmlApplicationContext)context).close();
		if (num == 0)
			return false;
		return true;
	}

	//判断用户是否存在
	@Override
	public boolean isExist(String username) {
		String sql = "select * from t_user where username = ?";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		// 创建一个新的BeanPropertyRowMapper对象
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		((ClassPathXmlApplicationContext)context).close();
		try {
			//这里的queryForObject查到的数据只能是1行，0或者1以上都不行，都会抛异常，异常就说明查不到
			jdbcTemplate.queryForObject(sql, rowMapper, username);//查到该用户
		}catch(Exception exception) {
			return false;
		}
		return true;	
	}
	
	//判断是否是管理员
	@Override
	public boolean isManager(String username) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "select id from t_user where username = ?";
		// 创建一个新的BeanPropertyRowMapper对象
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		((ClassPathXmlApplicationContext)context).close();
		User user;
		try {
			user = jdbcTemplate.queryForObject(sql, rowMapper, username);//查到该用户
		}catch(Exception exception) {
			return false;
		}
		int id = user.getId();
		String sql2 = "select * from t_manager where userId = ?";
		RowMapper<Manager> rowMapperManager = new BeanPropertyRowMapper<Manager>(Manager.class);
		try {
			jdbcTemplate.queryForObject(sql2, rowMapperManager, id);
		}catch(Exception exception2) {
			//System.err.println(exception2);
			return false;
		}
		//查不到就抛异常返回false了
		return true;
	}

}
