package com.example.asus.jingdong.ui.MakeSureOrder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.AddrsBean;
import com.example.asus.jingdong.bean.GetCartsBean;
import com.example.asus.jingdong.bean.SellerBean;
import com.example.asus.jingdong.bean.eventbus.MessageEvent;
import com.example.asus.jingdong.component.DaggerHttpComponent;
import com.example.asus.jingdong.ui.MakeSureOrder.adapter.MakeSureOrderAdapter;
import com.example.asus.jingdong.ui.MakeSureOrder.contract.MakeSureOrderContract;
import com.example.asus.jingdong.ui.MakeSureOrder.presenter.MakeSureOrderPresenter;
import com.example.asus.jingdong.ui.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MakeSureOrderActivity extends BaseActivity<MakeSureOrderPresenter> implements MakeSureOrderContract.View {
    private ImageView mDetailImageBack;
    private RelativeLayout mDetaiRelative;
    /**
     * 收货人:
     */
    private TextView mTextName;
    private TextView mTextPhone;
    private ImageView mDingWeiImage;
    private TextView mTextAddr;
    private RelativeLayout mRelativeAddr01;
    private ExpandableListView elv;
    /**
     * 实付款:¥0.00
     */
    private TextView mTextShiFuKu;
    /**
     * 提交订单
     */
    private TextView mTextSubmitOrder;
    private LinearLayout mLinearBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化EventBus
        EventBus.getDefault().register(this);
        //setContentView(R.layout.activity_make_sure_order);
        initView();
//先去获取常用收货地址列表
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", "-1");
        String token = sharedPreferences.getString("token", "");

        if (mPresenter != null) {
            mPresenter.getAddrs(uid, token);
        }
        //点击改变收货人和地址
        mRelativeAddr01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky  = true)
    public void onMessageEvent(final MessageEvent event){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<List<GetCartsBean.DataBean.ListBean>> cList = event.getcList();
                List<SellerBean> gList = event.getgList();
                MakeSureOrderAdapter adapter = new MakeSureOrderAdapter(MakeSureOrderActivity.this, gList, cList);
                elv.setAdapter(adapter);
                for (int i = 0; i < gList.size(); i++) {
                    elv.expandGroup(i);
                }
            }
        },1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束EventBus
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        mDetailImageBack = (ImageView) findViewById(R.id.detail_image_back);
        mDetaiRelative = (RelativeLayout) findViewById(R.id.detai_relative);
        mTextName = (TextView) findViewById(R.id.text_name);
        mTextPhone = (TextView) findViewById(R.id.text_phone);
        mDingWeiImage = (ImageView) findViewById(R.id.ding_wei_image);
        mTextAddr = (TextView) findViewById(R.id.text_addr);
        mRelativeAddr01 = (RelativeLayout) findViewById(R.id.relative_addr_01);
        elv = (ExpandableListView) findViewById(R.id.elv);
        mTextShiFuKu = (TextView) findViewById(R.id.text_shi_fu_ku);
        mTextSubmitOrder = (TextView) findViewById(R.id.text_submit_order);
        mLinearBottom = (LinearLayout) findViewById(R.id.linear_bottom);
    }
    @Override
    public int getContentLayout() {
        return R.layout.activity_make_sure_order;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);
    }

    @Override
    public void getaddrsSuccess(List<AddrsBean.DataBean> list) {
        if (list != null && list.size() > 0) {
            //如果有数据，说明之前添加过地址
            Toast.makeText(MakeSureOrderActivity.this,"获取到地址列表",Toast.LENGTH_SHORT).show();
            mTextName.setText(list.get(0).getName());
            mTextAddr.setText(list.get(0).getAddr());

        } else {
            //如果没有数据，则没有则弹出一个dialog
            onGetDefaultAddrEmpty();
        }
    }
    public void onGetDefaultAddrEmpty() {
        //弹出对话框...取消,,,finish/////确定...添加新地址...没添加点击了返回,当前确认订单页面finish,,,如果添加了显示地址
        AlertDialog.Builder builder = new AlertDialog.Builder(MakeSureOrderActivity.this);
        builder.setTitle("提示")
                .setMessage("您还没有默认的收货地址,请设置收货地址")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //结束确认订单显示
                        MakeSureOrderActivity.this.finish();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //确定...跳转添加新地址...如果没有保存地址,确认订单finish,,,
                        //如果保存了地址...数据传回来进行显示(带有回传值的跳转)
                        Intent intent = new Intent(MakeSureOrderActivity.this, AddNewAddrActivity.class);
                        startActivityForResult(intent, 1001);
                    }
                })
                .show();
    }

    @Override
    public void getcreateOrderSuccess(String msg) {

    }
}
