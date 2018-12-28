package com.example.dell.lianxi1227.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.lianxi1227.LoaginActivity;
import com.example.dell.lianxi1227.R;
import com.example.dell.lianxi1227.bean.DaliBean;
import com.example.dell.lianxi1227.bean.GoodsBean;
import com.example.dell.lianxi1227.bean.MessageBean;
import com.example.dell.lianxi1227.presenter.IPresenterImpl;
import com.example.dell.lianxi1227.view.Iview;
import com.recker.flybanner.FlyBanner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopFragment extends Fragment implements Iview {
    private IPresenterImpl miPresenter;
    private String url="getProductDetail";
    private FlyBanner flyBanner;
    String data2;
    private TextView title,price;
    private Button xq,pl;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopfragment, container,false);
        flyBanner = view.findViewById(R.id.fly);
        title = view.findViewById(R.id.title);
        price = view.findViewById(R.id.price);
        xq= view.findViewById(R.id.xq) ;
        pl= view.findViewById(R.id.pl) ;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        data2 = ((LoaginActivity) getActivity()).getData();
        init();
        initData();

        xq.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EventBus.getDefault().post(new MessageBean(title.getText()+"","xq"));
                 ((LoaginActivity) getActivity()).getLayout(1);
            }
        });
        pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageBean(price.getText()+"","pl"));
                ((LoaginActivity) getActivity()).getLayout(2);
            }
        });
    }

    //发送消息

    //绑定
    public void init(){
        miPresenter = new IPresenterImpl(this);
    }
     public void initData(){
         HashMap<String,String>map = new HashMap<>();
         map.put("pid",data2);
        miPresenter.requestData(url,map,DaliBean.class);
    }
    @Override
    public void success(Object data) {
        if (data instanceof DaliBean){
            DaliBean bean = (DaliBean) data;
            DaliBean.DataBean data1 = bean.getData();
            String images = data1.getImages();
            String[] split = images.split("\\|");
            List<String> list = new ArrayList<>();
            for (int i=0;i<split.length;i++){
                list.add(split[i]);
            }
             flyBanner.setImagesUrl(list);
             title.setText(data1.title);
             price.setText(data1.price+"");
        }
    }
}
