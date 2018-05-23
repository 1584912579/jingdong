package com.example.asus.jingdong.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.jingdong.R;
import com.example.asus.jingdong.ui.base.BaseFragment;
import com.example.asus.jingdong.ui.login.LoginActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created by asus on 2018/5/10.
 */

public class Fragmentmine extends BaseFragment {
    private View view;
    private ImageView mMyUserIcon;
    /**
     * 登录/注册 >
     */
    private RecyclerView tui_jian_recycler;
    private LinearLayout my_linear_login;
    private ImageView my_user_icon;
    private TextView my_user_name;
    private LinearLayout my_order_dfk;
    private LinearLayout my_order_dpj;
    private LinearLayout my_order_dsh;
    private LinearLayout my_order_th;
    private LinearLayout my_order_all;
    private ScrollView fragment_my_scroll;
    private RelativeLayout login_back_pic;
    private LinearLayout smart_refresh;
    private String uid;
    private String name;
    private String iconUrl;

    @Override
    public int getContentLayout() {
        return R.layout.fragmentmine;
    }

    @Override
    public void inject() {

    }
    @Override
    public void initView(View view) {
        tui_jian_recycler = view.findViewById(R.id.tui_jian_recycler);
        my_linear_login = view.findViewById(R.id.my_linear_login);
        my_user_icon = view.findViewById(R.id.my_user_icon);
        my_user_name = view.findViewById(R.id.my_user_name);
        my_order_dfk = view.findViewById(R.id.my_order_dfk);
        my_order_dpj = view.findViewById(R.id.my_order_dpj);
        my_order_dsh = view.findViewById(R.id.my_order_dsh);
        my_order_th = view.findViewById(R.id.my_order_th);
        my_order_all = view.findViewById(R.id.my_order_all);
        fragment_my_scroll = view.findViewById(R.id.fragment_my_scroll);
        login_back_pic = view.findViewById(R.id.login_back_pic);
        smart_refresh = view.findViewById(R.id.smart_refresh);

    }
    @Override
    public void onResume() {
        super.onResume();
        //判断用户是否登录
        //判断的逻辑是，从SharedPreferences里取uid，如果能取到说明已经登录过，否则跳转到登录页面
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", "");
        name = sharedPreferences.getString("name", "");
        iconUrl = sharedPreferences.getString("iconUrl", "");
        if (!TextUtils.isEmpty(uid)) {
            //登录过
            login_back_pic.setBackgroundResource(R.drawable. reg_bg);
        } else {
            //未登录
            login_back_pic.setBackgroundResource(R.drawable.normal_regbg);
        }
        if (!TextUtils.isEmpty(iconUrl)) {
            Glide.with(getContext()).load(iconUrl).into(my_user_icon);
        }else {
            //@drawable/user
            my_user_icon.setImageResource(R.drawable.user);
        }
        if (!TextUtils.isEmpty(name)) {
            my_user_name.setText(name);
        }else {
            my_user_name.setText("登录/注册 >");
        }
        my_linear_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(uid)) {
                    //未登录
                    Intent intent = new Intent(getContext(), LoginActivity.class);

                    startActivity(intent);
                } else {
                    //已登录
                    Intent intent = new Intent(getContext(), UserInfoActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

}
