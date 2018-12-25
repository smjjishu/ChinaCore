package com.example.project.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LogInfo implements Serializable{
    private static final long serialVersionUID = 10L;
    //id属性是给mongodb用的，用@Id注解修饰
    @Id
    private String logId;
    private String type;
    private String content;
    private Date createTime;
}
