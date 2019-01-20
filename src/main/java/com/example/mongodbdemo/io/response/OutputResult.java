package com.example.mongodbdemo.io.response;

import lombok.Data;

@Data
public class OutputResult<T> {

    private Integer code = 200;
    private String message = "SUCCESS";
    private T data;

    public OutputResult() {}
    public OutputResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public OutputResult(T data) {
        this.data = data;
    }
}
