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
        void getupdateCartsSuccess();
        void getDeleteCartsSuccess();
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getGetCartsPresenter(String uid,String token);
        void getupdatePresenter(String uid, String sellerid, String pid, String num, String selected, String token);
        void getDeletePresenter(String uid, String pid, String token);
    }
}
