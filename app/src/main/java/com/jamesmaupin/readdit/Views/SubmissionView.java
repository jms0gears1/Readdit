package com.jamesmaupin.readdit.Views;

import com.jamesmaupin.readdit.Models.ThingModel;

import java.util.List;

/**
 * Created by jmsgears on 1/3/16.
 */
public interface SubmissionView {
    void setSubmissions(List<ThingModel> submissions);
    void setAuthoToken(String bearer);
    void showError(String error);
}
