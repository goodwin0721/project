package pers.goodwin.shopSystem.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.goodwin.shopSystem.mapper.CartMapper;
import pers.goodwin.shopSystem.model.Cart;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CartMapperTest {
	@Resource(name = "cartMapper")
	CartMapper cartMapper;
	@Test
	public void test_addGoodsToCart() {
		cartMapper.addGoodsToCart(10, 4001, 2);
	}
	
	@Test
	public void test_updateGoodsAmount() {
		cartMapper.updateGoodsAmount(9, 4001, 10);
	}
	
	@Test
	public void test_removeGoodsFromCart() {
		cartMapper.removeGoodsFromCart(9, 4001);
	}
	
	@Test
	public void test_removeAllGoodsByUserId() {
		cartMapper.removeAllGoodsByUserId(10);
	}
	
	@Test
	public void test_searchByUserId() {
		List<Cart> cartList = cartMapper.searchByUserId(9);
		for (Cart cart : cartList) {
			System.out.println(cart);
		}
	}
	
	@Test
	public void test_getCart() {
		Cart cart = cartMapper.getCart(91, 4004);
		System.out.println(cart);
	}
}
