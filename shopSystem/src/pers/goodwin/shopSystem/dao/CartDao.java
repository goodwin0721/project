package pers.goodwin.shopSystem.dao;

import java.util.List;

import pers.goodwin.shopSystem.model.Cart;
import pers.goodwin.shopSystem.model.Goods;
import pers.goodwin.shopSystem.model.User;

public interface CartDao {
	public boolean addGoodsToCart(User user,Goods goods,int amount);//用户user添加amount件goods到购物车
	public boolean addGoodsToCart(int userId, int goodsId, int amount);
	public boolean updateGoodsAmount(User user,Goods goods,int amount);//修改user购物车中goods的数量
	public boolean updateGoodsAmount(int userId,int goodsId,int amount);
	public boolean removeGoodsFromCart(int userId,int goodsId);//将goods从user的购物车中移除
	public boolean removeAllGoodsByUserId(int userId);
	public List<Cart> searchByUser(User user);//通过user查询user的购物车信息
	public List<Cart> searchByUserId(int userId);//通过userId查询user的购物车信息
}
