package pers.goodwin.shopSystem.service.impl;

import java.util.HashMap;
import java.util.List;

import pers.goodwin.shopSystem.constant.ClassifyConstant;
import pers.goodwin.shopSystem.constant.PageConstant;
import pers.goodwin.shopSystem.mapper.ClassifyMapper;
import pers.goodwin.shopSystem.mapper.GoodsMapper;
import pers.goodwin.shopSystem.pojo.Goods;
import pers.goodwin.shopSystem.pojo.GoodsClassify;
import pers.goodwin.shopSystem.pojo.GoodsDto;
import pers.goodwin.shopSystem.pojo.PageBean;
import pers.goodwin.shopSystem.service.GoodsService;
import pers.goodwin.shopSystem.utils.PictureUtil;

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
	public boolean addGoods(GoodsDto goodsDto) {
		//System.out.println("存商品");
		return this.addGoods(goodsDto.getName(),goodsDto.getDescribe(),
				goodsDto.getPrice(),goodsDto.getSum(),goodsDto.getClassify(),goodsDto.getBase64Img());
	}

	@Override
	public boolean addGoods(String name, String describe, int price, int stock, String classify,
			String base64Img) {
		// 把图片存入，返回图片地址,将图片base64转格式存起来，获得文件地址
		//System.out.println("========开始存图片==============");
		String pictureUrl =  pictureUtil.savePictureByBase64(base64Img);
		// System.out.println("========图片存好了==============");
		Goods goods = new Goods();
		//生成goodsId
		goods.setId(setGoodsId(classify));
		goods.setName(name);
		goods.setDescribed(describe);
		goods.setPrice(price);
		goods.setStock(stock);
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

	//
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
	public List<Goods> getAll() {
		return goodsMapper.getAllGoods();
	}

	/*@Override
	public List<Goods> getAllByPage(int currentPage) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("start",(currentPage-1)*PageConstant.PAGE_SIZE);
		map.put("size", PageConstant.PAGE_SIZE);
		return goodsMapper.getAllGoodsByPage(map);
	}*/

	/*@Override
	public List<Goods> searchByClassifyAndPage(String classify, int currentPage) {
		if(ClassifyConstant.CLASSIFYMAP.containsKey(classify)) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			Integer classifyId = classifyMapper.getClassifyId(ClassifyConstant.CLASSIFYMAP.get(classify));
			map.put("classifyId",classifyId);
			map.put("start",(currentPage-1)*PageConstant.PAGE_SIZE);
			map.put("size", PageConstant.PAGE_SIZE);
			return goodsMapper.getGoodsByClassifyIdAndPage(map);
		}else
			return null;
	}*/

	/*@Override
	public List<Goods> searchByKeywordAndPage(String keyword, int currentPage) {
		if (keyword == null || keyword.trim().length() == 0)
			return null;
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("keyword",keyword);
		map.put("start",(currentPage-1)*PageConstant.PAGE_SIZE);
		map.put("size", PageConstant.PAGE_SIZE);
		return goodsMapper.getGoodsByKeywordAndPage(map);
	}*/

	/*@Override
	public Integer getCount() {
		return goodsMapper.getCount();
	}*/

	/*@Override
	public Integer getCountByClassify(String classify) {
		if(ClassifyConstant.CLASSIFYMAP.containsKey(classify)) {
			Integer classifyId = classifyMapper.getClassifyId(ClassifyConstant.CLASSIFYMAP.get(classify));
			return goodsMapper.getCountByClassifyId(classifyId);
		}else
			return 0;
	}*/

	/*@Override
	public Integer getCountByKeyword(String keyword) {
		return goodsMapper.getCountByKeyword(keyword);
	}*/

	@Override
	//分页查询-所有商品
	public PageBean<Goods> pagedQuery(int currentPage) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		PageBean<Goods> goodsPageBean = new PageBean<>();

		//当前页面
		goodsPageBean.setCurrPage(currentPage);
		//每页显示的数据数目
		goodsPageBean.setPageSize(PageConstant.PAGE_SIZE);
		//封装总商品数目
		int count = goodsMapper.getCount();
		goodsPageBean.setTotalCount(count);
		//总页数
		double dc = count;
		Double num =Math.ceil(dc/PageConstant.PAGE_SIZE);//向上取整
		goodsPageBean.setTotalPage(num.intValue());
		//每页商品数据
		map.put("start",(currentPage-1)*PageConstant.PAGE_SIZE);
		map.put("size", PageConstant.PAGE_SIZE);
		goodsPageBean.setLists(goodsMapper.getAllGoodsByPage(map));
		return goodsPageBean;
	}

	@Override
	public PageBean<Goods> pagedQueryByClassify(String classify, int currentPage) {
		if(!ClassifyConstant.CLASSIFYMAP.containsKey(classify))
			return null;
		HashMap<String,Object> map = new HashMap<String,Object>();
		PageBean<Goods> goodsPageBean = new PageBean<>();
		Integer classifyId = classifyMapper.getClassifyId(ClassifyConstant.CLASSIFYMAP.get(classify));
		int count = goodsMapper.getCountByClassifyId(classifyId);
		double dc = count;

		goodsPageBean.setCurrPage(currentPage);				//当前页
		goodsPageBean.setPageSize(PageConstant.PAGE_SIZE);	//页大小
		goodsPageBean.setTotalCount(count);					//商品总数
		Double num =Math.ceil(dc/PageConstant.PAGE_SIZE);	//向上取整
		goodsPageBean.setTotalPage(num.intValue());			//页总数

		//设置查询条件
		map.put("classifyId",classifyId);					//商品分类Id
		map.put("start",(currentPage-1)*PageConstant.PAGE_SIZE);//第几页
		map.put("size", PageConstant.PAGE_SIZE);			//每页多少项
		goodsPageBean.setLists(goodsMapper.getGoodsByClassifyIdAndPage(map));//查询所得商品列表
		return goodsPageBean;
	}

	@Override
	public PageBean<Goods> pagedQueryByKeyword(String keyword, int currentPage) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		PageBean<Goods> goodsPageBean = new PageBean<>();
		int count = goodsMapper.getCountByKeyword(keyword);
		double dc = count;

		goodsPageBean.setCurrPage(currentPage);				//当前页
		goodsPageBean.setPageSize(PageConstant.PAGE_SIZE);	//页大小
		goodsPageBean.setTotalCount(count);					//商品总数
		Double num =Math.ceil(dc/PageConstant.PAGE_SIZE);	//向上取整
		goodsPageBean.setTotalPage(num.intValue());			//页总数

		//设置查询条件
		map.put("keyword",keyword);					//商品分类Id
		map.put("start",(currentPage-1)*PageConstant.PAGE_SIZE);//第几页
		map.put("size", PageConstant.PAGE_SIZE);			//每页多少项
		goodsPageBean.setLists(goodsMapper.getGoodsByKeywordAndPage(map));//查询所得商品列表
		return goodsPageBean;
	}

	@Override
	public List<Goods> getHotSale() {
		return goodsMapper.getHotSale(PageConstant.HOT_SALE_COUNT);
	}

}
