package com.example.project.mq.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.*;

@Configuration
public class RabbitConfig {

//    //声明队列
//    @Bean
//    public Queue TopicQueueInfo() {
//        return new Queue("TopicQueue", false); // true表示持久化该队列
//    }
//
//    @Bean
//    public Queue DirectQueueInfo() {
//        return new Queue("DirectQueue", false);
//    }
//
//    @Bean
//    public Queue FanoutQueueInfo() {
//        return new Queue("FanoutQueue", false);
//    }
//
//    //声明交互器
//    @Bean
//    TopicExchange topicExchange() {
//        return new TopicExchange("TopicExchange");
//    }
//
//    @Bean
//    DirectExchange directExchange() {
//        return new DirectExchange ("DirectExchange");
//    }
//
//    @Bean
//    FanoutExchange fanoutExchange() {
//        return new FanoutExchange ("FanoutExchange");
//    }
//
//    //绑定
//    @Bean
//    public Binding bindingTopicQueueInfo() {
//        return BindingBuilder.bind(TopicQueueInfo()).to(topicExchange()).with("TopicExchange");
//    }
//
//    @Bean
//    public Binding bindingDirectQueueInfo() {
//        return BindingBuilder.bind(DirectQueueInfo()).to(directExchange()).with("DirectExchange");
//    }
//
//    @Bean
//    public Binding bindingFanoutExchange() {
//        return BindingBuilder.bind(FanoutQueueInfo()).to(fanoutExchange());
//    }
//
//

}
