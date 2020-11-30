package pers.goodwin.shopSystem.dao.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import pers.goodwin.shopSystem.dao.IndentDao;
import pers.goodwin.shopSystem.model.Goods;
import pers.goodwin.shopSystem.model.Indent;
import pers.goodwin.shopSystem.model.User;

public class IndentDaoImpl implements IndentDao {
	// 声明一个JdbcTmplate属性及其Setter方法
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean addGoodsToIndent(User user, Goods goods, int amount) {
		String sql = "INSERT into t_indent(userId,goodsId,price,amount,tradeTime) value(?,?,?,?,?);";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date());// 交易时间就是提交时间
		int num = jdbcTemplate.update(sql, user.getId(), goods.getId(), goods.getPrice(), amount, date);
		((ClassPathXmlApplicationContext)context).close();
		if (num != 0) {
			return true;
		}
		return false;
	}
	

	/**
	 *	@param price 交易价格
	 */
	@Override
	public boolean addGoodsToIndent(int userId, int goodsId, int price, int amount) {
		String sql = "INSERT into t_indent(userId,goodsId,price,amount,tradeTime) value(?,?,?,?,?);";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date());// 交易时间就是提交时间
		int num = jdbcTemplate.update(sql, userId, goodsId, price, amount, date);
		((ClassPathXmlApplicationContext)context).close();
		if (num != 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Indent> searchByUser(User user) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "select * from t_indent WHERE userId = ?;";
		((ClassPathXmlApplicationContext)context).close();
		// 创建一个新的BeanPropertyRowMapper对象
		RowMapper<Indent> rowMapper = new BeanPropertyRowMapper<Indent>(Indent.class);
		// 执行静态查询的sql，通过RowMapper返回结果
		try {
			return this.jdbcTemplate.query(sql, rowMapper, user.getId());
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	@Override
	public List<Indent> searchByUserId(int userId) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "select * from t_indent WHERE userId = ?;";
		((ClassPathXmlApplicationContext)context).close();
		// 创建一个新的BeanPropertyRowMapper对象
		RowMapper<Indent> rowMapper = new BeanPropertyRowMapper<Indent>(Indent.class);
		// 执行静态查询的sql，通过RowMapper返回结果
		try {
			return this.jdbcTemplate.query(sql, rowMapper, userId);
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

}
