package pers.goodwin.shopSystem.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.goodwin.shopSystem.dao.ClassifyDao;
import pers.goodwin.shopSystem.dao.GoodsDao;
import pers.goodwin.shopSystem.dao.PictureDao;
import pers.goodwin.shopSystem.model.Goods;
import pers.goodwin.shopSystem.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {

	@Override
	public List<Goods> searchByLClassify(String classify) {
		List<Goods> goodslist = new ArrayList<Goods>();
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GoodsDao goodsDao = (GoodsDao) context.getBean("goodsDao");
		// 这里搞错了，忘了在数据库分类填的是中文 - -!
		/*
		 * //String[] classifyList =
		 * {"active","toy","stationery","snacks","vegetables","meat","fruits"}; String
		 * classifyList = "active,toy,stationery,snacks,vegetables,meat,fruits";
		 * if(classify.equals("all")) { System.out.println("all"); goodslist =
		 * goodsDao.searchAll(); } else if ( !classify.contains(",") &&
		 * classifyList.contains(classify)) { System.out.println(classify); try {
		 * goodslist = goodsDao.searchByClassify("active");
		 * }catch(EmptyResultDataAccessException e) { System.err.println(e); return
		 * null; } }else goodslist = null;
		 */
		((ClassPathXmlApplicationContext)context).close();
		switch (classify) {
		case "all": {
			goodslist = goodsDao.searchAll();
			break;
		}
		case "active": {
			goodslist = goodsDao.searchByClassify("生活用品");
			break;
		}
		case "toy": {
			goodslist = goodsDao.searchByClassify("玩具");
			break;
		}
		case "stationery": {
			goodslist = goodsDao.searchByClassify("文具");
			break;
		}
		case "snacks": {
			goodslist = goodsDao.searchByClassify("零食");
			break;
		}
		case "vegetables": {
			goodslist = goodsDao.searchByClassify("蔬菜");
			break;
		}
		case "meat": {
			goodslist = goodsDao.searchByClassify("肉类");
			break;
		}
		case "fruits": {
			goodslist = goodsDao.searchByClassify("水果");
			break;
		}
		default:
			return null;
		}
		return goodslist;
	}

	@Override
	public List<Goods> searchByKeyword(String keyword) {
		if (keyword == null || keyword.trim().length() == 0)
			return null;
		List<Goods> goodslist = new ArrayList<Goods>();
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GoodsDao goodsDao = (GoodsDao) context.getBean("goodsDao");
		goodslist = goodsDao.searchByKeyword(keyword);
		((ClassPathXmlApplicationContext)context).close();
		return goodslist;
	}

	@Override
	public List<Goods> getHotSale(int count) {
		List<Goods> goodslist = new ArrayList<Goods>();
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GoodsDao goodsDao = (GoodsDao) context.getBean("goodsDao");
		goodslist = goodsDao.searchHotSale(count);
		((ClassPathXmlApplicationContext)context).close();
		return goodslist;
	}

	@Override
	public List<Goods> getAll() {
		List<Goods> goodslist = new ArrayList<Goods>();
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GoodsDao goodsDao = (GoodsDao) context.getBean("goodsDao");
		goodslist = goodsDao.searchAll();
		((ClassPathXmlApplicationContext)context).close();
		return goodslist;
	}

	@Override
	public boolean addGoods(String name, String describe, String price, String stock, String classify,
			String base64Img) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GoodsDao goodsDao = (GoodsDao) context.getBean("goodsDao");
		int gPrice = Integer.parseInt(price);
		int gStock = Integer.parseInt(stock);
		// 把图片存入，返回图片地址
		PictureDao pictureDao = (PictureDao) context.getBean("pictureDao");
		String pictureUrl = pictureDao.addPictureByBase64(base64Img);	//将图片base64转格式存起来，获得文件地址
		//System.out.println(pictureUrl);
		// 存入商品
		boolean flag1 = goodsDao.addGoods(name, describe, gPrice, gStock, pictureUrl, classify);
		// 该分类下的商品总数+1
		ClassifyDao classifyDao = (ClassifyDao) context.getBean("classifyDao");
		boolean flag2 = classifyDao.increaseSum(classify, 1);
		((ClassPathXmlApplicationContext)context).close();
		if(flag1 && flag2)
			return true;
		else 
			return false;
	}

	@Override
	public boolean updateGoodsStockById(String goodsId, String count) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GoodsDao goodsDao = (GoodsDao) context.getBean("goodsDao");
		int id = Integer.parseInt(goodsId);
		int cou = Integer.parseInt(count);
		((ClassPathXmlApplicationContext)context).close();
		return goodsDao.updateGoodsStockById(id, cou);
	}
}
