package pers.goodwin.shopSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.goodwin.shopSystem.pojo.GoodsDto;
import pers.goodwin.shopSystem.service.GoodsService;

import java.io.IOException;

@Controller
public class ManagerController {

    @Autowired
    @Qualifier("goodsService")
    private GoodsService goodsService;

    //上架商品
    @RequestMapping("/addGoods")
    @ResponseBody
    public String addGoods(@RequestBody String data) throws IOException {
        //System.out.println(data);
        String message;
        boolean isSuccess = false;
        ObjectMapper mapper = new ObjectMapper();
        GoodsDto goodsDto = mapper.readValue(data, GoodsDto.class);
        isSuccess = goodsService.addGoods(goodsDto);
        if(isSuccess)
            message = "<font color='green'>操作成功</font>";
        else
            message = "<font color='red'>操作失败</font>";
        return message;
    }

    //补充库存
    @RequestMapping("/replenish")
    @ResponseBody
    public String replenish(@RequestBody String data) {
        //System.out.println(data);
        String []msg = data.split(",");
        String goodsId = msg[0].substring( msg[0].indexOf(":")+1);
        String count = msg[1].substring(msg[1].indexOf(":")+1,msg[1].indexOf("}"));
        //System.out.println("goodsId = " + goodsId + " , count = " + count);
        if(goodsService.addStockById(goodsId,count))
            return "成功上架该商品数量：" + count;
        else
            return "上架失败";
    }

    //下架商品
    @RequestMapping("/unShelve")
    @ResponseBody
    public String unshelve(@RequestBody String data){
        String[] msg = data.split(",");
        String goodsId = msg[0].substring( msg[0].indexOf(":")+1);
        String stock = msg[1].substring(msg[1].indexOf(":")+1,msg[1].indexOf("}"));
        if(goodsService.subStockById(goodsId,stock))
            return "成功下架该商品数量：" + stock;
        else
            return "操作失败";
    }
}
