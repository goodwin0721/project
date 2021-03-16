package pers.goodwin.shopSystem.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pers.goodwin.shopSystem.mapper.CartMapper;
import pers.goodwin.shopSystem.mapper.GoodsMapper;
import pers.goodwin.shopSystem.mapper.IndentMapper;

import pers.goodwin.shopSystem.pojo.*;
import pers.goodwin.shopSystem.service.ShoppingService;

/**
 * @author goodwin
 * 处理与购物相关的操作，包括添加商品到购物车、将商品从购物车移除、将商品加入订单
 */
//@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class ShoppingServiceImpl implements ShoppingService {
	
	CartMapper cartMapper;
	IndentMapper indentMapper;
	GoodsMapper goodsMapper;
	
	public void setCartMapper(CartMapper cartMapper) {
		this.cartMapper = cartMapper;
	}

	public void setIndentMapper(IndentMapper indentMapper) {
		this.indentMapper = indentMapper;
	}

	public void setGoodsMapper(GoodsMapper goodsMapper) {
		this.goodsMapper = goodsMapper;
	}

	@Override
	public boolean addGoodsToCart(int userId, int goodsId, int amount) {
		Cart cart = cartMapper.getCart(userId, goodsId);
		if(cart == null)
			cartMapper.addGoodsToCart(userId, goodsId, amount);
		else
			cartMapper.updateGoodsAmount(userId, goodsId, cart.getAmount() + amount);
		return true;
	}

	@Override
	public boolean removeGoodsFromCart(int userId,int goodsId) {
		cartMapper.removeGoodsFromCart(userId, goodsId);
		return true;
	}

	@Override
	public List<CartVo> showUserCart(int userId) {
		List<Cart> cartList = cartMapper.searchByUserId(userId);
		if(cartList == null)
			return null;
		List<CartVo> cartVo = new ArrayList<CartVo>(cartList.size());
		for(Cart cart:cartList) {
			CartVo vo = new CartVo();
			Goods goods = goodsMapper.getGoods(cart.getGoodsId());
			vo.setGoodsId(goods.getId());
			vo.setGoodsName(goods.getName());
			vo.setPrice(goods.getPrice());
			vo.setAmount(cart.getAmount());
			vo.setPictureUrl(goods.getPictureUrl());
			vo.setAubtotal(cart.getAmount() * goods.getPrice());
			cartVo.add(vo);
		}
		return cartVo;
	}

	@Override
	public List<IndentVo> showUserIndent(int userId) {
		List<Indent> indentList = indentMapper.searchByUserId(userId);
		if(indentList == null)
			return null;
		List<IndentVo> indentVo = new ArrayList<IndentVo>(indentList.size());
		for(Indent indent:indentList) {
			IndentVo show = new IndentVo();
			Goods goods = goodsMapper.getGoods(indent.getGoodsId());
			show.setGoodsId(goods.getId());
			show.setGoodsName(goods.getName());
			show.setPrice(indent.getPrice());
			show.setAmount(indent.getAmount());
			show.setPictureUrl(goods.getPictureUrl());
			show.setAubtotal(indent.getAmount() * indent.getPrice());
			show.setTradeTime(indent.getTradeTime());
			indentVo.add(show);
		}
		return indentVo;
	}

	@Override
	public boolean updateGoodsAmountInCart(int userId, int goodsId, int amount) {
		cartMapper.updateGoodsAmount(userId, goodsId, amount);
		return true;
	}

	@Override
	//或者 @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public boolean shopping(int userId) {
		//获得购物车商品信息
		List<Cart> cartGoodsList = cartMapper.searchByUserId(userId);
		if(cartGoodsList == null)
			return true;
		for(Cart cartGoods:cartGoodsList) {
			//获得商品
			Goods goods = goodsMapper.getGoods(cartGoods.getGoodsId());
			//添加到订单列表
			Indent indent = new Indent();
			indent.setUserId(userId);
			indent.setGoodsId(goods.getId());
			indent.setAmount(cartGoods.getAmount());
			indent.setPrice(goods.getPrice());
			//交易时间就是提交时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String tradeTime = dateFormat.format(new Date());
			indent.setTradeTime(tradeTime);
			indentMapper.addGoodsToIndent(indent);
			//修改该商品库存数量
			goodsMapper.subGoodsStockById(goods.getId(), cartGoods.getAmount());
			//修改该商品已售数量
			goodsMapper.addGoodsSoldById(goods.getId(), cartGoods.getAmount());
		}
		//测试spring事务
		//int i = 1/0;
		//将商品从购物车移除
		cartMapper.removeAllGoodsByUserId(userId);
		return true;
	}
}
