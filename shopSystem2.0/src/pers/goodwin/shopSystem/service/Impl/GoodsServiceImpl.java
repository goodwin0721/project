package pers.goodwin.shopSystem.service.Impl;

import java.util.List;

import pers.goodwin.shopSystem.constant.ClassifyConstant;
import pers.goodwin.shopSystem.mapper.ClassifyMapper;
import pers.goodwin.shopSystem.mapper.GoodsMapper;
import pers.goodwin.shopSystem.model.Goods;
import pers.goodwin.shopSystem.model.GoodsClassify;
import pers.goodwin.shopSystem.service.GoodsService;
import pers.goodwin.shopSystem.util.PictureUtil;

public class GoodsServiceImpl implements GoodsService {
	private GoodsMapper goodsMapper;
	private ClassifyMapper classifyMapper;
	private PictureUtil pictureUtil;
	
	public void setGoodsMapper(GoodsMapper goodsMapper) {
		this.goodsMapper = goodsMapper;
	}

	public void setClassifyMapper(ClassifyMapper classifyMapper) {
		this.classifyMapper = classifyMapper;
	}

	public void setPictureUtil(PictureUtil pictureUtil) {
		this.pictureUtil = pictureUtil;
	}

	@Override
	public boolean addGoods(String name, String describe, String price, String stock, String classify,
			String base64Img) {
		int gPrice = Integer.parseInt(price);
		int gStock = Integer.parseInt(stock);
		// 把图片存入，返回图片地址,将图片base64转格式存起来，获得文件地址
		String pictureUrl =  pictureUtil.savePictureByBase64(base64Img);
		Goods goods = new Goods();
		//生成goodsId
		goods.setId(setGoodsId(classify));
		goods.setName(name);
		goods.setDescribed(describe);
		goods.setPrice(gPrice);
		goods.setStock(gStock);
		goods.setPictureUrl(pictureUrl);
		// 存入商品
		goodsMapper.addGoods(goods);
		// 该分类下的商品总数+1
		classifyMapper.increaseSum(classify, 1);
		return true;
	}

	private int setGoodsId(String classify) {
		GoodsClassify gClassify = classifyMapper.getClassify(classify);
		if(gClassify == null)
			return (int) (Math.random()*(-10000));
		int head = gClassify.getId();
		int tail = gClassify.getSum() + 1;
		int length = Integer.toString(tail).length();
		//该分类商品数量少于100
		if(length <= 2 )	
			return head * 100 + tail;
		return (int) (head * Math.pow(10, length) + tail);
	}

	@Override
	public boolean addStockById(String goodsId, String count) {
		int id = Integer.parseInt(goodsId);
		int cou = Integer.parseInt(count);
		goodsMapper.addGoodsStockById(id, cou);
		return true;
	}

	@Override
	public boolean subStockById(String goodsId, String count) {
		int id = Integer.parseInt(goodsId);
		int cou = Integer.parseInt(count);
		goodsMapper.subGoodsStockById(id, cou);
		return true;
	}

	@Override
	public List<Goods> searchByClassify(String classify) {
		if(ClassifyConstant.CLASSIFYMAP.containsKey(classify)) {
			Integer classifyId = classifyMapper.getClassifyId(ClassifyConstant.CLASSIFYMAP.get(classify));
			return goodsMapper.getGoodsByClassifyId(classifyId);
		}else if(classify.equals("all")) {
			return goodsMapper.getAllGoods();
		}else
			return null;
	}

	@Override
	public List<Goods> searchByKeyword(String keyword) {
		if (keyword == null || keyword.trim().length() == 0)
			return null;
		return goodsMapper.getGoodsByKeyword(keyword);
	}

	@Override
	public List<Goods> getHotSale(int count) {
		return goodsMapper.getHotSale(count);
	}

	@Override
	public List<Goods> getAll() {
		return goodsMapper.getAllGoods();
	}

}
