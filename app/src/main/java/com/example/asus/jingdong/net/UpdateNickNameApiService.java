package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/23.
 */

public interface UpdateNickNameApiService {
    @FormUrlEncoded
    @POST("user/updateNickName")
    Observable<BaseBean> UpdateNickName(@Field("uid") String uid,
                                     @Field("nickname") String nickname);
}
