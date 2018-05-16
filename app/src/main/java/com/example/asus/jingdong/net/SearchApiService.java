package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.ProductsBean;
import com.example.asus.jingdong.bean.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/14.
 */

public interface SearchApiService {
    @FormUrlEncoded
    @POST("product/searchProducts")
    Observable<ProductsBean> getSearchApi(@Field("keywords") String keywords,@Field("pscid") String pscid);

}
