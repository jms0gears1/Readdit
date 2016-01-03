package com.jamesmaupin.readdit.Models;

import java.util.List;

/**
 * Created by jmsgears on 1/3/16.
 */
public class DataModel {
    //A modhash is a token that the reddit API requires to help prevent CSRF
    String modhash;
    //Reddit's Thing is the basis of most Reddit JSON replies
    List<ThingModel> children;

    String domain;
    String subreddit;
    String id;
    String author;
    String thumbnail;
    String permalink;
    String url;

    int score;
    int num_comments;
    int downs;
    int ups;

    long created_utc;

    boolean hide_score;
    boolean edited;
    boolean visited;




}
