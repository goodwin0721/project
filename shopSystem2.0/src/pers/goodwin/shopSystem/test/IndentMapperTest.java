package pers.goodwin.shopSystem.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pers.goodwin.shopSystem.mapper.IndentMapper;
import pers.goodwin.shopSystem.model.Indent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IndentMapperTest {
	@Resource(name = "indentMapper")
	IndentMapper indentMapper;
	@Test
	public void test_addGoodsToIndent() {
		Indent indent = new Indent();
		indent.setUserId(9);
		indent.setGoodsId(1001);
		indent.setPrice(10);
		indent.setAmount(10);
		indent.setTradeTime("2015-1-1");
		indentMapper.addGoodsToIndent(indent);
	}
	
	@Test
	public void test_searchByUserId() {
		List<Indent> list = indentMapper.searchByUserId(9);
		for (Indent indent : list) {
			System.out.println(indent);
		}
	}
}
