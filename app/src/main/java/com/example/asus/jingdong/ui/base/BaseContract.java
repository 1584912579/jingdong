package com.example.asus.jingdong.ui.base;

/**
 * Created by asus on 2018/5/10.
 */

public interface BaseContract {
    //抽取所有presenter共性 比如绑定
    interface BasePresenter<T extends BaseView>{
        //绑定
        void attchView(T view);
        //解绑
        void detachView();
    }
    //抽取所有view中的共性 比如显示进度条和关闭进度条
    interface BaseView{
        void showLoading();
        void dismissLoading();
    }
}
