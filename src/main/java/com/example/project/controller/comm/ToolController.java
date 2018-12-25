package com.example.project.controller.comm;

import com.example.project.comm.ResponseData;
import com.example.project.model.BehaviorDataRq;
import com.example.project.mq.rabbitmq.Sender;
import com.example.project.tool.GsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api("测试模块")
@RestController
@RequestMapping({"/api/comm/tool"})
public class ToolController {

    @Autowired
    private Sender senderMQ;

    //-------------------------RabbitMQ应用程序测试------------------------------
    @ApiOperation(value = "RabbitMQ应用程序测试-Sent操作", notes = "RabbitMQ操作：Sent操作")
    @RequestMapping(value = "/mq", produces = {"application/json;charset=UTF-8;"}, method = RequestMethod.POST)
    public ResponseData<Object> getmq(@RequestBody BehaviorDataRq request) {
        String json = GsonUtil.toJson(request.getData());
        senderMQ.sendString("DirectExchange", json);
//        for (int i = 1; i <= 50; i++) {
//            senderMQ.sendString("TopicExchange", i + ".DirectExchange已发送" + json);
//            senderMQ.sendString("DirectExchange", json);
//            senderMQ.sendString("FanoutExchange", i + ".FanoutExchange已发送" + json);
//            System.out.println(i + ".已发送");
//        }
        System.out.println("---");
        return new ResponseData<Object>("RabbitMQ应用程序测试");
    }
}
