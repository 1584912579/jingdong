package com.example.asus.jingdong.bean.eventbus;

import com.example.asus.jingdong.bean.GetCartsBean;
import com.example.asus.jingdong.bean.SellerBean;

import java.util.List;

/**
 * Created by asus on 2018/5/17.
 */

public class MessageEvent {
    private List<SellerBean> gList;
    private List<List<GetCartsBean.DataBean.ListBean>> cList;

    public List<SellerBean> getgList() {
        return gList;
    }

    public void setgList(List<SellerBean> gList) {
        this.gList = gList;
    }

    public List<List<GetCartsBean.DataBean.ListBean>> getcList() {
        return cList;
    }

    public void setcList(List<List<GetCartsBean.DataBean.ListBean>> cList) {
        this.cList = cList;
    }
}
