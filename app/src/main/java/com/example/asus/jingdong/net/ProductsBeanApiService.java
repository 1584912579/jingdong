package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.ProductsBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/11.
 */

public interface ProductsBeanApiService {
    @FormUrlEncoded
    @POST("product/getProducts")
    Observable<ProductsBean> getProducts(@Field("pscid") String pscid);
}
