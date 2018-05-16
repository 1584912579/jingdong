package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.AddCartBean;
import com.example.asus.jingdong.bean.ProductsBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/13.
 */

public class AddCartApi {
    private static AddCartApi addCartApi;
    private AddCartApiService addCartApiService;

    private AddCartApi(AddCartApiService addCartApiService) {
        this.addCartApiService = addCartApiService;
    }

    public static AddCartApi getAddCartApi(AddCartApiService addCartApiService) {
        if (addCartApi == null) {
            addCartApi = new AddCartApi(addCartApiService);
        }
        return addCartApi;
    }
    public Observable<AddCartBean> getAddCartApi(String uid,String pid,String token) {
        return addCartApiService.getAddCart(uid,pid,token);
    }
}
