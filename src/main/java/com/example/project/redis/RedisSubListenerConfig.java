package com.example.project.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;


@Configuration
public class RedisSubListenerConfig {


    /**
     * redis库 监听key过期操作
     *  1.需要redis里面配置 Event notification项里面设置notify-keyspace-events "Ex"
     *       database: 10
     *       host: 127.0.0.1
     *       port: 6379
     *       password:
     */
    private String databaseOrder="10";


    @Autowired
    KeyExpirsMessageListener keyExpirsMessageListener;

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter( keyExpirsMessageListener);
    }


    @Autowired
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory ) {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListener(), new ChannelTopic("__keyevent@" + databaseOrder + "__:expired"));
        return container;
    }

}
