package pers.goodwin.shopSystem.service;

import java.util.List;

import pers.goodwin.shopSystem.model.Goods;

public interface GoodsService {
	public boolean addGoods(String name, String describe, String price, String stock, String classify,String base64Img);
	public boolean updateGoodsStockById(String goodsId,String count);
	public List<Goods> searchByLClassify(String classify);
	public List<Goods> searchByKeyword(String keyword);
	public List<Goods> getAll();
	public List<Goods> getHotSale(int count);//获得count个热销商品的列表
}
