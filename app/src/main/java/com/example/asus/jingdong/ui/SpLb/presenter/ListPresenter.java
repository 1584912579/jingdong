package com.example.asus.jingdong.ui.SpLb.presenter;

import com.example.asus.jingdong.bean.ProductsBean;
import com.example.asus.jingdong.net.ProductsBeanApi;
import com.example.asus.jingdong.ui.SpLb.contract.ListContract;
import com.example.asus.jingdong.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/5/11.
 */

public class ListPresenter extends BasePresenter<ListContract.View> implements ListContract.Presenter  {
    private ProductsBeanApi productsBeanApi;
    @Inject
    public ListPresenter(ProductsBeanApi productsBeanApi) {
        this.productsBeanApi = productsBeanApi;
    }

    @Override
    public void getPresenter(String pscid) {
        productsBeanApi.getProducts(pscid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ProductsBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProductsBean productsBean) {
                if (mView!=null){
                    mView.getProductsSuccess(productsBean);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
