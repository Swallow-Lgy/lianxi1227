package com.example.dell.lianxi1227.util;

import android.widget.RelativeLayout;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitManager {
    private final String BASE_URL="http://www.zhaoapi.cn/product/";
      private static RetrofitManager mretrofitManager;
      public static synchronized  RetrofitManager getInstance(){
          if (mretrofitManager==null){
              mretrofitManager = new RetrofitManager();
          }
          return mretrofitManager;
      }
      private BaseApis mbaseApis;
      public RetrofitManager(){
          HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
          interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
          OkHttpClient.Builder builder = new OkHttpClient.Builder();
          builder.connectTimeout(15,TimeUnit.SECONDS);
          builder.readTimeout(15,TimeUnit.SECONDS);
          builder.writeTimeout(15,TimeUnit.SECONDS);
           builder.addInterceptor(interceptor);
           builder.retryOnConnectionFailure(true);
           OkHttpClient client = builder.build();
          Retrofit retrofit = new Retrofit.Builder()
                  .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                  .baseUrl(BASE_URL)
                  .client(client)
                  .build();
          mbaseApis  = retrofit.create(BaseApis.class);
      }
      //get请求
    public RetrofitManager get(String url){
          mbaseApis.get(url)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(observer);
          return mretrofitManager;
    }
    //表单post请求
    public RetrofitManager postFormBody(String url,Map<String, RequestBody> map){
          if (map==null){
              map = new HashMap<>();
          }
          mbaseApis.postFormBody(url,map)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(observer);
          return mretrofitManager;

      }
    //普通post请求
    public RetrofitManager  post(String url, Map<String,String>map){
         if (map==null){
             map = new HashMap<>();
         }
         mbaseApis.post(url,map)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(observer);
         return mretrofitManager;
      }
   //返回数据
   private  Observer observer = new Observer<ResponseBody>() {
       @Override
       public void onCompleted() {

       }

       @Override
       public void onError(Throwable e) {
             if (listener!=null)  {
                 listener.onFail(e.getMessage());
             }
       }

       @Override
       public void onNext(ResponseBody responseBody) {
           try {
               String data = responseBody.string();
               if (listener!=null){
                   listener.onSuccess(data);
               }

           } catch (IOException e) {
               e.printStackTrace();
               if (listener!=null){
                   listener.onFail(e.getMessage());
               }
           }
       }
   };
      private HttListener listener;
      public void result(HttListener listener){
          this.listener = listener;
      }
      public interface HttListener{
          void onSuccess(String data);
          void onFail(String error);
      }
}
