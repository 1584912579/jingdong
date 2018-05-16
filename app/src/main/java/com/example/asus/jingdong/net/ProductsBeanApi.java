package com.example.asus.jingdong.net;

import com.example.asus.jingdong.bean.ProductsBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/11.
 */

public class ProductsBeanApi {
    private static ProductsBeanApi productsBeanApi;
    private ProductsBeanApiService productsBeanApiService;

    private ProductsBeanApi(ProductsBeanApiService productsBeanApiService) {
        this.productsBeanApiService = productsBeanApiService;
    }

    public static ProductsBeanApi getProductsBeanApi(ProductsBeanApiService productsBeanApiService1) {
        if (productsBeanApi == null) {
            productsBeanApi = new ProductsBeanApi(productsBeanApiService1);
        }
        return productsBeanApi;
    }

    public Observable<ProductsBean> getProducts(String pscid) {
        return productsBeanApiService.getProducts(pscid);
    }
}
