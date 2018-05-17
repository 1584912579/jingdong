package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.BaseBean;
import com.example.asus.jingdong.bean.ProductsBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/17.
 */

public class updateCartsApi {
    private static updateCartsApi updateCartsApi;
    private updateCartsApiService updateCartsApiService;

    private updateCartsApi(updateCartsApiService updateCartsApiService) {
        this.updateCartsApiService = updateCartsApiService;
    }

    public static updateCartsApi getupdateCartsApi(updateCartsApiService updateCartsApiService) {
        if (updateCartsApi == null) {
            updateCartsApi = new updateCartsApi(updateCartsApiService);
        }
        return updateCartsApi;
    }

    public Observable<BaseBean> getupdateCartsApi(String uid, String sellerid, String pid, String num, String selected, String token) {
        return updateCartsApiService.updateCarts( uid, sellerid, pid,  num, selected, token);
    }
}
