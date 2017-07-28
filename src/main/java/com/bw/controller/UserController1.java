package com.bw.controller;


import com.bw.bean.User;

import com.bw.dao.UserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by a on 2017/7/19.
 */
@RestController
@RequestMapping(value="/users")
public class UserController1 {
    /*static Map<Long, Good> goods = Collections.synchronizedMap(new HashMap<Long, Good>());*/
    @Autowired
    private UserRepository userRepository;
    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value={""}, method= RequestMethod.GET)

    public List<User> getGoodList() {
     List<User> r=userRepository.findAll();
        return r;
    }
    @ApiOperation(value="创建用户", notes="根据user对象创建用户")
    @ApiImplicitParam(name = "good", value = "用户详细实体good", required = true, dataType = "User")
    @RequestMapping(value="", method=RequestMethod.POST)
    public String postGood(@RequestBody User user) {
      userRepository.save(user);
        return "success";
    }
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Integer id) {
        return userRepository.findOne(id);
    }
    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的User信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "good", value = "商品详细实体good", required = true, dataType = "Good")
    })
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Integer id, @RequestBody User user) {
        User u=userRepository.findOne(id);
      userRepository.saveAndFlush(u);

        return "success";
    }
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Integer id) {
     userRepository.delete(id);
        return "success";
    }


}
