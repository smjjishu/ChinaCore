package com.example.project.comm;

import org.springframework.stereotype.Component;
import java.lang.annotation.*;
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Tiger {
    String desc() default "分布式锁";
    int expireSeconds() default 5;//redis-key过期时间
}
