package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.GetCartsBean;
import com.example.asus.jingdong.bean.UserBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/15.
 */

public class GetCartsApi {
    private static GetCartsApi loginApi;
    private GetCartsApiServive getCartsApiServive;

    private GetCartsApi(GetCartsApiServive getCartsApiServive) {
        this.getCartsApiServive = getCartsApiServive;
    }

    public static GetCartsApi getGetCartsApi(GetCartsApiServive getCartsApiServive) {
        if (loginApi == null) {
            loginApi = new GetCartsApi(getCartsApiServive);
        }
        return loginApi;
    }

    public Observable<GetCartsBean> GetCartsApi(String uid, String token) {
        return getCartsApiServive.getGetCarts(uid,token);
    }
}
