package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.AdBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/10.
 */

public class AdApi {
    private static AdApi adApi;
    private AdApiService adApiService;

    private AdApi(AdApiService adApiService) {
        this.adApiService = adApiService;
    }

    public static AdApi getAdApi(AdApiService adApiService) {
        if (adApi == null) {
            adApi = new AdApi(adApiService);
        }
        return adApi;
    }

    public Observable<AdBean> getAd() {
        return adApiService.getAd();
    }
}
