package com.example.project.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@TableName("userinfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DTO_User   implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private Date dt;
}
