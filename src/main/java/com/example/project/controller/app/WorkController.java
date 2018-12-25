package com.example.project.controller.app;

import com.example.project.comm.BaseParam;
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

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Api("测试模块")
@RestController
@RequestMapping({"/api/app/work"})
public class WorkController {
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
    //--------------------------MongoDB应用程序测试------------------------------
    //---------------------------------------------------------------------------
    @ApiOperation(value="MongoDB应用程序测试-查询用户", notes="MongoDB操作：根据用户名获取用户详情")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Object> getdata(@RequestBody UserInfo userInfo) {
        log.info("请求信息：{url:'/api/detail',name:" + userInfo.getName() + "}");
        AAAInfo model = userService.findUserByAction(userInfo.getName());
        log.info("返回值为：" + model.toString());
        return new ResponseData<Object>(model);
    }

    @ApiOperation(value="MongoDB应用程序测试-添加用户", notes="MongoDB操作：添加用户")
    @RequestMapping(value = "/addinfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Object> getdatainfo(@RequestBody BaseParam baseParam) {
        for (int i = 1; i <= 100; i++) {
            String guid = UUID.randomUUID().toString();
            String name = "king" + i;

            Calendar ca = Calendar.getInstance();
            ca.add(Calendar.MINUTE, i);
            Date dt = ca.getTime();

            AAAInfo model = AAAInfo.builder().name(name).createtime(dt).content("com").remark("hello").nameId(guid).build();
            userService.saveUser(model);
        }
        return new ResponseData<Object>("");
    }
    @ApiOperation(value="MongoDB应用程序测试-删除用户", notes="MongoDB操作：删除用户")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Object> remove(@RequestBody UserInfo userInfo) {
        AAAInfo model = userService.findUserById(userInfo.getId());
        userService.deleteUserById(userInfo.getId());
        return new ResponseData<Object>(model);
    }

    @ApiOperation(value="MongoDB应用程序测试-修改用户", notes="MongoDB操作：修改用户")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Object> modify(@RequestBody UserInfo userInfo) {
        AAAInfo model = userService.findUserByAction(userInfo.getName());
        model.setContent("dog");
        model.setRemark("peek");
        userService.updateUser(model);
        return new ResponseData<Object>(model);
    }

    @ApiOperation(value="MongoDB应用程序测试-用户列表", notes="MongoDB操作：用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Object> list(@RequestBody UserInfo userInfo) {
        return new ResponseData<Object>(userService.findUserByRemark(userInfo.getName()));
    }


}
