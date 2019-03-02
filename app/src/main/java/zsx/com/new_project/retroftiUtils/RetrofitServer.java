package zsx.com.new_project.retroftiUtils;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import zsx.com.new_project.bean.DetailBean;
import zsx.com.new_project.bean.ProBean;
import zsx.com.new_project.bean.ProductBean;
import zsx.com.new_project.bean.ShoppingCarBean;
import zsx.com.new_project.bean.ShowCarBean;

public interface RetrofitServer {
    //详情(用的是okhttputils网络请求)
    @GET
    Observable<ProductBean> GetProduct(@Url String url, @QueryMap HashMap<String, String> map);
    //搜索
    @GET
    Observable<ProBean> GetSearch(@Url String url, @QueryMap HashMap<String, String> map);

    //详情
    @GET
    Observable<DetailBean> GetDetail(@Url String url, @QueryMap HashMap<String, String> map);

    //加入购物车
    @PUT
    @FormUrlEncoded
    Observable<ShoppingCarBean> GetSHop(@Url String url, @FieldMap HashMap<String, String> map);

    //查询购物车
    @GET
    Observable<ShowCarBean> GetShowcar(@Url String url);


}
