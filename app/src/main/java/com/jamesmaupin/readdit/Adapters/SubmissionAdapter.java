package com.jamesmaupin.readdit.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        holder.tvKarma.setText(Integer.toString(things.get(position).data.score));
        holder.tvUserName.setText(things.get(position).data.author);
        holder.tvSubreddit.setText(things.get(position).data.subreddit);
        holder.tvTitle.setText(things.get(position).data.title);
    }

    @Override
    public int getItemCount() {
        return things==null?0:things.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvKarma;
        public TextView tvUserName;
        public TextView tvSubreddit;
        public TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            View card = itemView.findViewById(R.id.cvItem).findViewById(R.id.relativeLayout);
            tvKarma = (TextView) card.findViewById(R.id.tvKarma);
            tvUserName = (TextView) card.findViewById(R.id.tvUserName);
            tvSubreddit = (TextView) card.findViewById(R.id.tvSubReddit);
            tvTitle = (TextView) card.findViewById(R.id.tvTitle);
        }
    }
}
