package com.example.asus.jingdong.ui.MakeSureOrder.presenter;

import com.example.asus.jingdong.bean.AddrsBean;
import com.example.asus.jingdong.bean.BaseBean;
import com.example.asus.jingdong.net.AddrsApi;
import com.example.asus.jingdong.net.CreateOrderApi;
import com.example.asus.jingdong.ui.MakeSureOrder.contract.MakeSureOrderContract;
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

public class MakeSureOrderPresenter extends BasePresenter<MakeSureOrderContract.View> implements MakeSureOrderContract.Presenter {
    private AddrsApi addrsApi;
    private CreateOrderApi createOrderApi;
    @Inject
    public MakeSureOrderPresenter(AddrsApi addrsApi, CreateOrderApi createOrderApi) {
        this.addrsApi = addrsApi;
        this.createOrderApi = createOrderApi;
    }

    @Override
    public void getAddrs(String uid, String token) {
        addrsApi.getAddrs(uid,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<AddrsBean, List<AddrsBean.DataBean>>() {
                    @Override
                    public List<AddrsBean.DataBean> apply(AddrsBean addrsBean) throws Exception {
                        return addrsBean.getData();
                    }
                }).subscribe(new Consumer<List<AddrsBean.DataBean>>() {
            @Override
            public void accept(List<AddrsBean.DataBean> dataBeans) throws Exception {
                if (mView!=null){
                    mView.getaddrsSuccess(dataBeans);
                }
            }
        });

    }

    @Override
    public void getcreateOrder(String uid, String price ) {
        createOrderApi.GetCreateOrderApi(uid, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {

                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView != null) {
                    mView.getcreateOrderSuccess(s);
                }
            }
        });
    }
}
