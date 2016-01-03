package com.jamesmaupin.readdit.Factories;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

/**
 * Created by jmsgears on 1/3/16.
 */
public class RetrofitClientFactory {

    OkHttpClient okHttpClient;
    HttpLoggingInterceptor loggingInterceptor;
    Interceptor authHeaderInterceptor;

    public static <C> C create(Class <C> mClass){
    }
}
