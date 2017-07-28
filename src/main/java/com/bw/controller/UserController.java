package com.bw.controller;

import com.bw.bean.User;
import com.bw.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

/**
 * Created by a on 2017/7/28.
 */
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @RequestMapping("add.action")
    public String add(User user){
        userRepository.save(user);
        return "login";

    }
    @RequestMapping("tologin.action")
    public String tologin(){
        return "login";
    }
    @RequestMapping("login.action")
    public String login(String name, String pwd, HttpSession session){
      User user=userRepository.login(name,pwd);
    /* if(user!=null){
         session.setAttribute("name",user.getName());
         return "list.action";
     }*/
    return "forward:list.action";
    }
    @RequestMapping("list.action")
    public  String list(Model model){
       List<User> list= userRepository.findAll();
       model.addAttribute("list",list);
       System.out.print(list.size());
       return "listuser";

    }
   @RequestMapping("update.action")
     public String update(User user){
        userRepository.saveAndFlush(user);
        return "forward:list.action";

    }
    @RequestMapping("byid.action")
    public  String byid(Integer id, Model model){
        User user= userRepository.findOne(id);
        model.addAttribute("user",user);
        return "update";
    }
    @RequestMapping("delete.action")
    public String delete(Integer id){
        userRepository.delete(id);
        return "forward:list.action";
    }
    public String byname(String name){
        User user =userRepository.byname(name);
       return "success";
    }

  /*  @RequestMapping("/uid")
 String uid(HttpSession session) {
 UUID uid = (UUID) session.getAttribute("uid");
 if (uid == null) {
 uid = UUID.randomUUID();
 }
 session.setAttribute("uid", uid);
 return session.getId();
    }*/

}
