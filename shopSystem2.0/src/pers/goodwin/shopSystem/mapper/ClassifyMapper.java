package pers.goodwin.shopSystem.mapper;

import org.apache.ibatis.annotations.Param;

import pers.goodwin.shopSystem.model.GoodsClassify;

public interface ClassifyMapper {
	public GoodsClassify getClassify(String classify);
	public Integer getClassifyId(String classify);
	public Integer getSumByClassify(String classify);//获得该分类下的商品总数
	public void increaseSumById(@Param("classifyId") Integer classifyId,@Param("sum") Integer sum);//增加商品总数
	public void increaseSum(@Param("classify") String classify,@Param("sum") Integer sum);
}
