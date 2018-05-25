package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/17.
 */

public interface CreateOrderApiService {
    @FormUrlEncoded
    @POST("product/createOrder")
        Observable<BaseBean> createOrder(@Field("Uid") String  uid,
                                     @Field("Price") String price);
}
