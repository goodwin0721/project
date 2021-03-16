package pers.goodwin.shopSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pers.goodwin.shopSystem.constant.ClassifyConstant;
import pers.goodwin.shopSystem.pojo.Goods;
import pers.goodwin.shopSystem.pojo.PageBean;
import pers.goodwin.shopSystem.service.GoodsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    @Qualifier("goodsService")
    private GoodsService goodsService;

    //按商品分类搜索
    @GetMapping("/t/{classify}")
    public String search(Model model, @PathVariable String classify){
        List<Goods> goodsList = new ArrayList<Goods>();
        String keyword = "";
        if(classify.equals("all")){
            goodsList = goodsService.getAll();
            keyword = "全部商品";
        } else
            goodsList = goodsService.searchByClassify(classify);
        model.addAttribute("goodslist", goodsList);
        if(!classify.equals("all"))
            keyword = ClassifyConstant.CLASSIFYMAP.containsKey(classify)?ClassifyConstant.CLASSIFYMAP.get(classify):classify;
        model.addAttribute("keyword", keyword);
        if(goodsList == null)
            model.addAttribute("isSuccess",false);
        return "search2";
    }

    //关键字搜索，time用来标记成功搜索的时间戳，避免刷新重复提交请求
    @GetMapping("/search/{keyword}/{time}")
    public String searchByKeyword(@PathVariable String keyword, @PathVariable String time, Model model, HttpServletRequest request){
        String t = (String)request.getSession().getAttribute("time");

        /*无效搜索：
         1.没有输入搜索内容
         2.重复刷新搜索页面
         */
        if (keyword == null || keyword.trim().equals("") || (t != null && time.equals(t))) {
            //System.out.println("无效操作==>重复刷新页面");
            //如果搜索框为无效信息或者重复提交、刷新时，啥也不干
        } else {
            //更新session中的time
            request.getSession().setAttribute("time", time);//记录请求的time属性到session里，免得重复提交
            List<Goods> goodslist = goodsService.searchByKeyword(keyword);
            model.addAttribute("keyword", keyword);//用来更新“你的位置”
            if(goodslist != null && goodslist.size() != 0 ) {
                //查到商品信息
                //System.out.println("按关键字搜索====》搜到了");
                model.addAttribute("goodslist", goodslist);
            }else {
                //查不到商品信息
                model.addAttribute("isSuccess", "false");
            }
        }
        return "search2";
    }

    @GetMapping("/type/{classify}/{pageNumb}")
    public String searchByClassify(@PathVariable("classify") String classify,@PathVariable("pageNumb") int pageNumb,Model model){
        PageBean<Goods> pageMsg = new PageBean<>();
        String keyword = "";
        if(classify.equals("all")){
            pageMsg = goodsService.pagedQuery(pageNumb);
            keyword = "全部商品";
        } else
            pageMsg = goodsService.pagedQueryByClassify(classify, pageNumb);
        if(!classify.equals("all"))
            keyword = ClassifyConstant.CLASSIFYMAP.containsKey(classify)?ClassifyConstant.CLASSIFYMAP.get(classify):classify;
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageMsg", pageMsg);
        if(pageMsg.getLists().size() == 0)
            model.addAttribute("isSuccess",false);
        model.addAttribute("type","type");
        model.addAttribute("classify",classify);
        return "search";
    }

    @GetMapping("/keyword/{keyword}/{pageNumb}")
    public String searchByKeyword(@PathVariable("keyword") String keyword,@PathVariable("pageNumb") int pageNumb,Model model){
        PageBean<Goods> pageMsg = new PageBean<>();
        pageMsg = goodsService.pagedQueryByKeyword(keyword,pageNumb);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageMsg", pageMsg);
        if(pageMsg.getLists().size() == 0)
            model.addAttribute("isSuccess",false);
        model.addAttribute("type","keyword");
        return "search";
    }
}
