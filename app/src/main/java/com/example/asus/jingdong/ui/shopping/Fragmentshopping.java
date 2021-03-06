package com.example.asus.jingdong.ui.shopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.GetCartsBean;
import com.example.asus.jingdong.bean.SellerBean;
import com.example.asus.jingdong.bean.eventbus.MessageEvent;
import com.example.asus.jingdong.component.DaggerHttpComponent;
import com.example.asus.jingdong.module.HttpModule;
import com.example.asus.jingdong.ui.MakeSureOrder.MakeSureOrderActivity;
import com.example.asus.jingdong.ui.ShopCart.ShopCartActivity;
import com.example.asus.jingdong.ui.base.BaseFragment;
import com.example.asus.jingdong.ui.shopping.adapter.ElvShopcartAdapter;
import com.example.asus.jingdong.ui.shopping.contract.GetCartsCintract;
import com.example.asus.jingdong.ui.shopping.presenter.GetCartsPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by asus on 2018/5/10.
 */

public class Fragmentshopping extends BaseFragment<GetCartsPresenter> implements GetCartsCintract.View {
    private View view;
    private ExpandableListView mElvvv;
    private CheckBox mCbAll;
    private TextView mTvMoney;
    private TextView mTvTotal;
    private ElvShopcartAdapter adapter;
    @Override
    public int getContentLayout() {
        return R.layout.fragmentshopping;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {
        mElvvv = (ExpandableListView) view.findViewById(R.id.elvvv);
        mCbAll = (CheckBox) view.findViewById(R.id.cbAll);
        mTvMoney = (TextView) view.findViewById(R.id.tvMoney);
        mTvTotal = (TextView) view.findViewById(R.id.tvTotal);
        mCbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter != null) {
                    adapter.changeAllState(mCbAll.isChecked());
                }
            }
        });
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", "-1");
        String token = sharedPreferences.getString("token", "");
        mPresenter.getGetCartsPresenter(uid,token);
        //点击跳转结算页
        mTvTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MakeSureOrderActivity.class);
                String[] strings = adapter.computeMoneyAndNum();
                String nnn = strings[0];
                intent.putExtra("num",nnn);
                startActivity(intent);
                //把用户选中的商品传过去
                List<SellerBean> gList = adapter.getGroupList();
                List<List<GetCartsBean.DataBean.ListBean>> cList = adapter.getchildList();
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setcList(cList);
                messageEvent.setgList(gList);
                EventBus.getDefault().postSticky(messageEvent);
            }
        });
    }

    @Override
    public void getGetCartsSuccess(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList) {
        //判断所有商家是否全部选中
        mCbAll.setChecked(isSellerAddSelected(groupList));
        //创建适配器
        adapter = new ElvShopcartAdapter(getContext(), groupList, childList,mPresenter );
        mElvvv.setAdapter(adapter);
        //获取数量和总价
        String[] strings = adapter.computeMoneyAndNum();
        mTvMoney.setText("总计：" + strings[0] + "元");
        mTvTotal.setText("去结算("+strings[1]+"个)");
        //默认展开列表
        for (int i = 0; i < groupList.size(); i++) {
            mElvvv.expandGroup(i);
        }

    }
    //判断所有商家是否全部选中

    private boolean isSellerAddSelected(List<SellerBean> groupList) {
        for (int i = 0; i < groupList.size(); i++) {
            SellerBean sellerBean = groupList.get(i);
            if (!sellerBean.isSelected()) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void getupdateCartsSuccess() {
        if (adapter!=null){
            adapter.updataSuccess();
        }
    }

    @Override
    public void getDeleteCartsSuccess() {
        if (adapter!=null){
            adapter.delSuccess();
        }
    }

}
