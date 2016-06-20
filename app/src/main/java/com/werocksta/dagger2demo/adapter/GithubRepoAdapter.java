package com.werocksta.dagger2demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class GithubRepoAdapter extends RecyclerView.Adapter<GithubRepoAdapter.RepoViewHolder> {

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RepoViewHolder extends RecyclerView.ViewHolder {

        public RepoViewHolder(View itemView) {
            super(itemView);
        }
    }
}
