package com.example.dell.lianxi1227.bean;

public class MessageBean {
    public String message;
     public String flag;

    public MessageBean(String message, String flag) {
        this.message = message;
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
