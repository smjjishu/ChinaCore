package com.example.project.controller.web;

import com.example.project.comm.BaseParam;
import com.example.project.comm.PageDataVO;
import com.example.project.comm.ResponseData;
import com.example.project.model.*;
import com.example.project.mongo.MongoDBService;
import com.example.project.mysql.DataDaoService;
import com.example.project.mq.rabbitmq.Sender;
import com.example.project.redis.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@Api("测试模块")
@RestController
@RequestMapping({"/api/web/user"})
public class UserController {
    @Autowired
    private MongoDBService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    private DataDaoService datadaoservice;

    @Autowired
    private Sender sender;

    @Value("${comm.one}")
    private String INFOMATION;

    //---------------------------------------------------------------------------
    //--------------------------Redis应用程序测试--------------------------------
    //---------------------------------------------------------------------------
    @ApiOperation(value = "Redis应用程序测试-设置Value", notes = "Redis操作：设置Value")
    @RequestMapping(value = "/rset", method = RequestMethod.POST)
    public ResponseData<Object> setredis(@RequestBody UserInfo userInfo) {
        String guid = UUID.randomUUID().toString();
        redisService.setValue(userInfo.getName(), guid);
        return new ResponseData<Object>(userInfo);
    }

    @ApiOperation(value = "Redis应用程序测试-获取Value", notes = "Redis操作：获取Value")
    @RequestMapping(value = "/rget", method = RequestMethod.POST)
    public ResponseData<Object> getredis(@RequestBody UserInfo userInfo) {
        return new ResponseData<Object>(redisService.getValue(userInfo.getName()));
    }


    //---------------------------------------------------------------------------
    //--------------------------MySQL应用程序测试--------------------------------
    //---------------------------------------------------------------------------
    @ApiOperation(value = "MySQL应用程序测试-CRUD操作", notes = "MySQL操作：CRUD操作")
    @RequestMapping(value = "/sql", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Object> getdatasql(@RequestBody BaseParam baseParam) {
        int age = (int) (1 + Math.random() * (10 - 1 + 1)) + 20;
        String name = "name:" + age;
        datadaoservice.ModifyUser(new DTO_User().builder().id(15).name("timer").age(36).email("smj@163.com").build());
        datadaoservice.AddUser(new DTO_User().builder().name("name").age(age).email("king@163.com").build());
        datadaoservice.DeleteUser(3);

        PageDataVO<List<DTO_User>> list = datadaoservice.getUserList("name", 2, 5);
        return new ResponseData<Object>(list);
    }
}
