package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.AdBean;
import com.example.asus.jingdong.bean.BaseBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/17.
 */

public class AddAddrApi {
    private static AddAddrApi addAddrApi;
    private AddAddrApiService addAddrApiService;

    private AddAddrApi(AddAddrApiService addAddrApiService) {
        this.addAddrApiService = addAddrApiService;
    }

    public static AddAddrApi getAddAddrApi(AddAddrApiService addAddrApiService) {
        if (addAddrApi == null) {
            addAddrApi = new AddAddrApi(addAddrApiService);
        }
        return addAddrApi;
    }

    public Observable<BaseBean> getAddAddrApi(String uid,String addr, String mobile,String name,String token) {
        return addAddrApiService.getAddAddr(uid,addr,mobile,name,token);
    }
}
