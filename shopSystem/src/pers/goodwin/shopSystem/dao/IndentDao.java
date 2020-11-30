package pers.goodwin.shopSystem.dao;

import java.util.List;

import pers.goodwin.shopSystem.model.Goods;
import pers.goodwin.shopSystem.model.Indent;
import pers.goodwin.shopSystem.model.User;


public interface IndentDao {
	public boolean addGoodsToIndent(User user,Goods goods,int amount);//用户user完成amount件goods订单
	public boolean addGoodsToIndent(int userId,int goodsId,int price,int amount);//用户user完成amount件goods订单
	public List<Indent> searchByUser(User user);//通过user查询user的订单信息
	public List<Indent> searchByUserId(int userId);//通过userId查询user的订单信息
}
