package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.AddCartBean;
import com.example.asus.jingdong.bean.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/13.
 */

public interface AddCartApiService {
    @FormUrlEncoded
    @POST("product/addCart")
    Observable<AddCartBean> getAddCart(@Field("uid") String uid,
                                     @Field("pid") String pid,
                                     @Field("token") String token);

}
