package pers.goodwin.shopSystem.dao.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import pers.goodwin.shopSystem.dao.CartDao;
import pers.goodwin.shopSystem.model.Cart;
import pers.goodwin.shopSystem.model.Goods;
import pers.goodwin.shopSystem.model.User;

public class CartDaoImpl implements CartDao {
	// 声明一个JdbcTmplate属性及其Setter方法
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	// 用户user添加amount件goods到购物车
	public boolean addGoodsToCart(User user, Goods goods, int amount) {
		String sql = "INSERT into t_cart(userId,goodsId,amount) value(?,?,?);";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		int num = jdbcTemplate.update(sql, user.getId(), goods.getId(), amount);
		((ClassPathXmlApplicationContext)context).close();
		if (num != 0) {
			return true;
		}
		return false;
	}

	// 用户user添加amount件goods到购物车
	public boolean addGoodsToCart(int userId, int goodsId, int amount) {
		String sql = "INSERT into t_cart(userId,goodsId,amount) value(?,?,?);";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		int num = jdbcTemplate.update(sql, userId, goodsId, amount);
		((ClassPathXmlApplicationContext)context).close();
		if (num != 0) {
			return true;
		}
		return false;
	}

	@Override
	//修改user购物车中goods的数量
	public boolean updateGoodsAmount(User user, Goods goods, int amount) {
		String sql = "UPDATE t_cart set amount = ? WHERE userId = ? and goodsId = ?";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		int num = jdbcTemplate.update(sql, amount, user.getId(), goods.getId());
		((ClassPathXmlApplicationContext)context).close();
		if (num != 0) {
			return true;
		}
		return false;
	}

	@Override
	//修改user购物车中goods的数量
	public boolean updateGoodsAmount(int userId, int goodsId, int amount) {
		String sql = "UPDATE t_cart set amount = ? WHERE userId = ? and goodsId = ?";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		int num = jdbcTemplate.update(sql, amount, userId, goodsId);
		((ClassPathXmlApplicationContext)context).close();
		if (num != 0) {
			return true;
		}
		return false;
	}
	
	@Override
	// 通过user查询user的购物车信息
	public List<Cart> searchByUser(User user) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "select * from t_cart WHERE userId = ?;";
		((ClassPathXmlApplicationContext)context).close();
		// 创建一个新的BeanPropertyRowMapper对象
		RowMapper<Cart> rowMapper = new BeanPropertyRowMapper<Cart>(Cart.class);
		// 执行静态查询的sql，通过RowMapper返回结果
		try {
			return this.jdbcTemplate.query(sql, rowMapper, user.getId());
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	@Override
	// 通过userId查询user的购物车信息
	public List<Cart> searchByUserId(int userId) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "select * from t_cart WHERE userId = ?;";
		// 创建一个新的BeanPropertyRowMapper对象
		RowMapper<Cart> rowMapper = new BeanPropertyRowMapper<Cart>(Cart.class);
		((ClassPathXmlApplicationContext)context).close();
		// 执行静态查询的sql，通过RowMapper返回结果
		try {
			return this.jdbcTemplate.query(sql, rowMapper, userId);
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	@Override
	public boolean removeGoodsFromCart(int userId, int goodsId) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "DELETE FROM t_cart WHERE userId = ? and goodsId = ?;";
		// 创建一个新的BeanPropertyRowMapper对象
		int num = jdbcTemplate.update(sql, userId, goodsId);
		((ClassPathXmlApplicationContext)context).close();
		if (num != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean removeAllGoodsByUserId(int userId) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "DELETE FROM t_cart WHERE userId = ?;";
		// 创建一个新的BeanPropertyRowMapper对象
		int num = jdbcTemplate.update(sql, userId);
		((ClassPathXmlApplicationContext)context).close();
		if (num != 0) {
			return true;
		}
		return false;
	}

}
