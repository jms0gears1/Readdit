package com.jamesmaupin.readdit.Factories;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jamesmaupin.readdit.Models.DataModel;
import com.jamesmaupin.readdit.Models.ThingModel;
import com.jamesmaupin.readdit.Utilities.Constants;
import com.jamesmaupin.readdit.Utilities.GenericJsonDeserializer;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by jmsgears on 1/3/16.
 */
public class RetrofitClientFactory {

    static OkHttpClient okHttpClient;
    static HttpLoggingInterceptor loggingInterceptor;
    static Interceptor authHeaderInterceptor;
    static String base_url;

    public static <C> C create(Class <C> mClass, String bearerToken){
        okHttpClient = new OkHttpClient();


        if(bearerToken==null) {
            initHeaders("Basic " + Base64.encodeToString(Constants.CREDENTIALS.getBytes(), Base64.NO_WRAP));
            base_url = Constants.BASE_URL;
        }else {
            initHeaders("bearer " + bearerToken);
            base_url = Constants.OAUTH_BASE_URL;
        }

        return redditRetrofitClient(
                mClass,
                base_url);
    }

    private static void initHeaders(final String authentication){
        loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        authHeaderInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(
                        chain.request()
                        .newBuilder()
                        .addHeader("Authorization", authentication)
                        .build()
                );
            }
        };

        okHttpClient.interceptors().add(loggingInterceptor);
        okHttpClient.interceptors().add(authHeaderInterceptor);
    }

    private static Gson buildGsonConverter(){
        return new GsonBuilder()
                .registerTypeAdapter(
                        DataModel.class,
                        new GenericJsonDeserializer<>(DataModel.class, "data")
                )
                .registerTypeAdapter(
                        ThingModel.class,
                        new GenericJsonDeserializer<>(ThingModel.class, "children")
                )
                .create();
    }

    private static <C> C redditRetrofitClient(Class<C> mClass, String url){
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(buildGsonConverter()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(mClass);
    }
}
