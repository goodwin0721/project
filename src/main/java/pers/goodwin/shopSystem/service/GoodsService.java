package pers.goodwin.shopSystem.service;

import pers.goodwin.shopSystem.pojo.Goods;
import pers.goodwin.shopSystem.pojo.GoodsDto;
import pers.goodwin.shopSystem.pojo.PageBean;

import java.util.List;

public interface GoodsService {
    public boolean addGoods(GoodsDto goodsDto);
	public boolean addGoods(String name, String describe, int price, int stock, String classify,String base64Img);
	public boolean addStockById(String goodsId,String count);
	public boolean subStockById(String goodsId,String count);
	public List<Goods> getAll();
	public List<Goods> searchByClassify(String classify);
	public List<Goods> searchByKeyword(String keyword);
//	public List<Goods> getAllByPage(int currentPage);
//	public List<Goods> searchByClassifyAndPage(String classify,int currentPage);
//	public List<Goods> searchByKeywordAndPage(String keyword,int currentPage);
//	public Integer getCount();//获取所有商品数目
//	public Integer getCountByClassify(String classify);//获得商品数目-通过商品分类Id
//	public Integer getCountByKeyword(String keyword);//获得商品数目-通过关键字

	//获得count个热销商品的列表
	public List<Goods> getHotSale();
	//分页查询-全部商品
	public PageBean<Goods> pagedQuery(int currentPage);
	//分页查询-分类查询
	public PageBean<Goods> pagedQueryByClassify(String classify,int currentPage);
	//分页查询-通过关键字
	public PageBean<Goods> pagedQueryByKeyword(String keyword,int currentPage);
}
