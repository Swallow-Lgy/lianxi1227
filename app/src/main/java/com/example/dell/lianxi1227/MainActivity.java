package com.example.dell.lianxi1227;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dell.lianxi1227.adpter.GoodsAdpter;
import com.example.dell.lianxi1227.bean.GoodsBean;
import com.example.dell.lianxi1227.presenter.IPresenterImpl;
import com.example.dell.lianxi1227.view.Iview;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Iview {
    private String url="searchProducts";
    private IPresenterImpl miPresenter;
    private boolean flag=true;
    private RecyclerView recyclerView;
    private GoodsAdpter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyle);
        findViewById(R.id.qh).setOnClickListener(this);
        //绑定
        miPresenter = new IPresenterImpl(this);
        //请求数据
        initData();
        //切换布局
        initLayout();

    }

    //请求数据
    public void initData(){
       HashMap<String,String> map = new HashMap<>();
        map.put("keywords","手机");
        map.put("page",1+"");
        miPresenter.requestData(url,map,GoodsBean.class);
    }
    //加载线性
    public void initLin(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    //加载网格
    public void initGrid(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

    }
    //布局切换
    public void initLayout(){
        if (flag){
           initLin();
        }
        else {
            initGrid();
        }
        adpter = new GoodsAdpter(this,flag);
        recyclerView.setAdapter(adpter);
        //点击跳转
        adpter.setOnClickLisen(new GoodsAdpter.onClick() {
            @Override
            public void OnClick(int pid) {
                Intent intent = new Intent(MainActivity.this,LoaginActivity.class);
                //intent.putExtra("pid",pid);
                intent.putExtra("pid",String.valueOf(pid));
                startActivity(intent);
            }
        });
        flag=!flag;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.qh:
                Toast.makeText(MainActivity.this,"1111",Toast.LENGTH_SHORT).show();
                List<GoodsBean.DataBean> dataBeans = adpter.getmList();
                initLayout();
                adpter.setmList(dataBeans);
                break;
                default:
                    break;
        }
    }

    @Override
    public void success(Object data) {
        if (data instanceof  GoodsBean){
            GoodsBean goodsBean = (GoodsBean) data;
            List<GoodsBean.DataBean> data1 = goodsBean.getData();
            adpter.setmList(data1);
        }
    }
}
