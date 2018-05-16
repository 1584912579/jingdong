package com.example.asus.jingdong.ui.register.Presenter;

import com.example.asus.jingdong.bean.UserBean;
import com.example.asus.jingdong.net.RegisterApi;
import com.example.asus.jingdong.ui.base.BasePresenter;
import com.example.asus.jingdong.ui.login.contract.LoginContract;
import com.example.asus.jingdong.ui.register.contract.RegisterContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/5/13.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {
    private RegisterApi registerApi;
    @Inject
    public RegisterPresenter(RegisterApi registerApi) {
        this.registerApi = registerApi;
    }
    @Override
    public void Register(final String mobile, String password) {
        registerApi.getRegisterApi(mobile,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserBean userBean) {
                mView.RegisterSuccess(userBean);
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
