package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.BaseBean;
import com.example.asus.jingdong.bean.GetOrdersBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/17.
 */

public interface GetOrdersApiService {
    @FormUrlEncoded
    @POST("product/getOrders")
    Observable<GetOrdersBean> getGetOrders(@Field("Uid") String uid,
                                           @Field("Page") String page,
                                           @Field("Token") String token);
    @FormUrlEncoded
    @POST("product/updateOrder")
    Observable<BaseBean> updateOrder(@Field("uid") String uid,
                                     @Field("status") String status,
                                     @Field("orderId") String orderId,
                                     @Field("token") String token);
}
