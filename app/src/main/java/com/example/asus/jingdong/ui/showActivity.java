package com.example.asus.jingdong.ui;


import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.adapter.MyFragmentadapter;
import com.example.asus.jingdong.ui.base.BaseActivity;
import com.example.asus.jingdong.ui.dlassify.Fragmentdlassify;
import com.example.asus.jingdong.ui.find.Fragmentfind;
import com.example.asus.jingdong.ui.homepage.FragmentHomePage;
import com.example.asus.jingdong.ui.mine.Fragmentmine;
import com.example.asus.jingdong.ui.shopping.Fragmentshopping;

import java.util.ArrayList;
import java.util.List;

public class showActivity extends BaseActivity {

    private ViewPager viewPager;
    private RadioGroup rg;
    private List<Fragment> list;
    private ViewPager mViewpager;
    private RadioGroup mRg;
    private FragmentManager fragmentManager;
    private int currentIndex = 1;
    private RadioButton mRbHomepage;
    private FragmentHomePage fragmentHomePage;
    private Fragmentdlassify fragmentdlassify;
    private Fragmentfind fragmentfind;
    private Fragmentshopping fragmentshopping;
    private Fragmentmine fragmentmine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show);
        initView();
        //fullScreen(this);
//        one();
        fragmentManager = getSupportFragmentManager();
        fragmentHomePage = new FragmentHomePage();
        fragmentdlassify = new Fragmentdlassify();
        fragmentfind = new Fragmentfind();
        fragmentshopping = new Fragmentshopping();
        fragmentmine = new Fragmentmine();

        fragmentManager.beginTransaction()
                .replace(R.id.flont, fragmentHomePage)
                .commit();
        mRbHomepage.setChecked(true);
        //设置点击事件
        setLisenter();


    }
    private void setLisenter() {
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        //首页
                        if (currentIndex == 1) {
                            return;
                        }
                        currentIndex = 1;
                        fragmentManager.beginTransaction().replace(R.id.flont, fragmentHomePage).commit();
                        break;
                    case R.id.rb2:
                        if (currentIndex == 2) {
                            return;
                        }
                        currentIndex = 2;
                        fragmentManager.beginTransaction().replace(R.id.flont, fragmentdlassify).commit();
                        break;
                    case R.id.rb3:
                        if (currentIndex == 3) {
                            return;
                        }
                        currentIndex = 3;
                        fragmentManager.beginTransaction().replace(R.id.flont, fragmentfind).commit();
                        break;
                    case R.id.rb4:
                        if (currentIndex == 4) {
                            return;
                        }
                        currentIndex = 4;
                        fragmentManager.beginTransaction().replace(R.id.flont, fragmentshopping).commit();
                        break;
                    case R.id.rb5:
                        if (currentIndex == 5) {
                            return;
                        }
                        currentIndex = 5;
                        fragmentManager.beginTransaction().replace(R.id.flont, fragmentmine).commit();
                        break;
                }
            }
        });
    }
    public void one(){
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
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
//        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mRbHomepage = (RadioButton)  findViewById(R.id.rb1);
        mRg = (RadioGroup) findViewById(R.id.rg);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_show;
    }

    @Override
    public void inject() {

    }
}
