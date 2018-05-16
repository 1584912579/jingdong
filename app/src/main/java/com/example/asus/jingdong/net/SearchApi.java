package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.ProductsBean;
import com.example.asus.jingdong.bean.UserBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/14.
 */

public class SearchApi {
    private static SearchApi searchApi;
    private SearchApiService searchApiService;

    private SearchApi(SearchApiService searchApiService) {
        this.searchApiService = searchApiService;
    }

    public static SearchApi getSearchApi(SearchApiService searchApiService) {
        if (searchApi == null) {
            searchApi = new SearchApi(searchApiService);
        }
        return searchApi;
    }

    public Observable<ProductsBean> getSearchApi(String keywords,String pscid) {
        return searchApiService.getSearchApi(keywords,pscid);
    }
}
