package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/17.
 */

public interface updateCartsApiService {
    @FormUrlEncoded
    @POST("product/updateCarts")
    Observable<BaseBean> updateCarts(@Field("uid") String uid,
                                     @Field("sellerid") String sellerid,
                                     @Field("pid") String pid,
                                     @Field("num") String num,
                                     @Field("selected") String selected,
                                     @Field("token") String token);

}
