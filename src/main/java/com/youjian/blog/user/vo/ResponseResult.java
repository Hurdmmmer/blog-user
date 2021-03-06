package com.youjian.blog.user.vo;

import lombok.Data;

@Data
public class ResponseResult<T> {
    private boolean success;
    private String message;
    private T body;
    private String url;

    private ResponseResult() {}

    public static<S> ResponseResult<S> success(S data) {
        ResponseResult<S> result = new ResponseResult<>();
        result.setBody(data);
        result.setSuccess(true);
        return result;
    }

    public static ResponseResult fail(String msg) {
        ResponseResult<Object> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage(msg);
        return result;
    }

    public static ResponseResult success(String msg, String avatarUrl) {
        ResponseResult<Object> result = new ResponseResult<>();
        result.setSuccess(true);
        result.setMessage(msg);
        result.setUrl(avatarUrl);
        return result;
    }
}
