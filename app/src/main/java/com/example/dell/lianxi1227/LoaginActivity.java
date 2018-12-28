package com.example.dell.lianxi1227;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.dell.lianxi1227.fragment.PingLunFragment;
import com.example.dell.lianxi1227.fragment.ShopFragment;
import com.example.dell.lianxi1227.fragment.XiangQingFragment;

import java.util.ArrayList;
import java.util.List;

public class LoaginActivity extends AppCompatActivity {
     RadioGroup group;
     ViewPager pager;
     String s;
     private String pid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loagin);
        group = findViewById(R.id.group);
        pager = findViewById(R.id.pager);
        pager.setCurrentItem(3);
        Intent intent=getIntent();
        pid = intent.getStringExtra("pid");
        final List<Fragment> list = new ArrayList<>();
        list.add(new ShopFragment());
        list.add(new XiangQingFragment());
        list.add(new PingLunFragment());
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });



    }
    public String getData(){
        return pid;
    }
    public void getLayout(int posion){
         pager.setCurrentItem(posion);
    }
}
