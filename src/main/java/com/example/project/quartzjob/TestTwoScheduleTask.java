package com.example.project.quartzjob;


import com.example.project.tool.TimeUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@EnableScheduling
public class TestTwoScheduleTask {
    public void runJob() {
        System.out.println("任务：【A】------------【"+ TimeUtil.getCurrentTime("")+"】-------------");
    }
}
