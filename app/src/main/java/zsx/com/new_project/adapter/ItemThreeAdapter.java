package zsx.com.new_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


import zsx.com.new_project.R;
import zsx.com.new_project.bean.ProductBean;

public class ItemThreeAdapter extends RecyclerView.Adapter<ItemThreeAdapter.MyVh> {
    private Context context;
    private List<ProductBean.ProductItemBean.ProductItem> commodityList ;
    public ItemThreeAdapter(Context context, List<ProductBean.ProductItemBean.ProductItem> commodityList) {
        this.commodityList = commodityList;
        this.context =context;
    }

    @NonNull
    @Override
    public MyVh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.threeitem_layout,viewGroup,false);

        return new MyVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVh myVh, final int i) {

        myVh.titleTv.setText(commodityList.get(i).commodityName);
        Glide.with(context).load(commodityList.get(i).masterPic).into(myVh.iv);
        myVh.priceTv.setText("¥："+commodityList.get(i).price);
      myVh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().postSticky(commodityList.get(i).commodityId+"");

            }
        });


    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }

    public class MyVh extends RecyclerView.ViewHolder{
        private TextView titleTv,priceTv;
        private ImageView iv;
        public MyVh(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
            priceTv = itemView.findViewById(R.id.price);

        }
    }


}
