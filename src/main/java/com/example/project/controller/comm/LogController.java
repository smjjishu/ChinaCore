package com.example.project.controller.comm;

import com.example.project.async.AsyncService;
import com.example.project.comm.ResponseData;
import com.example.project.model.BehaviorDataRq;
import com.example.project.model.LogInfo;
import com.example.project.mongo.MongoDBService;
import com.example.project.tool.GsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Api("测试日志模块")
@RestController
@RequestMapping({"/api/comm/log"})
public class LogController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private MongoDBService mongoService;


    @ApiOperation(value = "测试日志信息1", notes = "测试统计日志信息")
    @RequestMapping(value = "/logInfo", produces = {"application/json;charset=UTF-8;"}, method = RequestMethod.POST)
    public ResponseData<Object> logInfo(@RequestBody BehaviorDataRq request) {


        String json = GsonUtil.toJson(request.getData());
        asyncService.writeLogInfo("001", json);
        return new ResponseData<Object>("测试日志信息:logInfo");
    }

    @ApiOperation(value = "测试日志信息2", notes = "测试统计日志信息")
    @RequestMapping(value = "/logInfo2", produces = {"application/json;charset=UTF-8;"}, method = RequestMethod.POST)
    public ResponseData<Object> logInfo2(@RequestBody BehaviorDataRq request) {
        String json = GsonUtil.toJson(request.getData());
        LogInfo log = LogInfo.builder().logId(UUID.randomUUID().toString()).content(json).type("002").createTime(new Date()).build();
        mongoService.saveLogInfo(log);
        return new ResponseData<Object>("测试日志信息:logInfo2");
    }
}
