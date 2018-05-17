package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.AddrsBean;
import com.example.asus.jingdong.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/17.
 */

public interface AddAddrApiService {
    @FormUrlEncoded
    @POST("user/addAddr")
    Observable<BaseBean> getAddAddr(@Field("uid") String uid,
                                    @Field("addr") String addr,
                                    @Field("mobile") String mobile,
                                    @Field("name") String name,
                                    @Field("token") String token);
}
