package com.example.project.quartzjob;


import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 定时任务配置
 */
//@Configuration
public class QuartzConfiguration {


    /**
     * 配置定时任务*******************
     */
    @Bean(name = "oneJobDetail")
    public MethodInvokingJobDetailFactoryBean oneJobDetail(TestOneScheduleTask testOneScheduleTask) {
        MethodInvokingJobDetailFactoryBean jobDetails = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetails.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetails.setTargetObject(testOneScheduleTask);
        // 需要执行的方法
        jobDetails.setTargetMethod("runJob");
        return jobDetails;
    }

    /**
     * 配置触发器*******************
     */
    @Bean(name = "oneTrigger")
    public CronTriggerFactoryBean oneTrigger(JobDetail oneJobDetail) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(oneJobDetail);
        // 配置时间
        tigger.setCronExpression("0 * * * * ? *");
        //trigger的name
        tigger.setName("Test_One_Job_Trigger");
        return tigger;
    }

    /**
     * 配置定时任务---------------------------
     */
    @Bean(name = "twoJobDetail")
    public MethodInvokingJobDetailFactoryBean twoJobDetail(TestTwoScheduleTask testTwoScheduleTask) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(testTwoScheduleTask);
        // 需要执行的方法
        jobDetail.setTargetMethod("runJob");
        return jobDetail;
    }

    /**
     * 配置触发器--------------------------------
     */
    @Bean(name = "twoTrigger")
    public CronTriggerFactoryBean twoTrigger(JobDetail twoJobDetail) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(twoJobDetail);
        // 配置时间
        tigger.setCronExpression("0 * * * * ? *");
        //trigger的name
        tigger.setName("Test_Two_Job_Trigger");
        return tigger;
    }





    /**
     * 配置Scheduler
     */
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger oneTrigger, Trigger twoTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 延时启动，应用启动多少秒后
        bean.setStartupDelay(10);
        // 注册触发器
        bean.setTriggers(
                oneTrigger,
                twoTrigger
        );

        return bean;
    }

}