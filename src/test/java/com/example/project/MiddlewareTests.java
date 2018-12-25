package com.example.project;

import com.example.project.model.AAAInfo;
import com.example.project.mongo.MongoDBService;
import com.example.project.mq.rabbitmq.Sender;
import com.example.project.redis.RedisService;
import com.example.project.tool.CommTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 *中间件操作测试
 */

@RunWith(value=SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ChinaCoreApplication.class)
public class MiddlewareTests {

    @Autowired
    RedisService redisService;

    @Autowired
    private MongoDBService mongoDBService;

    @Autowired
    private Sender sender;

   // @Autowired
   // private KafkaService kafkaService;
    @Test
    public void middlewaretest()   {
        String FLAG="";
       // kafkaService.sendInfo();


       // redisTest();
       // mongoDBTest();

    }


    //todo:mongoDB相关功能
    private void mongoDBTest() {
        List<AAAInfo> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String guid = UUID.randomUUID().toString();
            String name = "longlong" + i;
            Calendar ca = Calendar.getInstance();
            ca.add(Calendar.HOUR, i);
            Date dt = ca.getTime();
            AAAInfo model = AAAInfo.builder().name(name).createtime(dt).content("com").remark("hello").nameId(guid).build();
            list.add(model);
        }
        mongoDBService.RemoveAll();
        mongoDBService.saveUserList(list);
        List<AAAInfo> lists = mongoDBService.findUsers();
    }

    //todo:Redis相关功能
    private void redisTest() {
        String timeStamp =CommTool.dateToString(new Date(),CommTool.YYYYMMDDHHMMSS);
        redisService.setValue("tempData:test",timeStamp);
    }

}
