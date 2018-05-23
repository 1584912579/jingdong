package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.BaseBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/23.
 */

public class UpdateNickNameApi {
    private static UpdateNickNameApi updateNickNameApi;
    private UpdateNickNameApiService updateNickNameApiService;

    private UpdateNickNameApi(UpdateNickNameApiService updateNickNameApiService) {
        this.updateNickNameApiService = updateNickNameApiService;
    }

    public static UpdateNickNameApi getUpdateNickNameApi(UpdateNickNameApiService updateNickNameApiService) {
        if (updateNickNameApi == null) {
            updateNickNameApi = new UpdateNickNameApi(updateNickNameApiService);
        }
        return updateNickNameApi;
    }

    public Observable<BaseBean> getUpdateNickNameApi(String uid, String nickname) {
        return updateNickNameApiService.UpdateNickName( uid, nickname);
    }
}
