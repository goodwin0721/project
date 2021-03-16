package pers.goodwin.shopSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import pers.goodwin.shopSystem.pojo.Cart;


public interface CartMapper {
	// 用户user添加amount件goods到购物车
	public void addGoodsToCart(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId,
			@Param("amount") Integer amount);
	
	//通过用户id和商品id获得一条购物车记录
	public Cart getCart(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId);
	
	// 修改user购物车中goods的数量
	public void updateGoodsAmount(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId,
			@Param("amount") Integer amount);

	// 将goods从user的购物车中移除
	public void removeGoodsFromCart(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId);

	public void removeAllGoodsByUserId(Integer userId);

	// 通过userId查询user的购物车信息
	public List<Cart> searchByUserId(Integer userId);
}
