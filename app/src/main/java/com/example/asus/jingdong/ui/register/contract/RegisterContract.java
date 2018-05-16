package com.example.asus.jingdong.ui.register.contract;

import com.example.asus.jingdong.bean.ProductsBean;
import com.example.asus.jingdong.bean.UserBean;
import com.example.asus.jingdong.ui.base.BaseContract;

/**
 * Created by asus on 2018/5/11.
 */

public interface RegisterContract {
    interface View extends BaseContract.BaseView {
        void RegisterSuccess(UserBean userBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void Register(String mobile, String password);
    }
}
