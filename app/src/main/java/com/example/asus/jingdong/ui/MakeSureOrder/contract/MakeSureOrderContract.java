package com.example.asus.jingdong.ui.MakeSureOrder.contract;

import com.example.asus.jingdong.bean.AddrsBean;
import com.example.asus.jingdong.ui.base.BaseContract;

import java.util.List;

/**
 * Created by asus on 2018/5/17.
 */

public interface MakeSureOrderContract {
    interface View extends BaseContract.BaseView {
        void getaddrsSuccess(List<AddrsBean.DataBean> list);

        void getcreateOrderSuccess(String msg);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getAddrs(String uid, String token);

        void getcreateOrder(String uid, String price );
    }
}
