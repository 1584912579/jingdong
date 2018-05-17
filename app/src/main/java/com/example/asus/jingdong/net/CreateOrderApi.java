package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.BaseBean;
import com.example.asus.jingdong.bean.GetOrdersBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/17.
 */

public class CreateOrderApi {
    private static CreateOrderApi createOrderApi;
    private CreateOrderApiService createOrderApiService;

    private CreateOrderApi(CreateOrderApiService createOrderApiService) {
        this.createOrderApiService = createOrderApiService;
    }

    public static CreateOrderApi getCreateOrderApi(CreateOrderApiService createOrderApiService) {
        if (createOrderApi == null) {
            createOrderApi = new CreateOrderApi(createOrderApiService);
        }
        return createOrderApi;
    }

    public Observable<BaseBean> GetCreateOrderApi(String uid, String Price , String token) {
        return createOrderApiService.createOrder(uid,Price,token);
    }
}
