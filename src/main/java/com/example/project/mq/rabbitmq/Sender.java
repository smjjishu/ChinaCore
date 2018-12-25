package com.example.project.mq.rabbitmq;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public void sendString(String exchange, String msg) {
//        rabbitTemplate.convertAndSend(exchange, exchange, msg);
//    }
//
//    public void sendObject(String exchange, Object model) {
//        rabbitTemplate.convertAndSend(exchange, exchange, model);
//    }
//
//
//    public void sendString(String exchange, String routingKey, String msg, String expiration) {
//        MessagePostProcessor messagePostProcessor = message -> {
//            MessageProperties messageProperties = message.getMessageProperties();
////            设置编码
//            messageProperties.setContentEncoding("utf-8");
////            设置过期时间10*1000毫秒
//            messageProperties.setExpiration(expiration);
//            return message;
//        };
//
//        rabbitTemplate.convertAndSend(exchange, routingKey, msg, messagePostProcessor);
//    }

    public void sendString(String exchange, String msg) {

    }
}

