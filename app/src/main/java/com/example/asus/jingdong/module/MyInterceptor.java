package com.example.asus.jingdong.module;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by asus on 2018/4/25.
 */

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response;
        //区分是GET请求还是POST请求
        Request originRequest = chain.request();
        if ("GET".equals(originRequest.method())) {
            HttpUrl httpUrl = originRequest.url()
                    .newBuilder()
                    .addQueryParameter("source", "android")
                    .build();
            Request request = new Request.Builder().url(httpUrl).build();
            //发送请求
            response = chain.proceed(request);
        } else {
            FormBody.Builder builder = new FormBody.Builder();

            FormBody body = (FormBody) originRequest.body();
            int size = body.size();
            for (int i = 0; i < size; i++) {
                builder.add(body.name(i), body.value(i));
            }
            builder.add("source", "android");
            FormBody formBody = builder.build();
            Request request = new Request.Builder()
                    .url(originRequest.url())
                    .post(formBody)
                    .build();
            response = chain.proceed(request);

        }
        return response;
    }
}
