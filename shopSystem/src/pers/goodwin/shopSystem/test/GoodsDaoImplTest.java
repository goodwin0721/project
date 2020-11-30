package pers.goodwin.shopSystem.test;

import java.util.List;

import org.junit.Test;

import pers.goodwin.shopSystem.dao.Impl.GoodsDaoImpl;
import pers.goodwin.shopSystem.model.Goods;

public class GoodsDaoImplTest {
	@Test
	public void getGoodsTest() {
		System.out.println(new GoodsDaoImpl().getGoods(2004));
		System.out.println(new GoodsDaoImpl().getGoods(2006));
	}

	@Test
	public void getClassifyIdAndSumTest() {
		int[] a = new GoodsDaoImpl().getClassifyIdAndSum("水果");
		for (int i : a)
			System.out.println(i);
	}

	@Test
	public void setIdTest() {
		System.out.println(new GoodsDaoImpl().setId("水果"));
	}

	@Test
	public void addGoodsTest() {
		//if (new GoodsDaoImpl().addGoods("香蕉", "东南亚香蕉", 2, 100, "goodsPictures/1.jpg", "水果"))	
		if (new GoodsDaoImpl().addGoods("椰子汁", "椰树牌椰子汁真好喝！", 5, 200, "goodsPictures/1.jpg", "零食"))
			System.out.println("yeah");
		else
			System.out.println("shit");
	}

	@Test
	public void updateGoodsStockByIdTest() {
		if (new GoodsDaoImpl().updateGoodsStockById(1001, -5))
			System.out.println("yeah");
		else
			System.out.println("shit");
	}
	
	@Test
	public void updateGoodsSoldByIdTest() {
		if (new GoodsDaoImpl().updateGoodsSoldById(2001, 10))
			System.out.println("yeah");
		else
			System.out.println("shit");
	}

	@Test
	public void searchByClassifyTest() {
		List<Goods> list = new GoodsDaoImpl().searchByClassify("生活用品");
		for (Goods g : list) {
			System.out.println(g);
		}
	}

	@Test
	public void searchByKeywordTest() {
		List<Goods> list = new GoodsDaoImpl().searchByKeyword("笔");
		for (Goods g : list) {
			System.out.println(g);
		}
		/*
		输出：
		Goods [id=3001, name=圆珠笔, described=黑色签字笔, price=1000, stock=2, pictureUrl=goodsPictures/1.jpg, sold=0]
		Goods [id=3002, name=笔记本, described=16K笔记本, price=1000, stock=2, pictureUrl=goodsPictures/1.jpg, sold=0]
		*/
	}

	@Test
	public void searchHotSaleTest() {
		List<Goods> list = new GoodsDaoImpl().searchHotSale(5);
		for (Goods g : list) {
			System.out.println(g);
		}
	}

	@Test
	public void StringTest() {
		Integer a = 1;
		Integer b = 1;
		String c = a.toString() + "0" + b.toString();
		System.out.println(c);
		int d = Integer.parseInt(c);
		System.out.println(d);
		String e = a.toString() + b.toString();
		System.out.println(e);
	}
}
