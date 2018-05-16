package com.example.asus.jingdong.ui.dlassify.contract;

import com.example.asus.jingdong.bean.CatagoryBean;
import com.example.asus.jingdong.bean.ProductCatagoryBean;
import com.example.asus.jingdong.ui.base.BaseContract;

/**
 * Created by asus on 2018/5/11.
 */

public interface ClassifyContract {
    interface View extends BaseContract.BaseView{
        void getProductCatagorySuccess(ProductCatagoryBean productCatagoryBean);

        void getCatagorySuccess(CatagoryBean catagoryBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getPresenter(String cid);
        void getCatagory();
    }
}
