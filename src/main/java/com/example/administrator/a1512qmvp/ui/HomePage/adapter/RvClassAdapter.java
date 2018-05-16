package com.example.administrator.a1512qmvp.ui.HomePage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.a1512qmvp.R;
import com.example.administrator.a1512qmvp.bean.CatagoryBean;
import com.example.administrator.a1512qmvp.inter.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class RvClassAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<CatagoryBean.DataBean> list;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public RvClassAdapter(Context context, List<CatagoryBean.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rvclass_item, parent, false);
        ClassViewHolder classViewHolder = new ClassViewHolder(view);

        return classViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final ClassViewHolder classViewHolder= (ClassViewHolder) holder;
        CatagoryBean.DataBean dataBean = list.get(position);
        classViewHolder.iv.setImageURI(dataBean.getIcon());
        classViewHolder.tv.setText(dataBean.getName());
        classViewHolder.ll.setOnClickListener(new View.OnClickListener() {
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

     class ClassViewHolder extends RecyclerView.ViewHolder {

         private final SimpleDraweeView iv;
         private final TextView tv;
         private final LinearLayout ll;

         public ClassViewHolder(View view) {
             super(view);
             iv = view.findViewById(R.id.iv);
             tv = view.findViewById(R.id.tv);
             ll = view.findViewById(R.id.ll);


         }
     }
}
