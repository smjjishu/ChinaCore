package com.example.project.async;

import com.example.project.model.LogInfo;
import com.example.project.mongo.MongoDBService;
import com.example.project.tool.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


@Slf4j
@Component
public class AsyncService {
    @Autowired
    private MongoDBService mongoService;
    /**
     *
     */
    @Async("asyncLogExecutor")
    public void writeLogInfo(String type, String jsonStr) {
        String dt = TimeUtil.getStringFromTime(new Date(), TimeUtil.TIME_FORMAT_SHOW_MILLISECOND_WITH_COLON_One);
        LogInfo log=LogInfo.builder().logId(UUID.randomUUID().toString()).content(jsonStr).type(type).createTime(new Date()).build();
        mongoService.saveLogInfo(log);
        System.out.println(type + ".[" + dt + "] -------- json:" + jsonStr);
    }
}
