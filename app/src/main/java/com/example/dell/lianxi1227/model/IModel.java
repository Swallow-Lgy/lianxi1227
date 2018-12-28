package com.example.dell.lianxi1227.model;

import com.example.dell.lianxi1227.callback.MyCallBack;

import java.util.Map;

public interface IModel {
    void requestData(String url, Map<String,String>prams,MyCallBack callBack,Class clazz);
}
