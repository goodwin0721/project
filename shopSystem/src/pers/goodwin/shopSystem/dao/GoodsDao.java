package pers.goodwin.shopSystem.dao;

import java.util.List;

import pers.goodwin.shopSystem.model.Goods;

public interface GoodsDao {
	public Goods getGoods(int goodsId);
	public boolean addGoods(String name, String described, int price, int stock, String pictureUrl,String classify);
	public boolean updateGoodsStockById(int goodsId,int sum);//修改商品库存量，增加+，减少-
	public boolean updateGoodsSoldById(int goodsId,int sum);//修改商品销售数量，增加+，减少-
	public List<Goods> searchByClassify(String classify);
	public List<Goods> searchByKeyword(String keyword);
	public List<Goods> searchAll();
	public List<Goods> searchHotSale(int count);//查询热卖前count个商品
}
