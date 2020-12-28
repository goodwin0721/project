package pers.goodwin.shopSystem.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.goodwin.shopSystem.mapper.GoodsMapper;
import pers.goodwin.shopSystem.model.Goods;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class GoodsMapperTest {
	@Resource(name = "goodsMapper")
	GoodsMapper goodsMapper;
	
	@Test
	public void test_addGoods() {
		Goods goods = new Goods();
		goods.setId(0000);
		goods.setName("测试示例");
		goods.setPictureUrl("123");
		goods.setPrice(11);
		goods.setStock(100);
		goods.setDescribed("123");
		goodsMapper.addGoods(goods );
	}
	
	@Test
	public void test_getGoods() {
		Goods goods = goodsMapper.getGoods(7001);
		System.out.println(goods);
	}
	
	@Test
	public void test_getGoodsByClassifyId() {
		List<Goods> goodslist = goodsMapper.getGoodsByClassifyId(Integer.valueOf(70));
		for (Goods goods : goodslist) {
			System.out.println(goods);
		}
	}
	
	@Test
	public void test_getGoodsByKeyword() {
		List<Goods> goodslist = goodsMapper.getGoodsByKeyword("牙膏");
		for (Goods goods : goodslist) {
			System.out.println(goods);
		}
	}
	
	@Test
	public void test_getAllGoods() {
		List<Goods> goodslist = goodsMapper.getAllGoods();
		for (Goods goods : goodslist) {
			System.out.println(goods);
		}
	}
	
	@Test
	public void test_getHotSale() {
		List<Goods> goodslist = goodsMapper.getHotSale(10);
		for (Goods goods : goodslist) {
			System.out.println(goods);
		}
	}
	
	@Test
	public void test_addGoodsSoldById() {
		goodsMapper.addGoodsSoldById(7001, 1);
	}

	@Test
	public void test_addGoodsStockById() {
		goodsMapper.addGoodsStockById(7003, 1);
	}
	
	@Test
	public void test_subGoodsStockById() {
		goodsMapper.subGoodsStockById(7001, 1);
	}
	
}
