package com.example.dell.lianxi1227.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.lianxi1227.R;
import com.example.dell.lianxi1227.bean.GoodsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class GoodsAdpter extends RecyclerView.Adapter<GoodsAdpter.ViewHolder> {
    private List<GoodsBean.DataBean> mList;
    private Context mContext;
    private boolean flag;
    private int ITEM_LINEA = 0;
    private int ITEM_GRIAD = 1;
    public GoodsAdpter(Context mContext,boolean flag) {
        this.mContext = mContext;
        this.flag = flag;
        mList = new ArrayList<>();
    }

    public List<GoodsBean.DataBean> getmList() {
        return mList;
    }

    public void setmList(List<GoodsBean.DataBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }
   //判断布局

    @Override
    public int getItemViewType(int position) {
        if (flag){
            return ITEM_LINEA;
        }
        else {
            return ITEM_GRIAD;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View view = View.inflate(mContext, i == ITEM_LINEA ? R.layout.itemliean:R.layout.itemgriad,
         null
         );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String images = mList.get(i).getImages();
        String[] split = images.split("\\|");
        viewHolder.simpleDraweeView.setImageURI(split[0]);
        viewHolder.title.setText(mList.get(i).getTitle());
        viewHolder.price.setText(mList.get(i).getPrice()+"");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (monClick!=null){
                    monClick.OnClick(mList.get(i).getPid());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView title,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
        }
    }
    onClick monClick;
    public void setOnClickLisen(onClick onClick){
        monClick = onClick;
    }
    public interface  onClick{
        void OnClick(int pid);
    }
}
