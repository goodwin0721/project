package pers.goodwin.shopSystem.mapper;

import java.util.List;

import pers.goodwin.shopSystem.model.Indent;

public interface IndentMapper {
	// 用户user完成amount件goods订单
	public void addGoodsToIndent(Indent indent);

	// 用户user完成amount件goods订单
	//public void addGoodsToIndent(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId,
	//		@Param("price") Integer price, @Param("amount") Integer amount, @Param("tradeTime") String tradeTime);

	// 通过userId查询user的订单信息
	public List<Indent> searchByUserId(Integer userId);
}
