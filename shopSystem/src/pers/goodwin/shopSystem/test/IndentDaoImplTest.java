package pers.goodwin.shopSystem.test;

import java.util.List;

import org.junit.Test;

import pers.goodwin.shopSystem.dao.Impl.CartDaoImpl;
import pers.goodwin.shopSystem.dao.Impl.GoodsDaoImpl;
import pers.goodwin.shopSystem.dao.Impl.IndentDaoImpl;
import pers.goodwin.shopSystem.dao.Impl.UserDaoImpl;
import pers.goodwin.shopSystem.model.Cart;
import pers.goodwin.shopSystem.model.Goods;
import pers.goodwin.shopSystem.model.Indent;
import pers.goodwin.shopSystem.model.User;

public class IndentDaoImplTest {

	@Test
	public void addGoodsToIndentTest() {
		User user = new UserDaoImpl().getUserByPassword("Donald Trump", "idiot");
		Goods goods = new GoodsDaoImpl().getGoods(7001);
		if(new IndentDaoImpl().addGoodsToIndent(user, goods, 10)) {
			System.out.println("success");
		}else 
			System.out.println("fail");

	}

	@Test
	public void searchByUserTest() {
		User user = new UserDaoImpl().getUserByPassword("Donald Trump", "idiot");
		List<Indent> list = new IndentDaoImpl().searchByUser(user);
		for (Indent i : list)
			System.out.println(i);
	}

	@Test
	public void searchByUserIdTest() {
		List<Indent> list = new IndentDaoImpl().searchByUserId(4);
		for (Indent i : list)
			System.out.println(i);
	}
}
