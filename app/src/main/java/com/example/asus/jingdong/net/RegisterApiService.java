package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/13.
 */

public interface RegisterApiService {
    @FormUrlEncoded
    @POST("user/reg")
    Observable<UserBean> Register(@Field("mobile") String mobile,
                               @Field("password") String password);

}
