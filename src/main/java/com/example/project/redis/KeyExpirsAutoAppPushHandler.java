package com.example.project.redis;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.util.Date;


@Component
@Slf4j
public class KeyExpirsAutoAppPushHandler implements KeyExpirsBaseHandler {


    @Override
    public boolean matchKey(String key) {
        return StringUtils.isNotBlank(key) && key.startsWith("AAAA");
    }

    @Override
    public void handle(String key) {
        System.out.println("RedisSubListener:" + new Date());

    }
}
