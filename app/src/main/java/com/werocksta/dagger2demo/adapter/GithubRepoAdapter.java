package com.werocksta.dagger2demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.werocksta.dagger2demo.R;
import com.werocksta.dagger2demo.model.RepoCollection;
import com.werocksta.dagger2demo.widget.CustomRecyclerView;

import java.util.List;

public class GithubRepoAdapter extends CustomRecyclerView {

    private List<RepoCollection> repositories;
    private OnClickRepository clickRepository;

    public void setGithubAdapter(List<RepoCollection> repositories, OnClickRepository clickRepository) {
        this.repositories = repositories;
        this.clickRepository = clickRepository;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item_row, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RepoCollection repository = repositories.get(position);
        RepoViewHolder viewHolder = (RepoViewHolder) holder;
        viewHolder.tvName.setText(repository.getNameRepo());
        viewHolder.tvLanguage.setText(repository.getLanguage());

        viewHolder.cvRepo.setOnClickListener(v -> {
            clickRepository.onClickRepoItem(repository);
        });
    }

    @Override
    public int getItemCount() {
        return isRepoEmpty() ? 0 : repositories.size();
    }

    private boolean isRepoEmpty() {
        return repositories == null;
    }

    public interface OnClickRepository {
        void onClickRepoItem(RepoCollection repo);
    }

}
