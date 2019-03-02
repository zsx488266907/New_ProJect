package zsx.com.new_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zsx.com.new_project.R;
import zsx.com.new_project.bean.ShowCarBean;


public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyVh> {
    private Context context;
    private List<ShowCarBean.ResultBean> list;
    private double numprice;
    private int num;

    public ShopAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(List<ShowCarBean.ResultBean> list) {
        if (list != null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }


    //全选反选方法
    public double qx(boolean b) {
        numprice = 0.0;
        for (ShowCarBean.ResultBean resultBean : list) {
            resultBean.setCheck(b);
            if (resultBean.isCheck()) {
                numprice += new BigDecimal(resultBean.getPrice()).doubleValue() * resultBean.getCount();
            }
        }
        notifyDataSetChanged();
        return numprice;
    }

    public double getprice() {
        numprice = 0.0;
        for (ShowCarBean.ResultBean resultBean : list) {
            if (resultBean.isCheck()) {
                numprice += new BigDecimal(resultBean.getPrice()).doubleValue() * resultBean.getCount();
            }
        }
        notifyDataSetChanged();
        return numprice;
    }

    //全选   反选
    public boolean checknotity() {
        boolean temp = false;
        for (ShowCarBean.ResultBean resultBean : list) {
            if (resultBean.isCheck()) {
                temp = true;
                numprice += new BigDecimal(resultBean.getPrice()).doubleValue();
            } else {
                temp = false;
            }
        }
        return temp;
    }

    @NonNull
    @Override
    public MyVh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.showcar_item, viewGroup, false);
        return new MyVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyVh myVh, final int i) {
        myVh.cart_img.setImageURI(list.get(i).getPic());
        myVh.check_change.setChecked(list.get(i).isCheck());
        myVh.item_name.setText(list.get(i).getCommodityName());
        myVh.item_price.setText("$：" + list.get(i).getPrice());
        myVh.item_count.setText(list.get(i).getCount() + "");
        num = list.get(i).getCount();
        myVh.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                list.get(i).setCount(num);
                if (setOnclick != null) {
                    setOnclick.OnClick();
                }
            }
        });
        myVh.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num > 0) {
                    num--;
                    list.get(i).setCount(num);
                    if (setOnclick != null) {
                        setOnclick.OnClick();
                    }
                }
            }
        });
        myVh.check_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).setCheck(myVh.check_change.isChecked());
                if (setOnclick != null) {
                    setOnclick.OnClick();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyVh extends RecyclerView.ViewHolder {
        @BindView(R.id.check_change)
        CheckBox check_change;
        @BindView(R.id.cart_img)
        SimpleDraweeView cart_img;
        @BindView(R.id.item_name)
        TextView item_name;
        @BindView(R.id.item_price)
        TextView item_price;
        @BindView(R.id.add)
        TextView add;
        @BindView(R.id.item_count)
        TextView item_count;
        @BindView(R.id.jian)
        TextView jian;

        public MyVh(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public SetOnclick setOnclick;

    public void setSetOnclick(SetOnclick setOnclick) {
        this.setOnclick = setOnclick;
    }

    public interface SetOnclick {
        void OnClick();
    }


}
