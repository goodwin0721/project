package pers.goodwin.shopSystem.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import pers.goodwin.shopSystem.pojo.Goods;

public interface GoodsMapper {
	public Goods getGoods(Integer goodsId);

	public void addGoods(Goods goods);

	//修改商品库存量，增加goods库存
	public void addGoodsStockById(@Param("goodsId")Integer goodsId,@Param("sum")Integer sum);

	//修改商品库存量，减少goods库存
	public void subGoodsStockById(@Param("goodsId")Integer goodsId,@Param("sum")Integer sum);

	//修改商品销售数量，增加已售数量
	public void addGoodsSoldById(@Param("goodsId")Integer goodsId,@Param("sum")Integer sum);


	public List<Goods> getGoodsByClassifyId(Integer classifyId);
	public List<Goods> getGoodsByKeyword(String keyword);
	public List<Goods> getAllGoods();

	//查询热卖前count个商品
	public List<Goods> getHotSale(Integer count);

	//分页查询-查询全部商品
	public List<Goods> getAllGoodsByPage(HashMap<String,Object> map);

	//分页查询-通过商品分类Id
	public List<Goods> getGoodsByClassifyIdAndPage(HashMap<String,Object> map);

	//分页查询-通过关键字
	public List<Goods> getGoodsByKeywordAndPage(HashMap<String,Object> map);


	//获取所有商品数目
	public Integer getCount();

	//获得商品数目-通过商品分类Id
	public Integer getCountByClassifyId(Integer classifyId);

	//获得商品数目-通过关键字
	public Integer getCountByKeyword(String keyword);

}
