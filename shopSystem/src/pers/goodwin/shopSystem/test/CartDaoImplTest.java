package pers.goodwin.shopSystem.test;

import java.util.List;

import org.junit.Test;

import pers.goodwin.shopSystem.dao.Impl.CartDaoImpl;
import pers.goodwin.shopSystem.dao.Impl.GoodsDaoImpl;
import pers.goodwin.shopSystem.dao.Impl.UserDaoImpl;
import pers.goodwin.shopSystem.model.Cart;
import pers.goodwin.shopSystem.model.Goods;
import pers.goodwin.shopSystem.model.User;

public class CartDaoImplTest {
	@Test
	public void addGoodsToCartTest() {
		User user = new UserDaoImpl().getUserByPassword("Donald Trump", "idiot");
		Goods goods = new GoodsDaoImpl().getGoods(7001);
		if(new CartDaoImpl().addGoodsToCart(user, goods, 10)) {
			System.out.println("success");
		}else 
			System.out.println("fail");
			
	}

	@Test
	public void addGoodsToCartTest2() {
		if(new CartDaoImpl().addGoodsToCart(4, 7002, 20)) {
			System.out.println("success");
		}else 
			System.out.println("fail");
			
	}
	
	@Test
	public void deleteGoodsFromCartTest() {
		User user = new UserDaoImpl().getUserByPassword("Donald Trump", "idiot");
		Goods goods = new GoodsDaoImpl().getGoods(7001);
		if(new CartDaoImpl().updateGoodsAmount(user, goods, 1)) {
			System.out.println("success");
		}else 
			System.out.println("fail");
	}

	@Test
	public void searchByUserTest() {
		User user = new UserDaoImpl().getUserByPassword("Donald Trump", "idiot");
		List<Cart> list = new CartDaoImpl().searchByUser(user);
		for(Cart c : list)
			System.out.println(c);
	}

	@Test
	public void searchByUserIdTest() {
		List<Cart> list = new CartDaoImpl().searchByUserId(4);
		for(Cart c : list)
			System.out.println(c);
	}

}
