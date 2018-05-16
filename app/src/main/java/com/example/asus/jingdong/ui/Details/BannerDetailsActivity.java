package com.example.asus.jingdong.ui.Details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.DatailsBean;
import com.example.asus.jingdong.bean.ProductsBean;
import com.example.asus.jingdong.ui.Details.adapter.MyAdapter;
import com.example.asus.jingdong.ui.Details.widget.HackyViewPager;

import java.util.Arrays;
import java.util.List;

public class BannerDetailsActivity extends AppCompatActivity {

    private HackyViewPager mHvp;
    private TextView mTv;
    private int position;
    private DatailsBean.DataBean bean;
    private List<String> list;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_details);
        //获取JavaBean
        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        bean = (DatailsBean.DataBean) intent.getSerializableExtra("bean");
        String images = bean.getImages();
        String[] split = images.split("\\|");
        list = Arrays.asList(split);
        count = list.size();
        initView();

    }

    private void initView() {
        mHvp = (HackyViewPager) findViewById(R.id.hvp);
        mTv = (TextView) findViewById(R.id.tv);
        MyAdapter myAdapter = new MyAdapter(this, list);
        mHvp.setAdapter(myAdapter);
        //显示所点击的页面
        mHvp.setCurrentItem(position);
        //赋值
        mTv.setText((position + 1) + "/" + count);
        //监听滑动
        mHvp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int p) {
                BannerDetailsActivity.this.position = p;
                mTv.setText((position + 1) + "/" + count);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

