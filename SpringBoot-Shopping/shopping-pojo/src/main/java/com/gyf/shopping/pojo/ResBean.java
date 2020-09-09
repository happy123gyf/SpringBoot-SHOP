package com.gyf.shopping.pojo;

import java.io.Serializable;

public class ResBean implements Serializable {

    private Integer code;
    private String message;
    private Object data;

    public static ResBean ok(String message){

        return new ResBean(200, message);
    }

    public static ResBean ok(String message, Object data){
        return new ResBean(200, message,data);
    }

    public static ResBean error(String message){

        return new ResBean(500, message);
    }

    public static ResBean erroe(String message, Object data){
        return new ResBean(500, message,data);
    }



    private ResBean() {
    }

    private ResBean(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResBean(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
