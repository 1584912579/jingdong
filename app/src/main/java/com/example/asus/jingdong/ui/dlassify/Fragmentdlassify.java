package com.example.asus.jingdong.ui.dlassify;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dash.zxinglibrary.activity.CaptureActivity;
import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.CatagoryBean;
import com.example.asus.jingdong.bean.ProductCatagoryBean;
import com.example.asus.jingdong.component.DaggerHttpComponent;
import com.example.asus.jingdong.inter.OnItemClickListener;
import com.example.asus.jingdong.module.HttpModule;
import com.example.asus.jingdong.ui.base.BaseFragment;
import com.example.asus.jingdong.ui.dlassify.adapter.ElvAdapter;
import com.example.asus.jingdong.ui.dlassify.adapter.RvLeftAdapter;
import com.example.asus.jingdong.ui.dlassify.contract.ClassifyContract;
import com.example.asus.jingdong.ui.dlassify.presenter.ClassifyPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/5/10.
 */

public class Fragmentdlassify extends BaseFragment<ClassifyPresenter> implements ClassifyContract.View {
    private View view;
    private LinearLayout mIvZxing;
    private RecyclerView mRvLeft;
    private ImageView mIv;
    private ExpandableListView mElv;
    @Override
    public int getContentLayout() {
        return R.layout.fragmentclassify;
    }
    //绑定
    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {
        mIvZxing = (LinearLayout) view.findViewById(R.id.ivZxing);
        mRvLeft = (RecyclerView) view.findViewById(R.id.rvLeft);
        mIv = (ImageView) view.findViewById(R.id.iv);
        mIv.setImageResource(R.drawable.timg);
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mIvZxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent,1);
            }
        });
        //数据加载
        mPresenter.getCatagory();
        mPresenter.getPresenter("1");
    }

    @Override
    public void getProductCatagorySuccess(ProductCatagoryBean productCatagoryBean) {
        //取数据
        List<String> groupList=new ArrayList<>();
        List<List<ProductCatagoryBean.DataBean.ListBean>> childList=new ArrayList<>();
        List<ProductCatagoryBean.DataBean> data = productCatagoryBean.getData();
        for (int i = 0; i < data.size() ; i++) {
            String name = data.get(i).getName();
            groupList.add(name);
            List<ProductCatagoryBean.DataBean.ListBean> list = data.get(i).getList();
            childList.add(list);
        }
        ElvAdapter elvAdapter = new ElvAdapter(getContext(), groupList, childList);
        mElv.setAdapter(elvAdapter);
        //默认展开列表
        for (int i = 0; i < groupList.size(); i++) {
            mElv.expandGroup(i);
        }
    }

    @Override
    public void getCatagorySuccess(CatagoryBean catagoryBean) {
        final List<CatagoryBean.DataBean> data = catagoryBean.getData();
        //设置布局管理器
        mRvLeft.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvLeft.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
        //创建适配器
        final RvLeftAdapter rvLeftAdapter = new RvLeftAdapter(getContext(), data);
        mRvLeft.setAdapter(rvLeftAdapter);
        //设置第一个为默认选中
        rvLeftAdapter.changeCheck(0, true);
        //点击事件
        rvLeftAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                rvLeftAdapter.changeCheck(position,true);
                int cid = data.get(position).getCid();
                mPresenter.getPresenter(cid+"");
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });


    }
}
