package com.example.asus.jingdong.ui.SpLb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.ProductsBean;
import com.example.asus.jingdong.component.DaggerHttpComponent;
import com.example.asus.jingdong.module.HttpModule;
import com.example.asus.jingdong.ui.Details.ListDetailsActivity;
import com.example.asus.jingdong.ui.SpLb.adapter.XrvListAdapter;
import com.example.asus.jingdong.ui.SpLb.contract.ListContract;
import com.example.asus.jingdong.ui.SpLb.presenter.ListPresenter;
import com.example.asus.jingdong.ui.base.BaseActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by asus on 2018/5/11.
 */

public class ListActivity extends BaseActivity<ListPresenter> implements ListContract.View {

    private XRecyclerView mXrv;
    private int pscid;
    private XrvListAdapter adapter;
    private boolean isRefresh = true;
    @Override
    public int getContentLayout() {
        return R.layout.activity_list;
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
        // setContentView(R.layout.activity_list);
        mXrv = (XRecyclerView) findViewById(R.id.xrv);
        //获取pscid
        Intent intent = getIntent();
        pscid = intent.getIntExtra("pscid", 0);
        mPresenter.getPresenter(pscid+"");
    }



    @Override
    public void getProductsSuccess(final ProductsBean productsBean) {
        //设置布局管理器
        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mXrv.setLayoutManager(linearLayoutManager);
        final List<ProductsBean.DataBean> list=productsBean.getData();
        final List<ProductsBean.DataBean> tempList = new ArrayList<>();
        tempList.addAll(list);
        //创建适配器
        if (isRefresh) {
            adapter = new XrvListAdapter(this, list);
            mXrv.setAdapter(adapter);
            adapter.refresh(tempList);
            mXrv.refreshComplete();//设置刷新完成
        } else {
            if (adapter != null) {
                //判断适配器是否创建过
                adapter.loadMore(tempList);
                mXrv.loadMoreComplete();//设置加载更多完成
            }
        }
        if (adapter == null) {
            return;
        }
        adapter.setOnListItemClickListener(new XrvListAdapter.OnListItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                int pid = list.get(position).getPid();
                Intent intent = new Intent(ListActivity.this, ListDetailsActivity.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });
        //设置刷新和加载更多监听
        mXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                isRefresh = true;
                mPresenter.getPresenter(pscid+"");
            }

            @Override
            public void onLoadMore() {
                //加载更多
                isRefresh = false;
                mPresenter.getPresenter(pscid+"");
            }
        });


    }


}
