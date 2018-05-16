package com.example.asus.jingdong.ui.Search.contract;

import com.example.asus.jingdong.bean.ProductsBean;
import com.example.asus.jingdong.ui.SpLb.contract.ListContract;
import com.example.asus.jingdong.ui.base.BaseContract;

/**
 * Created by asus on 2018/5/14.
 */

public interface SearchContract {
    interface View extends BaseContract.BaseView{
        void getProductsSuccess(ProductsBean productsBean);

    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getPresenter(String keywords,String pscid);
    }
}
