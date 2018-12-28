package com.example.dell.lianxi1227.presenter;

import com.example.dell.lianxi1227.callback.MyCallBack;
import com.example.dell.lianxi1227.model.IModelImpl;
import com.example.dell.lianxi1227.view.Iview;

import java.util.HashMap;
import java.util.Map;

public class IPresenterImpl implements IPresenter{
    private Iview miview;
    private IModelImpl miModel;
    public IPresenterImpl(Iview iview){
        miview = iview;
        miModel = new IModelImpl();
    }

    @Override
    public void requestData(String url, Map<String, String> prams, Class clazz) {
        miModel.requestData(url, prams, new MyCallBack() {
            @Override
            public void setData(Object data) {
                miview.success(data);
            }
        },clazz);
    }
}
