package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.BaseBean;
import com.example.asus.jingdong.bean.GetCartsBean;
import com.example.asus.jingdong.bean.GetOrdersBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/17.
 */

public class GetOrdersApi {
    private static GetOrdersApi getOrdersApi;
    private GetOrdersApiService getOrdersApiService;

    private GetOrdersApi(GetOrdersApiService getOrdersApiService) {
        this.getOrdersApiService = getOrdersApiService;
    }

    public static GetOrdersApi getGetOrdersApi(GetOrdersApiService getOrdersApiService) {
        if (getOrdersApi == null) {
            getOrdersApi = new GetOrdersApi(getOrdersApiService);
        }
        return getOrdersApi;
    }

    public Observable<GetOrdersBean> GetOrdersApi(String uid, String page , String token) {
        return getOrdersApiService.getGetOrders(uid,page,token);
    }
    public Observable<BaseBean> updateOrder(String uid, String status, String orderId, String token) {
        return getOrdersApiService.updateOrder(uid, status, orderId, token);
    }
}
