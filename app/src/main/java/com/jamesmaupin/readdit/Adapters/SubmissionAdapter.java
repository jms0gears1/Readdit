package com.jamesmaupin.readdit.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jamesmaupin.readdit.Models.ThingModel;
import com.jamesmaupin.readdit.R;

import java.util.List;

/**
 * Created by jmsgears on 1/1/16.
 */
public class SubmissionAdapter extends RecyclerView.Adapter<SubmissionAdapter.ViewHolder> {
    private List<ThingModel> things;

    public SubmissionAdapter(List<ThingModel> things) {
        this.things = things;
    }

    public void changeData(List<ThingModel> things){
        this.things = things;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.submission_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return things==null?0:things.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
