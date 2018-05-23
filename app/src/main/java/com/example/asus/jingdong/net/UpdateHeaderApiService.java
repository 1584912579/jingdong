package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.BaseBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by asus on 2018/5/22.
 */

public interface UpdateHeaderApiService {
    @Multipart
    @POST("file/upload")
    Observable<BaseBean> updateHeader(@Part("uid") RequestBody uid, @Part MultipartBody.Part file);

}
