package pers.goodwin.shopSystem.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pers.goodwin.shopSystem.pojo.Goods;
import pers.goodwin.shopSystem.pojo.PageBean;
import pers.goodwin.shopSystem.service.GoodsService;

import javax.annotation.Resource;
import java.util.List;


/**
 * GoodsServiceImpl 测试
 * @author goodwin
 * @Date 2021-3-15 22:17:27
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestGoodsServiceImpl {

    @Resource
    private GoodsService goodsService;

    @Test
    public void testPagedQuery(){
        PageBean<Goods> goodsPageBean = goodsService.pagedQuery(1);
        System.out.println(goodsPageBean.getCurrPage());
        System.out.println(goodsPageBean.getPageSize());
        System.out.println(goodsPageBean.getTotalPage());
        System.out.println(goodsPageBean.getTotalCount());
        List<Goods> list = goodsPageBean.getLists();
        for (Goods goods:list) {
            System.out.println(goods);
        }
    }

    @Test
    public void testPagedQueryByClassify(){
        PageBean<Goods> goodsPageBean = goodsService.pagedQueryByClassify("fruits",1);
        if(goodsPageBean == null)
            System.out.println("null");
        else{
            System.out.println(goodsPageBean.getCurrPage());
            System.out.println(goodsPageBean.getPageSize());
            System.out.println(goodsPageBean.getTotalPage());
            System.out.println(goodsPageBean.getTotalCount());
            List<Goods> list = goodsPageBean.getLists();
            for (Goods goods:list) {
                System.out.println(goods);
            }
        }
    }

    @Test
    public void testPagedQueryByKeyword(){
        PageBean<Goods> goodsPageBean = goodsService.pagedQueryByKeyword("拖鞋",1);
        if(goodsPageBean == null)
            System.out.println("null");
        else{
            System.out.println(goodsPageBean.getCurrPage());
            System.out.println(goodsPageBean.getPageSize());
            System.out.println(goodsPageBean.getTotalPage());
            System.out.println(goodsPageBean.getTotalCount());
            List<Goods> list = goodsPageBean.getLists();
            for (Goods goods:list) {
                System.out.println(goods);
            }
        }
    }
}
