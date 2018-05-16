package com.example.asus.jingdong.ui.homepage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.bean.CatagoryBean;
import com.example.asus.jingdong.inter.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by asus on 2018/5/10.
 */

public class RvClassAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<CatagoryBean.DataBean> list;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public RvClassAdapter(Context context, List<CatagoryBean.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rvclass_item, parent, false);
        ViewHoler Holer = new ViewHoler(view);
        return Holer;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
//显示数据
        ViewHoler holer1 = (ViewHoler) holder;
        CatagoryBean.DataBean dataBean = list.get(position);
        holer1.iv.setImageURI(dataBean.getIcon());
        holer1.tv.setText(dataBean.getName());

        //给条目设置点击事件
        holer1.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHoler extends RecyclerView.ViewHolder {

        private final SimpleDraweeView iv;
        private final TextView tv;
        private final LinearLayout ll;

        public ViewHoler(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
