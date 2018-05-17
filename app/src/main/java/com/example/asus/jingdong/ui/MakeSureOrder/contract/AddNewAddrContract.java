package com.example.asus.jingdong.ui.MakeSureOrder.contract;

import com.example.asus.jingdong.bean.AddrsBean;
import com.example.asus.jingdong.ui.base.BaseContract;

import java.util.List;

/**
 * Created by asus on 2018/5/17.
 */

public interface AddNewAddrContract {
    interface View extends BaseContract.BaseView {
        void getaddaddrSuccess(String msg);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getAddaddr(String uid,String addr,String mobile,String name, String token);

    }
}
