package com.jamesmaupin.readdit.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.jamesmaupin.readdit.Models.ThingModel;
import com.jamesmaupin.readdit.R;
import com.jamesmaupin.readdit.Adapters.SubmissionAdapter;
import com.jamesmaupin.readdit.Views.SubmissionView;

import java.util.List;

public class SubmissionActivity extends AppCompatActivity implements SubmissionView{

    private RecyclerView mRecyclerView;
    private SubmissionAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ThingModel> mSubmissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvSubmissions);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SubmissionAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
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
}
