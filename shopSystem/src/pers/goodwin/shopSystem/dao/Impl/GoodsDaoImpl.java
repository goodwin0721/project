package pers.goodwin.shopSystem.dao.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import pers.goodwin.shopSystem.dao.ClassifyDao;
import pers.goodwin.shopSystem.dao.GoodsDao;
import pers.goodwin.shopSystem.model.Goods;
import pers.goodwin.shopSystem.model.GoodsClassify;

/**
 * @author goodwin
 *
 */
public class GoodsDaoImpl implements GoodsDao {
	// 声明一个JdbcTmplate属性及其Setter方法
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Goods getGoods(int goodsId) {
		String sql = "select * from t_goods WHERE id = ?;";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		RowMapper<Goods> rowMapper = new BeanPropertyRowMapper<Goods>(Goods.class);
		Goods goods;
		try {
			goods = jdbcTemplate.queryForObject(sql, rowMapper, goodsId);
		} catch (Exception e) {
			System.err.println(e);
			((ClassPathXmlApplicationContext)context).close();
			return null;
		}
		((ClassPathXmlApplicationContext)context).close();
		return goods;
	}

	@Override
	public boolean addGoods(String name, String described, int price, int stock, String pictureUrl, String classify) {
		int id = setId(classify);
		String sql = "insert into t_goods(id,name,described,price,stock,pictureUrl,sold) value(?,?,?,?,?,?,0)";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		// 执行添加操作，返回受SQL语句影响的条数
		int num = jdbcTemplate.update(sql, id, name, described, price, stock, pictureUrl);
		((ClassPathXmlApplicationContext)context).close();
		if (num != 0) {
			// 增加成功了，该分类下的商品总数也应该增加
			// 这个写在业务层
			//ClassifyDao cls = (ClassifyDao) context.getBean("classifyDao");
			//cls.increaseSum(classify, 1);
			return true;
		}
		return false;
	}

	// 获得某分类下的商品分类编号和总数
	public int[] getClassifyIdAndSum(String classify) {
		int[] data = new int[2];
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "select * from t_goodsclassify WHERE classify = ?;";
		RowMapper<GoodsClassify> rowMapper = new BeanPropertyRowMapper<GoodsClassify>(GoodsClassify.class);
		GoodsClassify goodsClassify;
		try {
			goodsClassify = jdbcTemplate.queryForObject(sql, rowMapper, classify);
		} catch (Exception e) {
			System.err.println(e);
			((ClassPathXmlApplicationContext)context).close();
			return null;
		}
		data[0] = goodsClassify.getId();
		data[1] = goodsClassify.getSum();
		((ClassPathXmlApplicationContext)context).close();
		return data;
	}

	// 设置商品id
	public int setId(String classify) {
		int id;
		int[] data = getClassifyIdAndSum(classify);
		Integer idHead = data != null && data[0] != 0 ? data[0] : 99;
		String temp = "0";
		if (data != null && data[1] >= 0) {
			Integer idTail = data[1] + 1;
			if (idTail < 10)
				temp = idHead.toString() + "0" + idTail.toString();
			else
				temp = idHead.toString() + idTail.toString();
				// System.out.println(temp);
			try {
				id = Integer.parseInt(temp);
			} catch (Exception e) {
				System.err.println(e);
				return Integer.MAX_VALUE;
			}
		} else
			id = Integer.MAX_VALUE;
		return id;
	}

	// 修改商品库存量，增加+，减少-
	@Override
	public boolean updateGoodsStockById(int goodsId, int sum) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "UPDATE t_goods set stock = stock + ? WHERE id = ?";
		int num = jdbcTemplate.update(sql, sum, goodsId);
		((ClassPathXmlApplicationContext)context).close();
		if (num == 0)
			return false;
		return true;
	}

	@Override
	public boolean updateGoodsSoldById(int goodsId, int sum) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "UPDATE t_goods set sold = sold + ? WHERE id = ?";
		int num = jdbcTemplate.update(sql, sum, goodsId);
		((ClassPathXmlApplicationContext)context).close();
		if (num == 0)
			return false;
		return true;
	}

	@Override
	public List<Goods> searchByClassify(String classify) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		// 先查到分类的编号
		ClassifyDao classifyDao = (ClassifyDao) context.getBean("classifyDao");
		int classifyId = classifyDao.getClassifyId(classify);
		//System.out.println(classifyId);
		if (classifyId != -1) {
			String sql = "select * from t_goods where stock > 0 and id like '" + classifyId + "%';";
			// 你奶奶的，被下面这个?号搞死了，不带引号就错误，带引号在下面query又匹配不到，你奶奶的我直接拼sql
			// String sql = "select * from t_goods where id like '?%';";
			// String sql = "select * from t_goods where id like ?%;";
			// 创建一个新的BeanPropertyRowMapper对象
			RowMapper<Goods> rowMapper = new BeanPropertyRowMapper<Goods>(Goods.class);
			// 执行静态查询的sql，通过RowMapper返回结果
			try {
				((ClassPathXmlApplicationContext)context).close();
				return this.jdbcTemplate.query(sql, rowMapper);
				// return this.jdbcTemplate.query(sql, rowMapper, classifyId);
				// return this.jdbcTemplate.query(sql, rowMapper, String.valueOf(classifyId));
			} catch (Exception e) {
				System.err.println(e);
				((ClassPathXmlApplicationContext)context).close();
				return null;
			}

		}
		((ClassPathXmlApplicationContext)context).close();
		return null;
	}

	@Override
	public List<Goods> searchByKeyword(String keyword) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "select * from t_goods where stock > 0 and name like '%" + keyword + "%' or described like '%" + keyword + "%';";
		//跟上面一个错误
		//String sql = "select * from t_goods where stock > 0 and name like %?% or described like %?% ";
		// 创建一个新的BeanPropertyRowMapper对象
		RowMapper<Goods> rowMapper = new BeanPropertyRowMapper<Goods>(Goods.class);
		// 执行静态查询的sql，通过RowMapper返回结果
		((ClassPathXmlApplicationContext)context).close();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	// 根据销量返回已售数量最多且库存最多的count件商品
	@Override
	public List<Goods> searchHotSale(int count) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "select * from t_goods ORDER BY sold desc,stock desc LIMIT ?; ";
		// 创建一个新的BeanPropertyRowMapper对象
		RowMapper<Goods> rowMapper = new BeanPropertyRowMapper<Goods>(Goods.class);
		((ClassPathXmlApplicationContext)context).close();
		// 执行静态查询的sql，通过RowMapper返回结果
		return this.jdbcTemplate.query(sql, rowMapper, count);
	}

	@Override
	public List<Goods> searchAll() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "select * from t_goods;";
		//跟上面一个错误
		//String sql = "select * from t_goods where name like %?% or described like %?% ";
		// 创建一个新的BeanPropertyRowMapper对象
		RowMapper<Goods> rowMapper = new BeanPropertyRowMapper<Goods>(Goods.class);
		((ClassPathXmlApplicationContext)context).close();
		// 执行静态查询的sql，通过RowMapper返回结果
		return this.jdbcTemplate.query(sql, rowMapper);
	}

}
