package com.example.asus.jingdong.ui.OrderList.presenter;


import com.example.asus.jingdong.bean.BaseBean;
import com.example.asus.jingdong.bean.GetOrdersBean;
import com.example.asus.jingdong.net.GetOrdersApi;
import com.example.asus.jingdong.ui.OrderList.contract.OrdersContract;
import com.example.asus.jingdong.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class OrdersPresenter extends BasePresenter<OrdersContract.View> implements OrdersContract.Presenter {
    private GetOrdersApi getOrdersApi;

    @Inject
    public OrdersPresenter(GetOrdersApi getOrdersApi) {
        this.getOrdersApi = getOrdersApi;
    }

    @Override
    public void getOrders(String uid, String page, String token) {
        getOrdersApi.GetOrdersApi(uid, page, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<GetOrdersBean, List<GetOrdersBean.DataBean>>() {
                    @Override
                    public List<GetOrdersBean.DataBean> apply(GetOrdersBean getOrdersBean) throws Exception {

                        return getOrdersBean.getData();
                    }
                }).subscribe(new Consumer<List<GetOrdersBean.DataBean>>() {
            @Override
            public void accept(List<GetOrdersBean.DataBean> dataBeans) throws Exception {
                if (mView!=null){
                    mView.showOrders(dataBeans);
                }
            }
        });
    }

    @Override
    public void getWaitOrders(String uid, String page, String token) {
        getOrdersApi.GetOrdersApi(uid, page, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<GetOrdersBean, List<GetOrdersBean.DataBean>>() {
                    @Override
                    public List<GetOrdersBean.DataBean> apply(GetOrdersBean ordersBean) throws Exception {
                        return ordersBean.getData();
                    }
                }).subscribe(new Consumer<List<GetOrdersBean.DataBean>>() {
            @Override
            public void accept(List<GetOrdersBean.DataBean> dataBeans) throws Exception {
                List<GetOrdersBean.DataBean> list = new ArrayList<>();
                for (int i = 0; i < dataBeans.size(); i++) {
                    if (dataBeans.get(i).getStatus() == 0) {
                        list.add(dataBeans.get(i));
                    }
                }
                if (mView != null) {
                    mView.showOrders(list);
                }
            }
        });
    }

    @Override
    public void getAlreadyOrders(String uid, String page, String token) {
        getOrdersApi.GetOrdersApi(uid, page, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<GetOrdersBean, List<GetOrdersBean.DataBean>>() {
                    @Override
                    public List<GetOrdersBean.DataBean> apply(GetOrdersBean ordersBean) throws Exception {
                        return ordersBean.getData();
                    }
                }).subscribe(new Consumer<List<GetOrdersBean.DataBean>>() {
            @Override
            public void accept(List<GetOrdersBean.DataBean> dataBeans) throws Exception {
                List<GetOrdersBean.DataBean> list = new ArrayList<>();
                for (int i = 0; i < dataBeans.size(); i++) {
                    if (dataBeans.get(i).getStatus() == 1) {
                        list.add(dataBeans.get(i));
                    }
                }
                if (mView != null) {
                    mView.showOrders(list);
                }
            }
        });
    }

    @Override
    public void getCancleOrders(String uid, String page, String token) {
        getOrdersApi.GetOrdersApi(uid, page, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<GetOrdersBean, List<GetOrdersBean.DataBean>>() {
                    @Override
                    public List<GetOrdersBean.DataBean> apply(GetOrdersBean ordersBean) throws Exception {
                        return ordersBean.getData();
                    }
                }).subscribe(new Consumer<List<GetOrdersBean.DataBean>>() {
            @Override
            public void accept(List<GetOrdersBean.DataBean> dataBeans) throws Exception {
                List<GetOrdersBean.DataBean> list = new ArrayList<>();
                for (int i = 0; i < dataBeans.size(); i++) {
                    if (dataBeans.get(i).getStatus() == 2) {
                        list.add(dataBeans.get(i));
                    }
                }
                if (mView != null) {
                    mView.showOrders(list);
                }
            }
        });
    }

    @Override
    public void updateOrder(String uid, String status, String orderId, String token) {
        getOrdersApi.updateOrder(uid, status, orderId, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        if (mView!=null){
                            mView.updateOrderSuccess(baseBean);
                        }
                    }
                });
    }


}
