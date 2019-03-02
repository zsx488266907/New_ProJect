package zsx.com.new_project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zsx.com.new_project.R;
import zsx.com.new_project.api.Api;
import zsx.com.new_project.bean.DetailBean;
import zsx.com.new_project.bean.ProductBean;
import zsx.com.new_project.bean.ShoppingCarBean;
import zsx.com.new_project.bean.SynBean;
import zsx.com.new_project.contart.MyContract;
import zsx.com.new_project.presenter.Mypresenter;

public class DetailsActivity extends AppCompatActivity implements MyContract.MyView ,View.OnClickListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.detil_xbanner)
    XBanner detil_xbanner;
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.add)
    ImageView toadd;
    @BindView(R.id.buy)
    ImageView buy;
    private String commodityId;
    private SynBean synBean;
    private List<SynBean> list;
    Mypresenter mypresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        initView();
         initData();

    }

    private void initData() {

        mypresenter = new Mypresenter(this);
        Intent intent = getIntent();
        String commodityId = intent.getStringExtra("commodityId");
        final HashMap<String,String> map = new HashMap<>();
        map.put("commodityId",commodityId);
        mypresenter.detail(map,Api.DETAIL_URL);
        toadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = new Gson().toJson(list);
                HashMap<String,String> map1 = new HashMap<>();
                map1.put("data",s);
                mypresenter.shoppingcart(map,Api.SHOPPINGCART);
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initView() {
        list = new ArrayList<>();
        back.setOnClickListener(this);
        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptEnabled(true);
        // 支持缩放
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);


    }

    @Override
    public void onFile(String s) {

    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onSucc(Object msg) {
        if (msg!=null){
            DetailBean detailBean = (DetailBean) msg;
            synBean = new SynBean();
            synBean.commodityId=detailBean.getResult().getCommodityId();
            synBean.count=1;
            list.add(synBean);
            price.setText(detailBean.getResult().getPrice());
            name.setText(detailBean.getResult().getCommodityName());
            final List<String> list = Arrays.asList(detailBean.getResult().getPicture().split(","));
            detil_xbanner.setData(list,null);
            detil_xbanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(DetailsActivity.this).load(list.get(position)).into((ImageView) view);
                }
            });
            webView.loadData(detailBean.getResult().getDetails(),"Text/html","UTF-8");
            webView.setWebChromeClient(new WebChromeClient());
            webView.setWebViewClient(new WebViewClient());
        }
    }


    @Override
    public void onSuccessBean(List<ProductBean.ProductItemBean> list) {

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
