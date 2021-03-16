package pers.goodwin.shopSystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.goodwin.shopSystem.pojo.CartDto;
import pers.goodwin.shopSystem.pojo.CartVo;
import pers.goodwin.shopSystem.pojo.User;
import pers.goodwin.shopSystem.service.ShoppingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ShoppingController {

    @Autowired
    @Qualifier("shoppingService")
    private ShoppingService shoppingService;

    @RequestMapping(value = "/addToCart",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addGoodsToCart(@RequestParam("goodsId") int goodsId, @RequestParam("amount") int amount, HttpServletRequest request) throws JsonProcessingException {
        //System.out.println(goodsId + "/" + amount);
        //创建一个jackson的对象映射器，用来解析数据
        ObjectMapper mapper = new ObjectMapper();
        boolean flag = false;
        User user = (User)request.getSession().getAttribute("user");
        String msg = "";
        if(user == null)
            msg = "请先登录";
        flag = shoppingService.addGoodsToCart(user.getId(),goodsId,amount);
        if(flag)
            msg = "操作成功";
        else
            msg = "操作失败";
        //System.out.println(mapper.writeValueAsString(msg));
        return mapper.writeValueAsString(msg);
    }

    @RequestMapping("/changeCartGoodsAmount")
    @ResponseBody
    public String changeCartGoodsAmount(@RequestBody String data,HttpServletRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        int aubtotal = 0;
        int total = 0;
        boolean isSuccess = false;
        String message;
        User user = (User)request.getSession().getAttribute("user");
        if(user == null)
            return "false";
        CartDto cartDto =  mapper.readValue(data, CartDto.class);
        cartDto.setUserId(user.getId());
        //System.out.println(data);{"goodsId":4002,"amount":"8"}
        if(cartDto.getAmount() == 0)
            isSuccess = shoppingService.removeGoodsFromCart(cartDto.getUserId(), cartDto.getGoodsId());
        else {
            isSuccess = shoppingService.updateGoodsAmountInCart(cartDto.getUserId(), cartDto.getGoodsId(), cartDto.getAmount());
        }
        List<CartVo> cartList = shoppingService.showUserCart(cartDto.getUserId());
        for(CartVo cart : cartList) {
            if(cart.getGoodsId() == cartDto.getGoodsId()) {
                aubtotal = cart.getAubtotal();
            }
            total += cart.getAubtotal();
        }
        if(isSuccess)
            message = "true," + aubtotal + "," + total;
        else
            message = "false";
        return message;
    }

    @RequestMapping("/pay")
    public String pay(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user != null &&  shoppingService.shopping(user.getId()))
            return "redirect:/p/indent";
        else
            return "redirect:/p/cart";
    }







}
