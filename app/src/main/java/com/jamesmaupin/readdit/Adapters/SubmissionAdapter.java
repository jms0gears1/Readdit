package com.jamesmaupin.readdit.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jamesmaupin.readdit.R;

/**
 * Created by jmsgears on 1/1/16.
 */
public class SubmissionAdapter extends RecyclerView.Adapter<SubmissionAdapter.ViewHolder> {
    private int num;
    public SubmissionAdapter(int i) {
        num = i;
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
        return num;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
