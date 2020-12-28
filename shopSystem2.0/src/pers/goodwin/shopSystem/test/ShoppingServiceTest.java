package pers.goodwin.shopSystem.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.goodwin.shopSystem.model.CartShow;
import pers.goodwin.shopSystem.model.IndentShow;
import pers.goodwin.shopSystem.service.ShoppingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ShoppingServiceTest {
	@Resource(name = "shoppingService")
	private ShoppingService shoppingService;
	
	@Test
	public void test_addGoodsToCart() {
		shoppingService.addGoodsToCart(11, 4001, 1);
	}
	
	@Test
	public void test_showUserCart() {
		List<CartShow> showUserCart = shoppingService.showUserCart(9);
		for (CartShow cartShow : showUserCart) {
			System.out.println(cartShow);
		}
	}
	
	@Test
	public void test_showUserIndent() {
		List<IndentShow> indentShow = shoppingService.showUserIndent(9);
		for (IndentShow indent : indentShow) {
			System.out.println(indent);
		}
	}
	
	@Test
	public void test_updateGoodsAmountInCart() {
		shoppingService.updateGoodsAmountInCart(2, 4001, 5);
	}
	
	@Test
	public void test_removeGoodsFromCart() {
		shoppingService.removeGoodsFromCart(9, 4004);
	}
	
	@Test
	public void test_shopping() {
		shoppingService.shopping(11);
	}
}
