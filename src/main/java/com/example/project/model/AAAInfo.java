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
public class AAAInfo implements Serializable{
    private static final long serialVersionUID = 10L;
    //id属性是给mongodb用的，用@Id注解修饰
    @Id
    private String nameId;
    private String name;
    private String content;
    private String remark;
    private Date createtime;
}
