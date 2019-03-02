package zsx.com.new_project.model;


import android.annotation.SuppressLint;
import android.os.Handler;

import java.util.HashMap;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import zsx.com.new_project.bean.DetailBean;
import zsx.com.new_project.bean.ProBean;
import zsx.com.new_project.bean.ProductBean;
import zsx.com.new_project.bean.ShoppingCarBean;
import zsx.com.new_project.bean.ShowCarBean;
import zsx.com.new_project.callback.OkhttpCallBack;
import zsx.com.new_project.callback.RequestCallBack;
import zsx.com.new_project.contart.MyContract;
import zsx.com.new_project.retroftiUtils.OkhttpUtils;
import zsx.com.new_project.retroftiUtils.RetrofitServer;
import zsx.com.new_project.retroftiUtils.RetrofitUtils;

public class MyModel implements MyContract.MyModel {
    Handler handler = new Handler();

    @Override
    public void ProductList(HashMap<String, String> params, String str, final RequestCallBack requestCallBack) {
        OkhttpUtils.getmInstanse().doGet(str, params, new OkhttpCallBack() {
            @Override
            public void onFair(String msg) {
                if (requestCallBack != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallBack.onfair("网不行啊,老铁");
                        }
                    });
                }
            }

            @Override
            public void onsuccess(String msg) {
                requestCallBack.onsuccess(msg);

            }
        });

    }

    @SuppressLint("CheckResult")
    @Override
    public void Pro(HashMap<String, String> params, String str, final RequestCallBack requestCallBack) {


        RetrofitUtils.getInstanct().setRetrofitss(RetrofitServer.class)
                .GetSearch(str, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProBean>() {
                               @Override
                               public void accept(ProBean proBean) throws Exception {
                                   if (requestCallBack != null) {
                                       requestCallBack.onsucc(proBean);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (requestCallBack != null) {
                                       requestCallBack.onfair(throwable.getMessage());
                                   }
                               }
                           }
                );
    }


    @SuppressLint("CheckResult")
    @Override
    public void detail(HashMap<String, String> params, String str, final RequestCallBack requestCallBack) {

        RetrofitUtils.getInstanct().setRetrofitss(RetrofitServer.class)
                .GetDetail(str, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DetailBean>() {
                               @Override
                               public void accept(DetailBean detailBean) throws Exception {
                                   if (requestCallBack != null) {
                                       requestCallBack.onsucc(detailBean);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (requestCallBack != null) {
                                       requestCallBack.onfair(throwable.getMessage());
                                   }
                               }
                           }
                );

    }

    @SuppressLint("CheckResult")
    @Override
    public void shoppingcart(HashMap<String, String> params, String str, final RequestCallBack requestCallBack) {
        RetrofitUtils.getInstanct().setRetrofitss(RetrofitServer.class)
                .GetSHop(str, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShoppingCarBean>() {
                               @Override
                               public void accept(ShoppingCarBean shoppingCarBean) throws Exception {
                                   if (requestCallBack != null) {
                                       requestCallBack.onsucc(shoppingCarBean);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (requestCallBack != null) {
                                       requestCallBack.onfair(throwable.getMessage());
                                   }
                               }
                           }
                );
    }

    @SuppressLint("CheckResult")
    @Override
    public void shopcarshow(String url, final RequestCallBack requestCallBack) {
        RetrofitUtils.getInstanct().setRetrofitss(RetrofitServer.class)
                .GetShowcar(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShowCarBean>() {
                               @Override
                               public void accept(ShowCarBean showCarBean) throws Exception {
                                   if (requestCallBack != null) {
                                       requestCallBack.onsucc(showCarBean);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (requestCallBack != null) {
                                       requestCallBack.onfair(throwable.getMessage());
                                   }
                               }
                           }
                );
    }


}
