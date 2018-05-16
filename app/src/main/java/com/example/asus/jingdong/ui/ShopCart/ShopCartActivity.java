package com.example.asus.jingdong.ui.ShopCart;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.GetCartsBean;
import com.example.asus.jingdong.bean.SellerBean;
import com.example.asus.jingdong.component.DaggerHttpComponent;
import com.example.asus.jingdong.module.HttpModule;
import com.example.asus.jingdong.ui.base.BaseActivity;
import com.example.asus.jingdong.ui.shopping.adapter.ElvShopcartAdapter;
import com.example.asus.jingdong.ui.shopping.contract.GetCartsCintract;
import com.example.asus.jingdong.ui.shopping.presenter.GetCartsPresenter;

import java.util.List;

public class ShopCartActivity extends BaseActivity<GetCartsPresenter> implements GetCartsCintract.View {
    private View view;
    private ExpandableListView mElvvv;
    private CheckBox mCbAll;
    private TextView mTvMoney;
    private TextView mTvTotal;
    private ElvShopcartAdapter adapter;
    @Override
    public int getContentLayout() {
        return R.layout.activity_shop_cart;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_shop_cart);
        mElvvv = (ExpandableListView) findViewById(R.id.elvvvq);
        mCbAll = (CheckBox) findViewById(R.id.cbAllq);
        mTvMoney = (TextView) findViewById(R.id.tvMoneyq);
        mTvTotal = (TextView) findViewById(R.id.tvTotalq);
        mCbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (adapter != null) {
//                    adapter.changeAllState(mCbAll.isChecked());
//                }
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", "-1");
        String token = sharedPreferences.getString("token", "");
        mPresenter.getPresenter(uid,token);

    }



    @Override
    public void getGetCartsSuccess(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList) {
//判断所有商家是否全部选中
        mCbAll.setChecked(isSellerAddSelected(groupList));
        //创建适配器
        adapter = new ElvShopcartAdapter(ShopCartActivity.this, groupList, childList );
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
}
