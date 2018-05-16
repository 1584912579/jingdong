package com.example.asus.jingdong.ui.homepage.contract;

import com.example.asus.jingdong.bean.AdBean;
import com.example.asus.jingdong.bean.CatagoryBean;
import com.example.asus.jingdong.ui.base.BaseContract;

/**
 * Created by asus on 2018/5/10.
 */

public interface HomPageContract {
    interface View extends BaseContract.BaseView{
        void getAdSuccess(AdBean adBean);

        void getCatagorySuccess(CatagoryBean catagoryBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getAd();

        void getCatagory();
    }
}
