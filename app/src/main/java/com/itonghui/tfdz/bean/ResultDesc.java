package com.itonghui.tfdz.bean;

/**
 * @Description 登录返回
 * @Author yandaocheng
 */

public class ResultDesc {
    private String info;
    private int resultCode;

    private int statusCode;
    private String message;

    public ResultDesc(int resultCode, String info) {
        this.info = info;
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
