package pers.goodwin.shopSystem.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.goodwin.shopSystem.dao.CartDao;
import pers.goodwin.shopSystem.dao.GoodsDao;
import pers.goodwin.shopSystem.dao.IndentDao;
import pers.goodwin.shopSystem.model.Cart;
import pers.goodwin.shopSystem.model.CartShow;
import pers.goodwin.shopSystem.model.Goods;
import pers.goodwin.shopSystem.model.Indent;
import pers.goodwin.shopSystem.model.IndentShow;
import pers.goodwin.shopSystem.service.ShoppingService;

/**
 * @author goodwin
 * 处理与购物相关的操作，包括添加商品到购物车、将商品从购物车移除、将商品加入订单
 */
//@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class ShoppingServiceImpl implements ShoppingService {

	@Override
	public boolean addGoodsToCart(int userId, int goodsId, int amount) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CartDao cartDao = (CartDao) context.getBean("cartDao");
		//添加到购物车不修改商品库存，结算时验证库存是否够
		//尝试修改数据，若修改成功则说明明购物车表有此条数据；否则，插入新数据
		boolean isUpdate = cartDao.updateGoodsAmount(userId, goodsId, amount);
		((ClassPathXmlApplicationContext)context).close();
		if(isUpdate)
			return true;
		return cartDao.addGoodsToCart(userId, goodsId, amount);
	}

	@Override
	public boolean removeGoodsFromCart(int userId,int goodsId) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CartDao cartDao = (CartDao) context.getBean("cartDao");
		((ClassPathXmlApplicationContext)context).close();
		return cartDao.removeGoodsFromCart(userId, goodsId);
	}
	
//	@Override
//	public List<Cart> removeAllGoodsFromCart(int userId) {
//		//TODO Auto-generated method stub
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		CartDao cartDao = (CartDao) context.getBean("cartDao");
//		cartDao.removeAllGoodsByUserId(userId);
//		return null;
//	}

//	@Override
//	public boolean addGoodsToIndent(int userId,int goodsId) {
//		//TODO Auto-generated method stub
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		IndentDao indentDao = (IndentDao) context.getBean("indentDao");
//		//indentDao.addGoodsToIndent(user, goods, amount);
//		return false;
//	}


	@Override
	public List<CartShow> showUserCart(int userId) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GoodsDao goodsDao = (GoodsDao) context.getBean("goodsDao");
		CartDao cartDao = (CartDao) context.getBean("cartDao");
		List<Cart> cartList = cartDao.searchByUserId(userId);
		((ClassPathXmlApplicationContext)context).close();
		if(cartList == null)
			return null;
		List<CartShow> cartShow = new ArrayList<CartShow>(cartList.size());
		//CartShow show = new CartShow();//放在这里的话，下面add(e)会导致e指向同一个地址，导致list里元素全都一样
		for(Cart cart:cartList) {
			CartShow show = new CartShow();
			Goods goods = goodsDao.getGoods(cart.getGoodsId());
			show.setGoodsId(cart.getGoodsId());
			show.setGoodsName(goods.getName());
			show.setPrice(goods.getPrice());
			show.setAmount(cart.getAmount());
			show.setPictureUrl(goods.getPictureUrl());
			show.setAubtotal(show.getAmount() * show.getPrice());
			cartShow.add(show);
		}
		return cartShow;
	}

	@Override
	public List<IndentShow> showUserIndent(int userId) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GoodsDao goodsDao = (GoodsDao) context.getBean("goodsDao");
		IndentDao indentDao = (IndentDao) context.getBean("indentDao");
		List<Indent> indentList = indentDao.searchByUserId(userId);
		((ClassPathXmlApplicationContext)context).close();
		if(indentList == null)
			return null;
		List<IndentShow> indentShow = new ArrayList<IndentShow>(indentList.size());
		for(Indent indent:indentList) {
			IndentShow show = new IndentShow();
			Goods goods = goodsDao.getGoods(indent.getGoodsId());
			show.setGoodsId(indent.getGoodsId());
			show.setGoodsName(goods.getName());
			show.setPrice(goods.getPrice());
			show.setAmount(indent.getAmount());
			show.setPictureUrl(goods.getPictureUrl());
			show.setAubtotal(show.getAmount() * show.getPrice());
			show.setTradeTime(indent.getTradeTime());
			indentShow.add(show);
		}
		return indentShow;
	}

	@Override
	public boolean updateGoodsAmountInCart(int userId, int goodsId, int amount) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CartDao cartDao = (CartDao) context.getBean("cartDao");
		((ClassPathXmlApplicationContext)context).close();
		return cartDao.updateGoodsAmount(userId, goodsId, amount);
	}

	@Override
	//或者 @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public boolean shopping(int userId) {
		boolean cleanCart = true;
		boolean addGoodsToIndent = true;
		boolean updateGoods = true;
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CartDao cartDao = (CartDao) context.getBean("cartDao");
		IndentDao indentDao = (IndentDao) context.getBean("indentDao");
		GoodsDao goodsDao = (GoodsDao) context.getBean("goodsDao");
		//获得购物车商品信息
		List<Cart> cartGoodsList = cartDao.searchByUserId(userId);
		if(cartGoodsList.size()!=0) {		
			for(Cart cartGoods:cartGoodsList) {
				//获得商品
				Goods goods = goodsDao.getGoods(cartGoods.getGoodsId());
				//添加到订单列表
				addGoodsToIndent = indentDao.addGoodsToIndent(userId, goods.getId(), goods.getPrice(), cartGoods.getAmount()) == false?false:addGoodsToIndent;
				//修改该商品库存数量
				updateGoods = goodsDao.updateGoodsStockById(goods.getId(), -cartGoods.getAmount()) == false?false:updateGoods;
				//修改该商品已售数量
				updateGoods = goodsDao.updateGoodsSoldById(goods.getId(), cartGoods.getAmount()) == false?false:updateGoods;
				//将该商品从购物车移除
				//cleanCart = cartDao.removeGoodsFromCart(userId, cartGoods.getGoodsId()) == false?false:cleanCart;
			}
			//将购物车清空
			cleanCart = cartDao.removeAllGoodsByUserId(userId);
			
		}
		((ClassPathXmlApplicationContext)context).close();
		return cleanCart && addGoodsToIndent && updateGoods;
	}

	
}
