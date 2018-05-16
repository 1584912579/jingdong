package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.DatailsBean;
import com.example.asus.jingdong.bean.ProductCatagoryBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/12.
 */

public class DatailsApi {
    private static DatailsApi datailsApi;
    private DatailsApiService datailsApiService;

    private DatailsApi(DatailsApiService datailsApiService) {
        this.datailsApiService = datailsApiService;
    }
    public static DatailsApi getDatailsApi(DatailsApiService datailsApiService) {
        if (datailsApi == null) {
            datailsApi = new DatailsApi(datailsApiService);
        }
        return datailsApi;
    }

    public Observable<DatailsBean> getDatailsApi(String pid) {
        return datailsApiService.getDatails(pid);
    }
}
