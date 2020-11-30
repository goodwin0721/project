package pers.goodwin.shopSystem.dao;

import pers.goodwin.shopSystem.model.GoodsClassify;

public interface ClassifyDao {
	public GoodsClassify getClassify(String classify);//
	public int getSum(String classify);//获得该分类下的商品总数
	public boolean increaseSum(String classify, int sum);//增加商品总数
	public int getClassifyId(String classify);//获得该分类的编号
}
