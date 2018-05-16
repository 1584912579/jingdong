package com.example.asus.jingdong.ui.Details.contract;

import com.example.asus.jingdong.bean.AddCartBean;
import com.example.asus.jingdong.bean.DatailsBean;
import com.example.asus.jingdong.bean.ProductsBean;
import com.example.asus.jingdong.ui.base.BaseContract;

/**
 * Created by asus on 2018/5/12.
 */

public interface DetailsContract {
    interface View extends BaseContract.BaseView{
        void getDatailsSuccess(DatailsBean datailsBean);
        void getAddCartSuccess(AddCartBean addCartBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getPresenter(String pid);
        void getAddCartPresenter(String uid,String pid,String token);
    }
}
