package com.example.dell.lianxi1227.model;

import android.util.Log;

import com.example.dell.lianxi1227.callback.MyCallBack;
import com.example.dell.lianxi1227.util.ICall;
import com.example.dell.lianxi1227.util.OkHttpUtil;
import com.example.dell.lianxi1227.util.RetrofitManager;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public class IModelImpl implements IModel {
    @Override
    public void requestData(String url, Map<String, String> prams, final MyCallBack callBack, final Class clazz) {

        RetrofitManager.getInstance().post(url,prams).result(new RetrofitManager.HttListener() {
            @Override
            public void onSuccess(String data) {

                    Gson gson = new Gson();
                    Object o = gson.fromJson(data, clazz);
                    callBack.setData(o);



            }

            @Override
            public void onFail(String error) {
              callBack.setData(error);
            }
        });
       /* OkHttpUtil.getmIstance().postEqueue(url, new HashMap<String, String>(), new ICall() {
            @Override
            public void faile(Exception e) {
                callBack.setData(e);
            }

            @Override
            public void success(Object data) {
                 callBack.setData(data);
            }
        },clazz);*/
    }
}
