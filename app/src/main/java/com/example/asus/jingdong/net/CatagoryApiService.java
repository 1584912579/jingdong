package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.CatagoryBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by asus on 2018/5/10.
 */

public interface CatagoryApiService {
    @GET("product/getCatagory")
    Observable<CatagoryBean> getCatagory();
}
