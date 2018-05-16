package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.GetCartsBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/15.
 */

public interface GetCartsApiServive {
    @FormUrlEncoded
    @POST("product/getCarts")
    Observable<GetCartsBean> getGetCarts(@Field("uid") String uid,@Field("token") String token);
}
