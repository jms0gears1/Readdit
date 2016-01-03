package com.jamesmaupin.readdit.Factories;

import android.util.Base64;

import com.jamesmaupin.readdit.Utilities.Constants;
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

    static OkHttpClient httpClient;

    public static <C> C create(Class<C> clientClass, String bearerToken) {
        String url;

        httpClient = new OkHttpClient();

        if (bearerToken == null) {
            setHeaders("Basic " + Base64.encodeToString(Constants.CREDENTIALS.getBytes(), Base64.NO_WRAP));
            url = Constants.BASE_URL;
        } else {
            setHeaders("bearer " + bearerToken);
            url = Constants.OAUTH_BASE_URL;
        }

        return redditRetrofitClient(clientClass, url);
    }

    private static void setHeaders(final String authorization) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor basicHeaderAuth = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(
                        chain.request()
                                .newBuilder()
                                .addHeader("Authorization", authorization)
                                .build()
                );
            }
        };

        httpClient.interceptors().add(basicHeaderAuth);
        httpClient.interceptors().add(loggingInterceptor);
    }

    public static <C> C redditRetrofitClient(Class<C> clientClass, String url) {

        Retrofit client = new Retrofit.Builder()
                .baseUrl(url)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return client.create(clientClass);
    }
}
