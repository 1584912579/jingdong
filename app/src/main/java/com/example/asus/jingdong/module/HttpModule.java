package com.example.asus.jingdong.module;

import com.example.asus.jingdong.net.AdApi;
import com.example.asus.jingdong.net.AdApiService;
import com.example.asus.jingdong.net.AddCartApi;
import com.example.asus.jingdong.net.AddCartApiService;
import com.example.asus.jingdong.net.Api;
import com.example.asus.jingdong.net.CatagoryApi;
import com.example.asus.jingdong.net.CatagoryApiService;
import com.example.asus.jingdong.net.DatailsApi;
import com.example.asus.jingdong.net.DatailsApiService;
import com.example.asus.jingdong.net.GetCartsApi;
import com.example.asus.jingdong.net.GetCartsApiServive;
import com.example.asus.jingdong.net.LoginApi;
import com.example.asus.jingdong.net.LoginApiService;
import com.example.asus.jingdong.net.ProductCatagoryApi;
import com.example.asus.jingdong.net.ProductCatagoryApiService;
import com.example.asus.jingdong.net.ProductsBeanApi;
import com.example.asus.jingdong.net.ProductsBeanApiService;
import com.example.asus.jingdong.net.RegisterApi;
import com.example.asus.jingdong.net.RegisterApiService;
import com.example.asus.jingdong.net.SearchApi;
import com.example.asus.jingdong.net.SearchApiService;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by asus on 2018/5/10.
 */
@Module
public class HttpModule {
    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new MyInterceptor());//拦截器
    }
    @Provides
    AdApi provideAdApi(OkHttpClient.Builder builder) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        AdApiService adApiService = retrofit.create(AdApiService.class);
        return AdApi.getAdApi(adApiService);
    }

    @Provides
    CatagoryApi provideCatagoryApi(OkHttpClient.Builder builder) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        CatagoryApiService catagoryApiService = retrofit.create(CatagoryApiService.class);
        return CatagoryApi.getCatagoryApi(catagoryApiService);
    }
    @Provides
    ProductCatagoryApi provideProductCatagoryApi(OkHttpClient.Builder builder) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        ProductCatagoryApiService productCatagoryApiService = retrofit.create(ProductCatagoryApiService.class);
        return ProductCatagoryApi.getProductCatagoryApi(productCatagoryApiService);
    }
    @Provides
    ProductsBeanApi provideProductsBeanApi(OkHttpClient.Builder builder) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        ProductsBeanApiService productsBeanApi = retrofit.create(ProductsBeanApiService.class);
        return ProductsBeanApi.getProductsBeanApi(productsBeanApi);
    }
    @Provides
    DatailsApi provideDatailsApi(OkHttpClient.Builder builder) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        DatailsApiService datailsApiService = retrofit.create(DatailsApiService.class);
        return DatailsApi.getDatailsApi(datailsApiService);
    }
    @Provides
    LoginApi provideLoginApi(OkHttpClient.Builder builder) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        LoginApiService loginApiService = retrofit.create(LoginApiService.class);
        return LoginApi.getLoginApi(loginApiService);
    }
    @Provides
    RegisterApi provideRegisterApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        RegisterApiService registerApiService = retrofit.create(RegisterApiService.class);
        return RegisterApi.getRegisterApi(registerApiService);
    }
    @Provides
    AddCartApi provideAddCartApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        AddCartApiService addCartApiService = retrofit.create(AddCartApiService.class);
        return AddCartApi.getAddCartApi(addCartApiService);

    }
    @Provides
    SearchApi provideSearchApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.addInterceptor(new MyInterceptor()).build())
                .build();
        SearchApiService searchApiService = retrofit.create(SearchApiService.class);
        return SearchApi.getSearchApi(searchApiService);

    }
    @Provides
    GetCartsApi provideGetCartsApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        GetCartsApiServive getCartsApiServive = retrofit.create(GetCartsApiServive.class);
        return GetCartsApi.getGetCartsApi(getCartsApiServive);

    }
}
