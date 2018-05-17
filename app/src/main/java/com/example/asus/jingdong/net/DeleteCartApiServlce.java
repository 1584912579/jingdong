package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/17.
 */

public interface DeleteCartApiServlce {
    @FormUrlEncoded
    @POST("product/deleteCart")
    Observable<BaseBean> getDeleteCart(@Field("uid") String uid,
                                       @Field("pid") String pid,
                                       @Field("token") String token);
}
