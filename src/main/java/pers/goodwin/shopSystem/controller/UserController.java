package pers.goodwin.shopSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.goodwin.shopSystem.pojo.User;
import pers.goodwin.shopSystem.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @RequestMapping("/m/login")
    public String login(String username, String password, HttpServletRequest request){
        User user = userService.login(username, password);
        if(user != null){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if(userService.isManager(user))
                session.setAttribute("isManager", true);
            return "redirect:/p/index";
        }
        return "redirect:/p/login";
    }

    @RequestMapping("/m/register")
    public String register(String username,String password,String birthdate,String gender){
        boolean isSuccess = userService.register(username, password, birthdate, gender);
        if(isSuccess) {
            return "redirect:/p/index";
        }else
            return "redirect:/p/register";
    }

    @RequestMapping("/m/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("isManager");
        return "redirect:/p/index";
    }

    @RequestMapping(value = "/m/isExist",produces = "application/json")
    @ResponseBody
    public String isExist(@RequestParam("username") String username){
        if(userService.isExist(username))
            return "true";
        else return "false";
    }
}
