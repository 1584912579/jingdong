package com.example.asus.jingdong.ui.shopping.contract;

import com.example.asus.jingdong.bean.GetCartsBean;
import com.example.asus.jingdong.bean.SellerBean;
import com.example.asus.jingdong.ui.base.BaseContract;

import java.util.List;

/**
 * Created by asus on 2018/5/15.
 */

public interface GetCartsCintract {
    interface View extends BaseContract.BaseView{
        void getGetCartsSuccess(List<SellerBean> groupList,List<List<GetCartsBean.DataBean.ListBean>> childList);

    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getPresenter(String uid,String token);
    }
}
