package com.example.asus.jingdong.ui.SpLb.contract;

import com.example.asus.jingdong.bean.ProductsBean;
import com.example.asus.jingdong.ui.base.BaseContract;

public interface ListContract {
    interface View extends BaseContract.BaseView{
        void getProductsSuccess(ProductsBean productsBean);

    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getPresenter(String pscid);
    }
}
