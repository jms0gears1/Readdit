package com.jamesmaupin.readdit.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jamesmaupin.readdit.Models.RedditRestClient;
import com.jamesmaupin.readdit.Models.ThingModel;
import com.jamesmaupin.readdit.Presenters.SubmissionPresenter;
import com.jamesmaupin.readdit.R;
import com.jamesmaupin.readdit.Adapters.SubmissionAdapter;
import com.jamesmaupin.readdit.Views.SubmissionView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SubmissionActivity extends AppCompatActivity implements SubmissionView{

    private RecyclerView mRecyclerView;
    private SubmissionAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ThingModel> mSubmissions;

    private SubmissionPresenter mPresenter;
    private String bearerToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initLogic();
    }

    public void initViews(){
        mRecyclerView = (RecyclerView) findViewById(R.id.rvSubmissions);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SubmissionAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void initLogic(){
        mPresenter = new SubmissionPresenter(this);
        mPresenter.getUserlessAuthToken();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setSubmissions(List<ThingModel> submissions) {
        mSubmissions = submissions;
        mAdapter.changeData(mSubmissions);
    }

    @Override
    public void setAuthoToken(String bearer) {
        this.bearerToken = bearer;
        mPresenter.loadDefaultSubreddit(bearerToken);
    }

    @Override
    public void showError(String error) {
        Log.e(this.getClass().getSimpleName(), error);
    }
}
