package com.example.project.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public class KeyExpirsMessageListener implements MessageListener {
    @Autowired
    List<KeyExpirsBaseHandler> handlerList;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String key = message.toString();
        for (KeyExpirsBaseHandler handler : handlerList){
            if(handler.matchKey(key)){
                handler.handle(key);
                break;
            }
        }
    }

}
