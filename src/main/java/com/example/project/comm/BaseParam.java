package com.example.project.comm;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.io.Serializable;

@Data
@ToString
public class BaseParam  implements Serializable {

    private static final long serialVersionUID = 1000L;

    @ApiModelProperty(value = "访问accessToken")
    private String accessToken;

    @ApiModelProperty(value = "请求时间戳")
    private String timeSpan;

}



