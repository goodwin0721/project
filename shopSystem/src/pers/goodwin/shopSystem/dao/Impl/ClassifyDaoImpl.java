package pers.goodwin.shopSystem.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import pers.goodwin.shopSystem.dao.ClassifyDao;
import pers.goodwin.shopSystem.model.GoodsClassify;

public class ClassifyDaoImpl implements ClassifyDao {
	// 声明一个JdbcTmplate属性及其Setter方法
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean increaseSum(String classify, int sum) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "UPDATE t_goodsclassify set sum = sum + ? WHERE classify = ?;";
		int num = jdbcTemplate.update(sql, sum, classify);
		((ClassPathXmlApplicationContext)context).close();
		if (num == 0)
			return false;
		return true;
	}

	@Override
	public GoodsClassify getClassify(String classify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSum(String classify) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getClassifyId(String classify) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "select * from t_goodsclassify where classify = ?";
		RowMapper<GoodsClassify> classifyRowMapper = new BeanPropertyRowMapper<GoodsClassify>(GoodsClassify.class);
		GoodsClassify goodsClassify;
		try {
			goodsClassify = jdbcTemplate.queryForObject(sql, classifyRowMapper, classify);
		}catch(Exception e) {
			System.out.println(e);
			((ClassPathXmlApplicationContext)context).close();
			return -1;
		}
		((ClassPathXmlApplicationContext)context).close();
		return goodsClassify.getId();
	}

}
