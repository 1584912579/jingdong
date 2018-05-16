package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.AdBean;
import com.example.asus.jingdong.bean.CatagoryBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/10.
 */

public class CatagoryApi {
    private static CatagoryApi catagoryApi;
    private CatagoryApiService catagoryApiService;

    private CatagoryApi(CatagoryApiService catagoryApiService) {
        this.catagoryApiService = catagoryApiService;
    }

    public static CatagoryApi getCatagoryApi(CatagoryApiService catagoryApiService) {
        if (catagoryApi == null) {
            catagoryApi = new CatagoryApi(catagoryApiService);
        }
        return catagoryApi;
    }

    public Observable<CatagoryBean> getCatagory() {
        return catagoryApiService.getCatagory();
    }
}
