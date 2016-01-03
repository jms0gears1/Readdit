package com.jamesmaupin.readdit.Presenters;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jamesmaupin.readdit.Models.AccessTokenModel;
import com.jamesmaupin.readdit.Models.RedditRestClient;
import com.jamesmaupin.readdit.Models.ThingModel;
import com.jamesmaupin.readdit.Views.SubmissionView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jmsgears on 1/3/16.
 */
public class SubmissionPresenter {

    SubmissionView submissionView;

    public SubmissionPresenter(SubmissionView submissionView) {
        this.submissionView = submissionView;
    }

    public void getUserlessAuthToken() {
        RedditRestClient.getUserlessAuthToken()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AccessTokenModel>() {
                    @Override
                    public void onCompleted() {
                        if (!isUnsubscribed())
                            unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        submissionView.showError(e.toString());
                    }

                    @Override
                    public void onNext(AccessTokenModel accessTokenModel) {
                        submissionView.showError(accessTokenModel.access_token);
                        submissionView.setAuthoToken(accessTokenModel.access_token);
                    }
                });
    }

    public void loadDefaultSubreddit(String bearer) {
        RedditRestClient.getAllSubreddit(bearer)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ThingModel>() {
                    @Override
                    public void onCompleted() {
                        if(!isUnsubscribed())
                            unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        submissionView.showError(e.toString());
                    }

                    @Override
                    public void onNext(ThingModel thingModel) {
                        submissionView.setSubmissions(thingModel.data.children);
                    }
                });

    }
}
