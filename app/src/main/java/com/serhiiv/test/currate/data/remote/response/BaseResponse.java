package com.serhiiv.test.currate.data.remote.response;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private T data;

    public BaseResponse() {
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
