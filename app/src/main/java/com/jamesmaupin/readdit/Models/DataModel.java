package com.jamesmaupin.readdit.Models;

import java.util.List;

/**
 * Created by jmsgears on 1/3/16.
 */
public class DataModel {
    //A modhash is a token that the reddit API requires to help prevent CSRF
    public String modhash;
    //Reddit's Thing is the basis of most Reddit JSON replies
    public List<ThingModel> children;

    public String domain;
    public String subreddit;
    public String id;
    public String author;
    public String thumbnail;
    public String permalink;
    public String url;
    public String title;

    public int score;
    public int num_comments;
    public int downs;
    public int ups;

    public long created_utc;

    public boolean hide_score;
    public boolean edited;
    public boolean visited;




}
