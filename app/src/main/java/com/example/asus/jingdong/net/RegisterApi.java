package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.UserBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/13.
 */

public class RegisterApi {
    private static RegisterApi registerApi;
    private RegisterApiService registerApiService;

    private RegisterApi(RegisterApiService registerApiService) {
        this.registerApiService = registerApiService;
    }

    public static RegisterApi getRegisterApi(RegisterApiService registerApiService) {
        if (registerApi == null) {
            registerApi = new RegisterApi(registerApiService);
        }
        return registerApi;
    }

    public Observable<UserBean> getRegisterApi(String mobile, String password) {
        return registerApiService.Register(mobile, password);
    }
}
