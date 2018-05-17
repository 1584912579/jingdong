package com.example.asus.jingdong.ui.MakeSureOrder.presenter;

import com.example.asus.jingdong.bean.AddrsBean;
import com.example.asus.jingdong.bean.BaseBean;
import com.example.asus.jingdong.net.AddAddrApi;
import com.example.asus.jingdong.ui.MakeSureOrder.contract.AddNewAddrContract;
import com.example.asus.jingdong.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/5/17.
 */

public class AddNewAddrPresenter extends BasePresenter<AddNewAddrContract.View> implements AddNewAddrContract.Presenter {
    private AddAddrApi addAddrApi;
    @Inject
    public AddNewAddrPresenter(AddAddrApi addAddrApi) {
        this.addAddrApi = addAddrApi;
    }

    @Override
    public void getAddaddr(String uid, String addr, String mobile, String name, String token) {
        addAddrApi.getAddAddrApi(uid,addr,mobile,name,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean,String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView!=null){
                    mView.getaddaddrSuccess(s);
                }
            }
        });

    }
}
