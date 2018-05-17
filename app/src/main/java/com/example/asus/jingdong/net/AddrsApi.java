package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.AddrsBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/17.
 */

public class AddrsApi {
    private static AddrsApi addrsApi;
    private AddrsApiService addrsApiService;

    private AddrsApi(AddrsApiService addrsApiService) {
        this.addrsApiService = addrsApiService;
    }

    public static AddrsApi getAddrsApi(AddrsApiService addrsApiService) {
        if (addrsApi == null) {
            addrsApi = new AddrsApi(addrsApiService);
        }
        return addrsApi;
    }

    public Observable<AddrsBean> getAddrs(String uid, String token) {
        return addrsApiService.getAddrs(uid, token);
    }
}
