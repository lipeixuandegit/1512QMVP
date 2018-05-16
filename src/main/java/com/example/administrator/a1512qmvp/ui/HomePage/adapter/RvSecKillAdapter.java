package com.example.administrator.a1512qmvp.ui.HomePage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.a1512qmvp.R;
import com.example.administrator.a1512qmvp.bean.AdBean;
import com.example.administrator.a1512qmvp.inter.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2018/5/11.
 */

public class RvSecKillAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<AdBean.MiaoshaBean.ListBeanX> list;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public RvSecKillAdapter(Context context, List<AdBean.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rvseckill_item, parent, false);
        SecKillViewHolder secKillViewHolder = new SecKillViewHolder(view);
        return secKillViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        SecKillViewHolder secKillViewHolder = (SecKillViewHolder) holder;
        AdBean.MiaoshaBean.ListBeanX listBeanX = list.get(position);
        String url = listBeanX.getImages().split("\\|")[0];
        secKillViewHolder.iv.setImageURI(url);
        secKillViewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SecKillViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView iv;

        public SecKillViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
