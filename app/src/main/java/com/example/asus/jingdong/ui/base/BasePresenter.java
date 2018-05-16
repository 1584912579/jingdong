package com.example.asus.jingdong.ui.base;

/**
 * 所有Presenter的积累，实现了BaseContract.BasePresenter接口
 * Created by asus on 2018/5/10.
 */

public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
    protected T mView;
    //绑定
    @Override
    public void attchView(T view) {
        this.mView=view;
    }
    //解绑
    @Override
    public void detachView() {
        if (mView!=null){
            mView=null;
        }
    }
}
