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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.ui.login.LoginActivity;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by asus on 2018/5/10.
 */

public class Fragmentmine extends Fragment {
    private View view;
    private SimpleDraweeView mImg;
    /**
     * 登录/注册>
     */
    private TextView mText;
    /**
     * 新用户注册即送188红包
     */
    private TextView mSss;
    private LinearLayout mLine;
    private RecyclerView mRvD;
    private String uid;
    private String name;

    private String iconUrl;
    private PopupWindow pop;
    private LinearLayout ll_popup;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentmine, container, false);

        initView(view);
        //判断用户是否登录
        //判断的逻辑是，从SharedPreferences里取uid，如果能取到说明已经登录过，否则跳转到登录页面
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", "-1");
        name = sharedPreferences.getString("name", "");
        iconUrl = sharedPreferences.getString("iconUrl", "");
        if ("".equals(iconUrl)){

        }else{
            mImg.setImageURI(iconUrl);
        }

        if ("".equals(name)){

        }else {
            mText.setText(name);
        }
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //上传头像
                showPopupWindow();
            }
        });

        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCard();
            }
        });
        return view;
    }

    /****
     * 头像提示框
     */
    public void showPopupWindow() {
        pop = new PopupWindow(getContext());
        View view = getLayoutInflater().inflate(R.layout.item_popupwindows,
                null);
        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 1);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent picture = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(picture, 2);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
    }




    private void initView(View view) {
        mImg = (SimpleDraweeView) view.findViewById(R.id.img);
        mText = (TextView) view.findViewById(R.id.text);
        mSss = (TextView) view.findViewById(R.id.sss);
        mLine = (LinearLayout) view.findViewById(R.id.line);
        mRvD = (RecyclerView) view.findViewById(R.id.rv_d);
    }
    private void addCard() {

        if ("-1".equals(uid)){
            //跳转到登陆页面表示没登录
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);

        }

    }
}
