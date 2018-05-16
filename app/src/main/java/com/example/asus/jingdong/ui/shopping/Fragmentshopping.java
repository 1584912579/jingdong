package com.example.asus.jingdong.ui.shopping;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.GetCartsBean;
import com.example.asus.jingdong.bean.SellerBean;
import com.example.asus.jingdong.component.DaggerHttpComponent;
import com.example.asus.jingdong.module.HttpModule;
import com.example.asus.jingdong.ui.base.BaseFragment;
import com.example.asus.jingdong.ui.shopping.adapter.ElvShopcartAdapter;
import com.example.asus.jingdong.ui.shopping.contract.GetCartsCintract;
import com.example.asus.jingdong.ui.shopping.presenter.GetCartsPresenter;

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
//                if (adapter != null) {
//                    adapter.changeAllState(mCbAll.isChecked());
//                }
            }
        });
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", "-1");
        String token = sharedPreferences.getString("token", "");
        mPresenter.getPresenter(uid,token);
    }

    @Override
    public void getGetCartsSuccess(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList) {
        //判断所有商家是否全部选中
        mCbAll.setChecked(isSellerAddSelected(groupList));
        //创建适配器
        adapter = new ElvShopcartAdapter(getContext(), groupList, childList );
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
