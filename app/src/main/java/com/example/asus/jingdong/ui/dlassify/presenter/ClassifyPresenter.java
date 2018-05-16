package com.example.asus.jingdong.ui.dlassify.presenter;

import com.example.asus.jingdong.bean.CatagoryBean;
import com.example.asus.jingdong.bean.ProductCatagoryBean;
import com.example.asus.jingdong.net.CatagoryApi;
import com.example.asus.jingdong.net.ProductCatagoryApi;
import com.example.asus.jingdong.ui.base.BasePresenter;
import com.example.asus.jingdong.ui.dlassify.contract.ClassifyContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/5/11.
 */

public class ClassifyPresenter extends BasePresenter<ClassifyContract.View> implements ClassifyContract.Presenter {
   private ProductCatagoryApi productCatagoryApi;
   private CatagoryApi catagoryApi;
    @Inject
    public ClassifyPresenter(ProductCatagoryApi productCatagoryApi, CatagoryApi catagoryApi) {
        this.productCatagoryApi = productCatagoryApi;
        this.catagoryApi = catagoryApi;
    }

    @Override
    public void getPresenter(String cid) {
        productCatagoryApi.getProductCatagory(cid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ProductCatagoryBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProductCatagoryBean productCatagoryBean) {
                if (mView != null) {
                    mView.getProductCatagorySuccess(productCatagoryBean);
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

    @Override
    public void getCatagory() {
        catagoryApi.getCatagory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CatagoryBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CatagoryBean catagoryBean) {
                if (mView!=null){
                    mView.getCatagorySuccess(catagoryBean);
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
