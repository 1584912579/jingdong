package com.example.asus.jingdong.ui.MakeSureOrder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.component.DaggerHttpComponent;
import com.example.asus.jingdong.ui.MakeSureOrder.contract.AddNewAddrContract;
import com.example.asus.jingdong.ui.MakeSureOrder.presenter.AddNewAddrPresenter;
import com.example.asus.jingdong.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewAddrActivity extends BaseActivity<AddNewAddrPresenter> implements AddNewAddrContract.View {

    @BindView(R.id.addr)
    EditText mAddr;
    @BindView(R.id.mobile)
    EditText mMobile;
    @BindView(R.id.name)
    EditText mName;
    @BindView(R.id.btn_tj)
    Button mBtnTj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_addr);
        ButterKnife.bind(this);
        //添加收货地址
        //先去获取常用收货地址列表
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", "-1");
        String token = sharedPreferences.getString("token", "");
        String addr = mAddr.getText().toString();
        String mobile = mMobile.getText().toString();
        String name = mName.getText().toString();
        if(!TextUtils.isEmpty(addr)&&!TextUtils.isEmpty(addr)&&!TextUtils.isEmpty(addr)){
            mPresenter.getAddaddr(uid,addr,mobile,name,token);
        }

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_add_new_addr;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);
    }

    @Override
    public void getaddaddrSuccess(String msg) {
        Toast.makeText(AddNewAddrActivity.this,msg,Toast.LENGTH_SHORT).show();
    }


}
