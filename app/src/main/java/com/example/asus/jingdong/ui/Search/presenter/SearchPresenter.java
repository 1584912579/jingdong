package com.example.asus.jingdong.ui.Search.presenter;

import com.example.asus.jingdong.bean.ProductsBean;
import com.example.asus.jingdong.net.SearchApi;
import com.example.asus.jingdong.ui.Search.contract.SearchContract;
import com.example.asus.jingdong.ui.SpLb.contract.ListContract;
import com.example.asus.jingdong.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/5/14.
 */

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {
    private SearchApi searchApi;
    @Inject
    public SearchPresenter(SearchApi searchApi) {
        this.searchApi = searchApi;
    }

    @Override
    public void getPresenter(String keywords,String pscid) {
        searchApi.getSearchApi(keywords,pscid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ProductsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductsBean productsBean) {
                        mView.getProductsSuccess(productsBean);
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
