package pers.goodwin.shopSystem.service;

import java.util.List;

import pers.goodwin.shopSystem.pojo.CartVo;
import pers.goodwin.shopSystem.pojo.IndentVo;

public interface ShoppingService {
	public boolean addGoodsToCart(int userId, int goodsId, int amount);
	public boolean removeGoodsFromCart(int userId,int goodsId);//当用户下单时，将结算的商品从用户购物车移除
//	public List<Cart> removeAllGoodsFromCart(int userId);
	public boolean updateGoodsAmountInCart(int userId,int goodsId,int amount);
//	public boolean addGoodsToIndent(int userId,int goodsId);	//当用户下单时，将商品信息添加到订单表
	public boolean shopping(int userId);//用户在购物车结算所做的动作
	public List<CartVo> showUserCart(int userId);//通过用户id查看用户的购物车商品信息
	public List<IndentVo> showUserIndent(int userId);//通过用户id查看用户的购物车商品信息
}
