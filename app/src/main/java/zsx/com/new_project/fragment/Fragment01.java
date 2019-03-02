package zsx.com.new_project.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.recker.flybanner.FlyBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zsx.com.new_project.R;
import zsx.com.new_project.activity.DetailsActivity;
import zsx.com.new_project.adapter.ItemOneAdapter;
import zsx.com.new_project.adapter.ItemThreeAdapter;
import zsx.com.new_project.adapter.ItemTwoAdapter;
import zsx.com.new_project.adapter.ProductAdapter;
import zsx.com.new_project.adapter.SearchAdapter;
import zsx.com.new_project.api.Api;
import zsx.com.new_project.bean.ProBean;
import zsx.com.new_project.bean.ProductBean;
import zsx.com.new_project.contart.MyContract;
import zsx.com.new_project.presenter.Mypresenter;
import zsx.com.new_project.retroftiUtils.OkhttpUtils;


public class Fragment01 extends Fragment implements MyContract.MyView {
    int page = 1;
    @BindView(R.id.fly_banner)
    FlyBanner flyBanner;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.fangda)
    ImageView fangda;
    private List<String> list3;
    @BindView(R.id.onerv)
    XRecyclerView xRecyclerView;
    @BindView(R.id.tworv)
    XRecyclerView tworv;
    @BindView(R.id.xrv)
    XRecyclerView xrv;

    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.layout2)
    LinearLayout layout2;
    @BindView(R.id.layoutsou)
    LinearLayout layout3;
    @BindView(R.id.layoutsout)
    LinearLayout layout4;
    @BindView(R.id.layout5)
    RelativeLayout layout5;
    @BindView(R.id.layout6)
    RelativeLayout layout6;
    @BindView(R.id.layout7)
    LinearLayout layout7;

    @BindView(R.id.edit1)
    EditText edit1;

    private List<ProductBean.ProductItemBean> prolist = new ArrayList<>();
    Mypresenter mypresenter;
    private ProductAdapter productAdapter;
    private SearchAdapter searchAdapter;
    private HashMap<String, String> map1 = new HashMap<>();
    ;
    private String s;
    private String s2;
    private ItemOneAdapter itemOneAdapter;
    private ItemTwoAdapter itemTwoAdapter;
    private ItemThreeAdapter itemThreeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment01, container, false);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this, view);

        initView();
        initData();
        hh();
        return view;
    }

    private void hh() {

        tworv.setPullRefreshEnabled(true);
        tworv.setLoadingMoreEnabled(true);
        //    xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tworv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                Toast.makeText(getActivity(), "刷新...." + page, Toast.LENGTH_SHORT).show();
                xrecy();
                tworv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                Toast.makeText(getActivity(), "加载...." + page, Toast.LENGTH_SHORT).show();
                xrecy();
                tworv.loadMoreComplete();
            }
        });

        page = 1;
        xrecy();
    }


    private void xrecy() {
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout5.setVisibility(View.GONE);
                layout6.setVisibility(View.GONE);
                layout7.setVisibility(View.VISIBLE);
                tworv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                s = edit1.getText().toString();
                s2 = Api.SEARCH_URL + "&&keyword=" + s + "&&page=" + page;
                Toast.makeText(getActivity(), "111111" + s2, Toast.LENGTH_SHORT).show();
                mypresenter.pro(map1, s2);
            }
        });


    }

    private void initData() {
        mypresenter = new Mypresenter(this);

        fangda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout3.setVisibility(View.GONE);
                layout4.setVisibility(View.VISIBLE);
            }
        });


        HashMap<String, String> map = new HashMap<>();
        mypresenter.ProductList(map);


    }

    private void initView() {
        list3 = new ArrayList<>();
        list3.add("http://172.17.8.100/images/small/banner/cj.png");
        list3.add("http://172.17.8.100/images/small/banner/hzp.png");
        list3.add("http://172.17.8.100/images/small/banner/px.png");
        list3.add("http://172.17.8.100/images/small/banner/wy.png");
        flyBanner.setImagesUrl(list3);


    }

    @Override
    public void onFile(String s) {
        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onSucc(Object msg) {
       /* if (msg instanceof ProBean) {
            ProBean proBean1 = (ProBean) msg;
            searchAdapter.setList(proBean1.result);

        }
        searchAdapter = new SearchAdapter(getActivity());
        tworv.setAdapter(searchAdapter);

        //page++;
        tworv.refreshComplete();
        tworv.loadMoreComplete();*/



    }


    @Override
    public void onSuccessBean(List<ProductBean.ProductItemBean> list) {
        Toast.makeText(getActivity(), "请求成功", Toast.LENGTH_SHORT).show();

        productAdapter = new ProductAdapter(getActivity(), list);

        xrv.setAdapter(productAdapter);
        xrv.setLayoutManager(new LinearLayoutManager(getActivity()));

        productAdapter.setItemListener(new ProductAdapter.ItemListener() {
            @Override
            public void onItemClickListener(List<ProductBean.ProductItemBean> posi) {
                prolist = posi;

                if ("1002".equals(prolist.get(0).id)) {
                    layout.setVisibility(View.GONE);
                    layout2.setVisibility(View.VISIBLE);
                    itemOneAdapter = new ItemOneAdapter(getActivity(), prolist.get(0).commodityList);
                    xRecyclerView.setAdapter(itemOneAdapter);
                    xRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

                } else if ("1003".equals(prolist.get(1).id)) {
                    layout.setVisibility(View.GONE);
                    layout2.setVisibility(View.VISIBLE);
                    itemTwoAdapter = new ItemTwoAdapter(getActivity(), prolist.get(1).commodityList);
                    xRecyclerView.setAdapter(itemTwoAdapter);
                    xRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

                } else if ("1004".equals(prolist.get(2).id)) {
                    layout.setVisibility(View.GONE);
                    layout2.setVisibility(View.VISIBLE);
                    itemThreeAdapter = new ItemThreeAdapter(getActivity(), prolist.get(2).commodityList);
                    xRecyclerView.setAdapter(itemThreeAdapter);
                    xRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

                } else {
                    layout.setVisibility(View.VISIBLE);
                }

            }


        });


    }
// 跳转详情
    private void goweb(Class cls, int commodityId) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtra("commodityId", commodityId + "");
        getActivity().startActivity(intent);
    }
  //evebus点击ID
    @Subscribe(sticky = true)
    public void OnItemclick(String commodityId) {
        int i = Integer.parseInt(commodityId);
        goweb(DetailsActivity.class, i);

    }
      //解决内存泄漏
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mypresenter != null) {
            mypresenter.destroy();
        }
        OkhttpUtils.getmInstanse().cancalAllTask();
    }
}

