package zsx.com.new_project.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zsx.com.new_project.R;
import zsx.com.new_project.adapter.ShopAdapter;
import zsx.com.new_project.api.Api;
import zsx.com.new_project.bean.ProductBean;
import zsx.com.new_project.bean.ShowCarBean;
import zsx.com.new_project.contart.MyContract;
import zsx.com.new_project.presenter.Mypresenter;


public class Fragment03 extends Fragment implements MyContract.MyView,ShopAdapter.SetOnclick,CheckBox.OnClickListener {
    @BindView(R.id.shopcar)
    RecyclerView shopcar;
    @BindView(R.id.check_radio)
    CheckBox check_radio;
    @BindView(R.id.zongprice)
    TextView zongprice;
    @BindView(R.id.jiesuan)
    Button jiesuan;
    private ShopAdapter shopAdapter;
    private Mypresenter mypresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment03, container, false);
        mypresenter = new Mypresenter(this);
        ButterKnife.bind(this,view);
        goLogin();
        initView(view);
        initData();
        return view;
    }

    private void goLogin() {

    }

    private void initData() {
         mypresenter.shopcarshow(Api.SHOWCAR_API);
    }

    private void initView(View view) {
        shopcar.setLayoutManager(new LinearLayoutManager(getActivity()));
        shopAdapter = new ShopAdapter(getActivity());
        shopcar.setAdapter(shopAdapter);
        shopAdapter.setSetOnclick(this);
        check_radio.setOnClickListener(this);
    }

    @Override
    public void onFile(String s) {

    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onSucc(Object msg) {
        if (msg!=null) {
            ShowCarBean showShopCar = (ShowCarBean) msg;
            shopAdapter.setList(showShopCar.getResult());
            if (shopAdapter.checknotity()){
                check_radio.setChecked(true);
            }else{
                check_radio.setChecked(false);
            }
            zongprice.setText("￥:"+shopAdapter.getprice());
        }
    }


    @Override
    public void onSuccessBean(List<ProductBean.ProductItemBean> list) {

    }

    @Override
    public void OnClick() {
        if (shopAdapter.checknotity()){
            check_radio.setChecked(true);
        }else{
            check_radio.setChecked(false);
        }
        double getprice = shopAdapter.getprice();
        zongprice.setText("￥:"+getprice);
    }
    //全选反选
    @Override
    public void onClick(View v) {
        double qx = shopAdapter.qx(check_radio.isChecked());

        zongprice.setText("￥:"+qx);

    }
}
