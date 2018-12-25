package com.example.project.model;


import com.example.project.comm.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.io.Serializable;

@Data
@ToString
public class UserInfo extends BaseParam implements Serializable {
    private static final long serialVersionUID = 1000L;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "用户名ID")
    private String id;
}