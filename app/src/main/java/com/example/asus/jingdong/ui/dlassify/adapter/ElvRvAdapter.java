package com.example.asus.jingdong.ui.dlassify.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.ProductCatagoryBean;
import com.example.asus.jingdong.inter.OnItemClickListener;

import java.util.List;

/**
 * Created by asus on 2018/5/11.
 */

public class ElvRvAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ProductCatagoryBean.DataBean.ListBean> listBeans;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    public ElvRvAdapter(Context context, List<ProductCatagoryBean.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
        inflater =LayoutInflater.from(context);
    }
    public void setonItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rvclassitem, parent, false);
        ElvRvAdapterViewHolder elvRvAdapterViewHolder = new ElvRvAdapterViewHolder(view);
        return elvRvAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ElvRvAdapterViewHolder holder1 = (ElvRvAdapterViewHolder) holder;
        ProductCatagoryBean.DataBean.ListBean listBean = listBeans.get(position);
        Glide.with(context).load(listBean.getIcon()).into(holder1.iv);

        holder1.tv.setText(listBean.getName());
        holder1.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }
    class ElvRvAdapterViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv;
        private final TextView tv;
        private final LinearLayout ll;

        public ElvRvAdapterViewHolder(View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
