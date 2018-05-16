package com.example.asus.jingdong.ui;


import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.adapter.MyFragmentadapter;
import com.example.asus.jingdong.ui.dlassify.Fragmentdlassify;
import com.example.asus.jingdong.ui.find.Fragmentfind;
import com.example.asus.jingdong.ui.homepage.FragmentHomePage;
import com.example.asus.jingdong.ui.mine.Fragmentmine;
import com.example.asus.jingdong.ui.shopping.Fragmentshopping;

import java.util.ArrayList;
import java.util.List;

public class showActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RadioGroup rg;
    private List<Fragment> list;
    private ViewPager mViewpager;
    private RadioGroup mRg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        //fullScreen(this);


        //数据
        list = new ArrayList<>();
        list.add(new FragmentHomePage());
        list.add(new Fragmentdlassify());
        list.add(new Fragmentfind());
        list.add(new Fragmentshopping());
        list.add(new Fragmentmine());
        MyFragmentadapter myFragmentadapter = new MyFragmentadapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(myFragmentadapter);
        //监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                rg.check(rg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rb4:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.rb5:
                        viewPager.setCurrentItem(4);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    //
    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        rg = (RadioGroup) findViewById(R.id.rg);
    }
}
