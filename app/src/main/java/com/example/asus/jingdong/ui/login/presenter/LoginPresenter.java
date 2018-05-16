package com.example.asus.jingdong.ui.login.presenter;

import com.example.asus.jingdong.bean.UserBean;
import com.example.asus.jingdong.net.LoginApi;
import com.example.asus.jingdong.ui.base.BasePresenter;
import com.example.asus.jingdong.ui.login.contract.LoginContract;
import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    private LoginApi loginApi;

    @Inject
    public LoginPresenter(LoginApi loginApi) {
        this.loginApi = loginApi;
    }

    @Override
    public void login(String mobile, String password) {

        loginApi.login(mobile, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        mView.loginSuccess(userBean);
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
