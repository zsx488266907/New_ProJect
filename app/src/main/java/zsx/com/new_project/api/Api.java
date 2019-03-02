package zsx.com.new_project.api;

import io.reactivex.schedulers.Schedulers;

public class Api {
    public final static String BASE_API = "http://172.17.8.100/small/";
    public final static String USER_PRODUCT = BASE_API + "commodity/v1/commodityList";
    public static final String SEARCH_URL = BASE_API + "commodity/v1/findCommodityByKeyword?count=10";
    //详情
    public final static String DETAIL_URL = "commodity/v1/findCommodityDetailsById";
    //加入购物车
    public final static String SHOPPINGCART = BASE_API + "order/verify/v1/syncShoppingCart";
    //查询购物车
    public final static String SHOWCAR_API = BASE_API + "order/verify/v1/findShoppingCart";


}
