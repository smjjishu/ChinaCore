package com.example.project.comm;


import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接口信息返回数据封装
 */
@Data
public class ResponseData<T> implements Serializable {

    @ApiModelProperty(value = "返回结果代码")
    private int code;
    @ApiModelProperty(value = "返回结果信息")
    private String msg;
    @ApiModelProperty(value = "返回结果的对象内容")
    private T data = null;// 数据

    public ResponseData() {
    }

    public ResponseData(T data) {
        this.code = 1;
        this.msg = "SUCCESS";
        this.data = data;
    }

    public ResponseData(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public ResponseData(String msg, T data) {
        this.code = 1;
        this.msg = msg;
        this.data = data;
    }

    public ResponseData(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}

