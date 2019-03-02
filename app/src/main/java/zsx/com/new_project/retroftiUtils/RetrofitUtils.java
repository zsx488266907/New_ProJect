package zsx.com.new_project.retroftiUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zsx.com.new_project.api.Api;
import zsx.com.new_project.application.MyApplication;

public class RetrofitUtils {
    private static RetrofitUtils instanct;
    private final Retrofit retrofit;
      SharedPreferences sp;
    public RetrofitUtils() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
             .addInterceptor(httpLoggingInterceptor)

                /*.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request=chain.request();
                        sp=MyApplication.getContext().getSharedPreferences("User",Context.MODE_PRIVATE);
                        String userId = sp.getString("userId","");
                        String sessionId = sp.getString("sessionId", "");
                        Request.Builder newBuilder = request.newBuilder();
                        newBuilder.method(request.method(),request.body());

                        if(!TextUtils.isEmpty(userId)&&!TextUtils.isEmpty(sessionId)){
                            newBuilder.addHeader("userId",userId);
                            newBuilder.addHeader("sessionId",sessionId);
                        }

                        Request request1 = newBuilder.build();

                        return chain.proceed(request1);
                    }
                })*/
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

    }

       public RetrofitServer setRetrofit(){
        return retrofit.create(RetrofitServer.class);
    }

    public <T> T setRetrofitss(Class<T> tClass){
        return retrofit.create(tClass);
    }
       public static RetrofitUtils getInstanct() {
        if (instanct == null){
            synchronized (RetrofitUtils.class){
                if (instanct == null){
                    instanct = new RetrofitUtils();
                }
            }
        }
        return instanct;
    }
}
