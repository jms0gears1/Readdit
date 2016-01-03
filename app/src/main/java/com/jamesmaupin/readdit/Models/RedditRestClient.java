package com.jamesmaupin.readdit.Models;
import com.jamesmaupin.readdit.Factories.RetrofitClientFactory;
import com.jamesmaupin.readdit.Utilities.Constants;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by jmsgears on 1/3/16.
 */
public class RedditRestClient {
    private interface Reddit{
            @FormUrlEncoded
            @POST("access_token")
            Observable<AccessTokenModel> obtainAccessToken(
                    @Field("grantM_type") String grantType,
                    @Field("code") String code,
                    @Field("redirect_uri") String redirectUri
            );

            @FormUrlEncoded
            @POST("access_token")
            Observable<AccessTokenModel> obtainUserlessAccessToken(
                    @Field("grant_type") String grantType,
                    @Field("device_id") String deviceId
            );

            @GET("/r/all")
            Observable<ThingModel> obtainAllSubreddit();
    }

    public static Observable<AccessTokenModel> getUserlessAuthToken(){
        return RetrofitClientFactory.create(Reddit.class, null)
                .obtainUserlessAccessToken(
                        Constants.GRANT_TYPE_USERLESS,
                        Constants.DEVICE_ID
                );
    }

    public static Observable<ThingModel> getAllSubreddit(String bearer){
        return RetrofitClientFactory.create(Reddit.class, bearer)
                .obtainAllSubreddit();
    }
}
