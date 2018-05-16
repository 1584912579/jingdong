package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.DatailsBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/12.
 */

public interface DatailsApiService {
    @FormUrlEncoded
    @POST("product/getProductDetail")
    Observable<DatailsBean> getDatails(@Field("pid") String pid);


}
