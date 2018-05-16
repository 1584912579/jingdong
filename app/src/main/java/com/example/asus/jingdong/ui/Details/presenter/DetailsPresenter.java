package com.example.asus.jingdong.ui.Details.presenter;

import com.example.asus.jingdong.bean.AddCartBean;
import com.example.asus.jingdong.bean.DatailsBean;
import com.example.asus.jingdong.net.AddCartApi;
import com.example.asus.jingdong.net.DatailsApi;
import com.example.asus.jingdong.ui.Details.contract.DetailsContract;
import com.example.asus.jingdong.ui.SpLb.contract.ListContract;
import com.example.asus.jingdong.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/5/12.
 */

public class DetailsPresenter extends BasePresenter<DetailsContract.View> implements DetailsContract.Presenter {
    private DatailsApi datailsApi;
    private AddCartApi addCartApi;
    @Inject
    public DetailsPresenter(DatailsApi datailsApi,AddCartApi addCartApi) {
        this.datailsApi = datailsApi;
        this.addCartApi = addCartApi;
    }

    @Override
    public void getPresenter(String pid) {
        datailsApi.getDatailsApi(pid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DatailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DatailsBean datailsBean) {
                        mView.getDatailsSuccess(datailsBean);
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
    public void getAddCartPresenter(String uid, String pid, String token) {
        addCartApi.getAddCartApi(uid, pid, token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<AddCartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddCartBean addCartBean) {
                        mView.getAddCartSuccess(addCartBean);
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
